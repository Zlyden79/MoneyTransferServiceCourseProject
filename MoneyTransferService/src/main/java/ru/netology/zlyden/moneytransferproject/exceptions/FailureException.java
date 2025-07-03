package ru.netology.zlyden.moneytransferproject.exceptions;

public class FailureException extends RuntimeException {
    public FailureException(String message) {
        super(message);
    }
}
