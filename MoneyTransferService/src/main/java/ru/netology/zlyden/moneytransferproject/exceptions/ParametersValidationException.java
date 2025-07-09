package ru.netology.zlyden.moneytransferproject.exceptions;

public class ParametersValidationException extends RuntimeException {
  public ParametersValidationException(String message) {
    super(message);
  }
}
