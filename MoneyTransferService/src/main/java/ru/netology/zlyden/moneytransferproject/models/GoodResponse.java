package ru.netology.zlyden.moneytransferproject.models;

import java.util.Objects;

public class GoodResponse {
    private String operationId;

    public GoodResponse() {
    }

    public GoodResponse(String operationId) {
        this.operationId = operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GoodResponse that = (GoodResponse) o;
        return Objects.equals(operationId, that.operationId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(operationId);
    }

    @Override
    public String toString() {
        return "{" +
                "operationId='" + operationId + '\'' +
                '}';
    }
}
