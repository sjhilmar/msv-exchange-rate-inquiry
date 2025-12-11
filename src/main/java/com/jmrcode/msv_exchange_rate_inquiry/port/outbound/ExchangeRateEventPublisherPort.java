package com.jmrcode.msv_exchange_rate_inquiry.port.outbound;

import com.jmrcode.avro.ExchangeRateResponse;

/**
 * <br/> ExchangeRateEventProducer <br/>
 * <b>Class</b>: ExchangeRateEventProducer<br/>
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
public interface ExchangeRateEventPublisherPort {

  void publish(ExchangeRateResponse event);

}
