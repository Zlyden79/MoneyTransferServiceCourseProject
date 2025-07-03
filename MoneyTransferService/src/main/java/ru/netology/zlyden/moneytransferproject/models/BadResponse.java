package ru.netology.zlyden.moneytransferproject.models;

import java.util.Objects;

public class BadResponse {
    private String message;
    private int id;

    public BadResponse() {
    }

    public BadResponse(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BadResponse that = (BadResponse) o;
        return id == that.id && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, id);
    }

    @Override
    public String toString() {
        return "{" +
                "message='" + message + '\'' +
                ", id=" + id +
                '}';
    }
}
