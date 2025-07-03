package ru.netology.zlyden.moneytransferproject.models;

public class Amount {
    private String currency;
    //@Positive
    private int value;

    public Amount() {
    }

    public Amount(String currency, int value) {
        this.currency = currency;
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "currency='" + currency + '\'' +
                ", value=" + value +
                '}';
    }
}
