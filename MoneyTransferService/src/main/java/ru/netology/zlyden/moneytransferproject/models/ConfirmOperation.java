package ru.netology.zlyden.moneytransferproject.models;

public class ConfirmOperation {
    //@NotNull
    //@NotEmpty
    private String operationId;
    //@NotNull
    //@NotEmpty
    //@Size(min=4, max=6)
    private String code;

    public ConfirmOperation() {
    }

    public ConfirmOperation(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "{" +
                "operationId='" + operationId + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
