package com.jmrcode.msv_exchange_rate_inquiry.domain;

import lombok.Builder;

/**
 * <br/> ExchangeRateDto <br/>
 * <b>Class</b>: ExchangeRateDto<br/>
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
@Builder
public record ExchangeRateDto(String date,
                              double sunat,
                              double purchase,
                              double sale) {
}
