package com.jmrcode.msv_exchange_rate_inquiry.adapter.inbound;

import com.jmrcode.avro.ExchangeRate;
import com.jmrcode.avro.ExchangeRateResponse;
import com.jmrcode.avro.IdentityDocument;
import com.jmrcode.avro.IdentityDocumentRequest;
import com.jmrcode.msv_exchange_rate_inquiry.application.ExchangeRateService;
import com.jmrcode.msv_exchange_rate_inquiry.port.outbound.ExchangeRateEventPublisherPort;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;

/**
 * <br/> ExchangeRateEventListener <br/>
 * <b>Class</b>: ExchangeRateEventListener<br/>
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
@AllArgsConstructor
@Log4j2
public class ExchangeRateEventListener {
  private final ExchangeRateService exchangeRateService;
  private final ExchangeRateEventPublisherPort publisher;

  @KafkaListener(topics = "${topics.input}", groupId = "${spring.kafka.consumer.group-id}")
  public void onMessage(@Payload IdentityDocumentRequest request) {
    Disposable disposable = exchangeRateService.getCurrentExchangeRate()
        .subscribe(processed -> {
          ExchangeRate exchangeRate = ExchangeRate.newBuilder()
              .setDate(LocalDate.parse(processed.date()))
              .setSunat(processed.sunat())
              .setPurchase(processed.purchase())
              .setSale(processed.sale())
              .setConsultationdate(Instant.now())
              .build();

          IdentityDocument identityDocument = IdentityDocument.newBuilder()
              .setRequestId(request.getRequestId())
              .setDocumenttype(request.getDocumenttype())
              .setDocumentnumber(request.getDocumentnumber())
              .build();

          ExchangeRateResponse response = ExchangeRateResponse.newBuilder()
              .setIdentityDocument(identityDocument)
              .setExchangeRate(exchangeRate)
              .build();
          publisher.publish(response);
          log.info("Published ExchangeRateResponse for IdentityDocument: {}", response);
        }, error -> {
          publisher.publish(new ExchangeRateResponse());
          log.error("Error processing Exchange Rate for IdentityDocument: {}: {}", request, error.getMessage());
        });
  }


}
