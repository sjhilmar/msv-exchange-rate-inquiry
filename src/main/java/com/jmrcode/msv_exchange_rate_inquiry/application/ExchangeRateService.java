package com.jmrcode.msv_exchange_rate_inquiry.application;

import com.jmrcode.avro.ExchangeRateResponse;
import com.jmrcode.msv_exchange_rate_inquiry.adapter.outbound.ExchangeRateApiAdapter;
import com.jmrcode.msv_exchange_rate_inquiry.common.exception.CustomException;
import com.jmrcode.msv_exchange_rate_inquiry.domain.ExchangeRateDto;
import dev.mccue.guava.base.Throwables;
import io.reactivex.rxjava3.core.Single;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.jmrcode.msv_exchange_rate_inquiry.common.exception.enums.ExceptionCatalog.EXCHANGE_RATE_API_ERROR;

/**
 * <br/> ExchangeRateService <br/>
 * <b>Class</b>: ExchangeRateService<br/>
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
@Service
@AllArgsConstructor
@Log4j2
public class ExchangeRateService {

  private final ExchangeRateApiAdapter exchangeRateApiAdapter;

  public Single<ExchangeRateDto> getCurrentExchangeRate() {
    return exchangeRateApiAdapter.getCurrentRate()
        .doOnError(error -> log.error("Error fetching current exchange rate: {}",
            error.getMessage()))
        .onErrorResumeWith(error ->
            Single.error(new CustomException(EXCHANGE_RATE_API_ERROR, (Throwable) error)));
  }
}