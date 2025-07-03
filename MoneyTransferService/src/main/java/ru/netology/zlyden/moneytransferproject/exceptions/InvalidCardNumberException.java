package ru.netology.zlyden.moneytransferproject.exceptions;

public class InvalidCardNumberException extends RuntimeException {
    public InvalidCardNumberException(String message) {
        super(message);
    }
}
