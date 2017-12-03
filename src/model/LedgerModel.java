package model;

import java.sql.Timestamp;
import java.util.Date;

public class LedgerModel {
    private Timestamp timeTrans;
    private float income;
    private String ledgerDate;

    public LedgerModel(Timestamp timeTrans, float income, String ledgerDate) {
        this.timeTrans = timeTrans;
        this.income = income;
        this.ledgerDate = ledgerDate;
    }

    public Timestamp getTimeTrans() {
        return timeTrans;
    }

    public void setTimeTrans(Timestamp timeTrans) {
        this.timeTrans = timeTrans;
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
}
