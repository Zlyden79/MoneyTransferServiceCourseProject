package ru.netology.zlyden.moneytransferproject.models;

public class MoneyTransfer {
    //@Pattern(regexp = "\\d{16}")
    private String cardFromNumber;
    //@Pattern(regexp = "\\d{16}")
    private String cardToNumber;
    //@Pattern(regexp = "\\d{3}")
    private String cardFromCVV;
    //@Pattern(regexp = "(0[1-9]|1[0-2])\\/[0-9]{2}")
    private String cardFromValidTill;
    //@NotNull
    private Amount amount;

    public MoneyTransfer() {
    }

    public MoneyTransfer(String cardFromNumber, String cardToNumber, String cardFromCVV, String cardFromValidTill, Amount amount) {
        this.cardFromNumber = cardFromNumber;
        this.cardToNumber = cardToNumber;
        this.cardFromCVV = cardFromCVV;
        this.cardFromValidTill = cardFromValidTill;
        this.amount = amount;
    }

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public void setCardFromNumber(String cardFromNumber) {
        this.cardFromNumber = cardFromNumber;
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public void setCardToNumber(String cardToNumber) {
        this.cardToNumber = cardToNumber;
    }

    public String getCardFromCVV() {
        return cardFromCVV;
    }

    public void setCardFromCVV(String cardFromCVV) {
        this.cardFromCVV = cardFromCVV;
    }

    public String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public void setCardFromValidTill(String cardFromValidTill) {
        this.cardFromValidTill = cardFromValidTill;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        int transferFee = amount.getValue() / 100;
        return "{" +
                "cardFromNumber='" + cardFromNumber + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' +
                ", amount='" + amount + '\'' +
                ", transferFee='" + transferFee + '\'' +
                '}';
    }
}
