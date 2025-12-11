package com.jmrcode.msv_exchange_rate_inquiry.common.exception.enums;

import static com.jmrcode.msv_exchange_rate_inquiry.common.util.Constants.BUSINESS;
import static com.jmrcode.msv_exchange_rate_inquiry.common.util.Constants.TECHNICAL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <br/> ExceptionCatalog <br/>
 * <b>Class</b>: ExceptionCatalog<br/>
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
@AllArgsConstructor
@Getter
public enum ExceptionCatalog {
  EXCHANGE_RATE_NOT_FOUND("EX001", "Exchange rate not found for the given currency.", BUSINESS,
      HttpStatus.NOT_FOUND.value()),
  EXCHANGE_RATE_API_ERROR("EX002", "Error occurred while fetching exchange rate from external API.",
      TECHNICAL, HttpStatus.INTERNAL_SERVER_ERROR.value()),
  MANAGEMENT_EXCHANGE_RATE_ERROR("EX003", "Error occurred while managing exchange rate.", TECHNICAL,
      HttpStatus.INTERNAL_SERVER_ERROR.value()),
  BUSINESS_ERROR_LIMIT_EXCEEDED("EX004", "Business operation limit exceeded.", BUSINESS,
      HttpStatus.TOO_MANY_REQUESTS.value()),
  BUSINESS_ERROR_INVALID_REQUEST("EX005", "Invalid request parameters for business operation.",
      BUSINESS, HttpStatus.BAD_REQUEST.value()),
  BUSINESS_ERROR_DNI_NOT_FOUND("EX006", "DNI not found for the consultant user.", BUSINESS,
      HttpStatus.NOT_FOUND.value());

  private final String code;
  private final String description;
  private final String type;
  private final int status;
}
