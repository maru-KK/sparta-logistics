package com.sparta.logistics.hubcompany.presentation.exception.exceptions;

public class AccessDeniedException extends RuntimeException {
  public AccessDeniedException(String message) {
    super(message);
  }
}
