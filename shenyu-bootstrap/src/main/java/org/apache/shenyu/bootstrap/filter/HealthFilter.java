package org.apache.shenyu.bootstrap.filter;


import org.apache.shenyu.plugin.api.utils.WebFluxResultUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;


/**
 * @author dsp
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 9)
public class HealthFilter implements WebFilter {

    private static final String[] HEALTH_URI_ARRAY = {"/health", "/actuator/health"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return Arrays.stream(HEALTH_URI_ARRAY).anyMatch(uri -> Objects.equals(uri, exchange.getRequest().getPath().toString())) ? response(exchange)
                : chain.filter(exchange);
    }

    private Mono<Void> response(ServerWebExchange exchange) {
        final String content = "I'm fine!";
        return exchange.getResponse()
                .writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(content.getBytes(StandardCharsets.UTF_8)))
                        .doOnNext(data -> exchange.getResponse().getHeaders().setContentLength(data.readableByteCount())));
//        return WebFluxResultUtils.result(exchange, content);
    }

}
