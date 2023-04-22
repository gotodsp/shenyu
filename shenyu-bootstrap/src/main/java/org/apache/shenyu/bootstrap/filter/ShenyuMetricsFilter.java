package org.apache.shenyu.bootstrap.filter;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.metrics.web.reactive.server.CancelledServerWebExchangeException;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


/**
 * @author dsp
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ShenyuMetricsFilter implements WebFilter {

    private static Logger log = LoggerFactory.getLogger(ShenyuMetricsFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("收到请求");
        return chain.filter(exchange).transformDeferred((call) -> filter(exchange, call));
    }

    private Publisher<Void> filter(ServerWebExchange exchange, Mono<Void> call) {
        log.info("=== 收到请求");
        long start = System.currentTimeMillis();
        return call.doOnEach((signal) -> onTerminalSignal(exchange, signal.getThrowable(), start))
                .doOnCancel(() -> onTerminalSignal(exchange, new CancelledServerWebExchangeException(), start));
    }

    private void onTerminalSignal(ServerWebExchange exchange, Throwable cause, long start) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted() || cause instanceof CancelledServerWebExchangeException) {
            record(exchange, cause, start);
        } else {
            response.beforeCommit(() -> {
                record(exchange, cause, start);
                return Mono.empty();
            });
        }
    }

    private void record(ServerWebExchange exchange, Throwable cause, long start) {
        try {
            cause = (cause != null) ? cause : exchange.getAttribute(ErrorAttributes.ERROR_ATTRIBUTE);
            // TODO 耗时 = WebFilter链耗时 + shenyu插件耗时 + 后端服务处理及网络往返耗时
            long duration = System.currentTimeMillis() - start;
            log.info("=== 结束请求,耗时={}ms", duration, cause);
        } catch (Exception ex) {
            log.warn("Failed to record timer metrics", ex);
            // Allow exchange to continue, unaffected by metrics problem
        }
    }

}
