package org.apache.shenyu.plugin.httpclient.metrics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.netty.http.client.HttpClientMetricsRecorder;

import java.net.SocketAddress;
import java.time.Duration;


/**
 * @author dsp
 */
public class ShenyuHttpClientHttpClientMetricsRecorder implements HttpClientMetricsRecorder {

    private Logger log = LoggerFactory.getLogger(ShenyuHttpClientHttpClientMetricsRecorder.class);

    @Override
    public void recordDataReceivedTime(SocketAddress remoteAddress, String uri, String method, String status, Duration time) {
        log.info("===*** receive upstream message: address={}, method={}, uri={}, status={}, duration={}ms", remoteAddress, method, uri, status, time.toMillis());
    }

    @Override
    public void recordDataSentTime(SocketAddress remoteAddress, String uri, String method, Duration time) {
        log.info("===*** call upstream info: address={}, method={}, uri={}, duration={}ms", remoteAddress, method, uri, time.toMillis());
    }

    @Override
    public void recordResponseTime(SocketAddress remoteAddress, String uri, String method, String status, Duration time) {
        // 后端服务处理及网络往返耗时
        log.info("===*** call upstream cost: address={}, method={}, uri={}, status={}, duration={}ms", remoteAddress, method, uri, status, time.toMillis());
    }

    @Override
    public void recordDataReceived(SocketAddress remoteAddress, String uri, long bytes) {
        log.info("===recordDataReceived: address={}, uri={}, bytes={}", remoteAddress, uri, bytes);
    }

    @Override
    public void recordDataSent(SocketAddress remoteAddress, String uri, long bytes) {
        log.info("===recordDataSent: address={}, uri={}, bytes={}", remoteAddress, uri, bytes);
    }

    @Override
    public void incrementErrorsCount(SocketAddress remoteAddress, String uri) {
        log.info("===incrementErrorsCount: address={}, uri={}", remoteAddress, uri);
    }

    @Override
    public void recordDataReceived(SocketAddress remoteAddress, long bytes) {
        log.info("===recordDataReceived: address={}, bytes={}", remoteAddress, bytes);
    }

    @Override
    public void recordDataSent(SocketAddress remoteAddress, long bytes) {
        log.info("===recordDataSent: address={}, bytes={}", remoteAddress, bytes);
    }

    @Override
    public void incrementErrorsCount(SocketAddress remoteAddress) {
        log.info("===incrementErrorsCount, address={}", remoteAddress);
    }

    @Override
    public void recordTlsHandshakeTime(SocketAddress remoteAddress, Duration time, String status) {
        log.info("===recordTlsHandshakeTime, address={}, status={}, duration={}ms", remoteAddress, status, time.toMillis());
    }

    @Override
    public void recordConnectTime(SocketAddress remoteAddress, Duration time, String status) {
        log.info("===recordConnectTime, address={}, status={}, duration={}ms", remoteAddress, status, time.toMillis());
    }

    @Override
    public void recordResolveAddressTime(SocketAddress remoteAddress, Duration time, String status) {
        log.info("===recordResolveAddressTime, address={}, status={}, duration={}ms", remoteAddress, status, time.toMillis());
    }
}
