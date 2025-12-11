package com.jmrcode.msv_exchange_rate_inquiry.common.exception.dto;

import lombok.Builder;

/**
 * <br/> ErrorResponse <br/>
 * <b>Class</b>: ErrorResponse<br/>
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
public record ErrorResponse(String code,
                            String description,
                            String typeError,
                            String datetimeException) {
}
