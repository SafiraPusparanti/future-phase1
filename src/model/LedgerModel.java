package model;

import java.sql.Timestamp;
import java.util.Date;

public class LedgerModel {
    private Timestamp timeTrans;
    private double income;
    private Date ledgerDate;

    public LedgerModel(Timestamp timeTrans, double income, Date ledgerDate) {
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

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public Date getLedgerDate() {
        return ledgerDate;
    }

    public void setLedgerDate(Date ledgerDate) {
        this.ledgerDate = ledgerDate;
    }
}
