package com.bbc.push.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception that should be thrown when the request is bad.
 *
 * @author Ahmad A. Furqan
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {

  private static final long serialVersionUID = 7227648413709685738L;

  public BadRequestException(String message) {
    super(message);
  }

}
