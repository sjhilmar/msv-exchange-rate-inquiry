package com.jmrcode.msv_exchange_rate_inquiry.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

/**
 * <br/> WebClientConfig <br/>
 * <b>Class</b>: WebClientConfig<br/>
 * Copyright: &copy; 2025 JMR Code.<br/>
 *
 * @author JMR Code <br/>
 * <u>Developed by</u>: <br/>
 * <ul>
 *     <li>Sergio Jhilmar Alvarez Toledo (SJAT)</li>
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 *     <li>Nov. 01, 2025 Creaci&oacute;n de Clase.</li>
 * </ul>
 * @version 1.0
 */
@Configuration
public class WebClientConfig {

  @Value("${external.api.base-url}")
  private String baseUrl;

  @Value("${external.api.timeout}")
  private int timeout;

  @Value("${external.api.max-in-memory-bytes_size}")
  private int maxInMemorySize;

  @Bean
  @Qualifier("externalApiWebClient")
  public WebClient externalApiWebClient(WebClient.Builder builder){
    HttpClient httpClient = HttpClient.create()
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
        .responseTimeout(java.time.Duration.ofMillis(timeout))
        .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(timeout, TimeUnit.MILLISECONDS)));

    ExchangeStrategies strategies = ExchangeStrategies.builder()
        .codecs(config -> config.defaultCodecs().maxInMemorySize(maxInMemorySize))
        .build();
    return builder.baseUrl(baseUrl)
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchangeStrategies(strategies)
        .build();
  }

  @Bean
  public WebClient.Builder webClientBuilder() {
    return WebClient.builder();
  }
}
