package model;

import java.sql.Timestamp;

public class TransactionModel {
    private String transactionId;
    private String cashierId;
    private Timestamp timeTrans;
    private float totalPrice;
    private String currencyWrapper;

    public TransactionModel(String transactionId, String cashierId, Timestamp timeTrans, float totalPrice) {
        this.transactionId = transactionId;
        this.cashierId = cashierId;
        this.timeTrans = timeTrans;
        this.totalPrice = totalPrice;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactioId) {
        this.transactionId = transactioId;
    }

    public String getCashierId() {
        return cashierId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }

    public Timestamp getTimeTrans() {
        return timeTrans;
    }

    public void setTimeTrans(Timestamp timeTrans) {
        this.timeTrans = timeTrans;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCurrencyWrapper() {
        return currencyWrapper;
    }

    public void setCurrencyWrapper(String currencyWrapper) {
        this.currencyWrapper = currencyWrapper;
    }
}
