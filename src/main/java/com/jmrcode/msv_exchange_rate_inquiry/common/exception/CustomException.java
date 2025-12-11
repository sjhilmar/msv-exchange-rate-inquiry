package com.jmrcode.msv_exchange_rate_inquiry.common.exception;

import com.jmrcode.msv_exchange_rate_inquiry.common.exception.enums.ExceptionCatalog;
import lombok.Builder;
import lombok.Getter;

/**
 * <br/> CustomException <br/>
 * <b>Class</b>: CustomException<br/>
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
@Getter
public class   CustomException extends RuntimeException{

  private final ExceptionCatalog catalog;

  public CustomException(ExceptionCatalog catalog) {
    super(catalog.getDescription());
    this.catalog = catalog;
  }

  public CustomException(ExceptionCatalog catalog,
                         Throwable cause) {
    super(catalog.getDescription(), cause);
    this.catalog = catalog;
  }
}
