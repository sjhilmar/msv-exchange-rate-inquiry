package com.jmrcode.msv_exchange_rate_inquiry.common.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import com.jmrcode.msv_exchange_rate_inquiry.common.exception.dto.ErrorResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * <br/> ExceptionHandler <br/>
 * <b>Class</b>: ExceptionHandler<br/>
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
@Log4j2
@RestControllerAdvice
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
    log.error("CustomException caught: {}", ex.getMessage());
    return ResponseEntity
        .status(ex.getCatalog().getStatus())
        .body(ErrorResponse.builder()
            .code(ex.getCatalog().getCode())
            .description(ex.getCatalog().getDescription())
            .typeError(ex.getCatalog().getType())
            .datetimeException(LocalDateTime.now().toString())
            .build());
  }
}
