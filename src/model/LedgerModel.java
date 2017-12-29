package model;

public class LedgerModel {
    private float income;
    private String ledgerDate;
    private String startDate;
    private String endDate;
    private String currencyWrapper;

    public LedgerModel(float income, String ledgerDate, String startDate, String endDate) {
        this.income = income;
        this.ledgerDate = ledgerDate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public String getLedgerDate() {
        return ledgerDate;
    }

    public void setLedgerDate(String ledgerDate) {
        this.ledgerDate = ledgerDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCurrencyWrapper() {
        return currencyWrapper;
    }

    public void setCurrencyWrapper(String currencyWrapper) {
        this.currencyWrapper = currencyWrapper;
    }
}
