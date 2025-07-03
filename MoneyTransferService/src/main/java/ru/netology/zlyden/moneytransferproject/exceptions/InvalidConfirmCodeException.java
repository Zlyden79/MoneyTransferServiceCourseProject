package ru.netology.zlyden.moneytransferproject.exceptions;

public class InvalidConfirmCodeException extends RuntimeException {
    public InvalidConfirmCodeException(String message) {
        super(message);
    }
}
