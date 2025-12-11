package com.jmrcode.msv_exchange_rate_inquiry.adapter.outbound;

import com.jmrcode.avro.ExchangeRateResponse;
import com.jmrcode.msv_exchange_rate_inquiry.port.outbound.ExchangeRateEventPublisherPort;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * <br/> ExchangeRateEventPublisher <br/>
 * <b>Class</b>: ExchangeRateEventPublisher<br/>
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
public class ExchangeRateEventProducer implements ExchangeRateEventPublisherPort {
  private final KafkaTemplate<String, ExchangeRateResponse> kafkaTemplate;
  private final String topic = "exchange-rate-responses";


  @Override
  public void publish(ExchangeRateResponse event) {
    kafkaTemplate.send(topic, event);
  }
}
