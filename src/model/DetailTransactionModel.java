package model;

public class DetailTransactionModel {
    private String transactionId;
    private String productId;
    private float subTotal;
    private int quantity;
    private String productName;// for transaction history feature
    private int productPrice;// for transaction history feature
    private String currencyWrapper;

    public DetailTransactionModel(String transactionId, String productId, float subTotal, int quantity) {
        this.transactionId = transactionId;
        this.productId = productId;
        this.subTotal = subTotal;
        this.quantity = quantity;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getCurrencyWrapper() {
        return currencyWrapper;
    }

    public void setCurrencyWrapper(String currencyWrapper) {
        this.currencyWrapper = currencyWrapper;
    }
}
