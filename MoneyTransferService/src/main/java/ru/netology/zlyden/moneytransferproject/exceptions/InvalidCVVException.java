package ru.netology.zlyden.moneytransferproject.exceptions;

public class InvalidCVVException extends RuntimeException {
    public InvalidCVVException(String message) {
        super(message);
    }
}
