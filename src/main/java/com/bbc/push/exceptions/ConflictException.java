package com.bbc.push.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception that should be thrown when there is a conflict.
 *
 * @author Ahmad A. Furqan
 *
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends Exception {

  private static final long serialVersionUID = -2265322692236143599L;

  public ConflictException(String message) {
    super(message);
  }

}
