package ru.netology.zlyden.moneytransferproject.exceptions;

public class InvalidOperationIdException extends RuntimeException {
  public InvalidOperationIdException(String message) {
    super(message);
  }
}
