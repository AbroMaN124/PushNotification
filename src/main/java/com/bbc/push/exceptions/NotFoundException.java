package com.bbc.push.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception that should be thrown when a resource is not found.
 *
 * @author Ahmad A. Furqan
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {

  private static final long serialVersionUID = -3830527213767044674L;

  public NotFoundException(String message) {
    super(message);
  }
}
