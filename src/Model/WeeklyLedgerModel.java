package Model;

public class WeeklyLedgerModel {
    private String date;
    private int income;

    public WeeklyLedgerModel(String date, int income) {
        this.date = date;
        this.income = income;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }
}
