package com.jmrcode.msv_exchange_rate_inquiry.adapter.outbound;

import com.jmrcode.avro.ExchangeRateResponse;
import com.jmrcode.msv_exchange_rate_inquiry.domain.ExchangeRateDto;
import com.jmrcode.msv_exchange_rate_inquiry.port.outbound.ExchangeRateApiPort;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static com.jmrcode.msv_exchange_rate_inquiry.common.util.Constants.TODAY;

/**
 * <br/> ExchangeRateApiAdapter <br/>
 * <b>Class</b>: ExchangeRateApiAdapter<br/>
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
@Component
public class ExchangeRateApiAdapter implements ExchangeRateApiPort {

  private final WebClient webClient;

  public ExchangeRateApiAdapter(@Qualifier("externalApiWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Single<ExchangeRateDto> getCurrentRate() {
    return Single.fromPublisher(
        webClient.get()
            .uri(TODAY)
            .retrieve()
            .bodyToMono(ExchangeRateDto.class)
    );
  }
}
