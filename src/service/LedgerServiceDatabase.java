package service;

import dao.LedgerDAO;
import model.LedgerModel;

import java.sql.SQLException;
import java.util.List;

public class LedgerServiceDatabase implements LedgerService {
    LedgerDAO ledgerDAO = new LedgerDAO();

    @Override
    public List<LedgerModel> getWeeklyLedger() throws SQLException{
        List<LedgerModel> weeklyLedger = null;

        try {
            weeklyLedger = ledgerDAO.getWeeklyLedger();
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }

        String currencyWrapper = "";
        for(int i = 0; i < weeklyLedger.size(); i++) {
            currencyWrapper = String.format("Rp %,.2f", weeklyLedger.get(i).getIncome());
            weeklyLedger.get(i).setCurrencyWrapper(currencyWrapper);
        }

        return weeklyLedger;
    }

    @Override
    public List<LedgerModel> getMonthlyLedger() throws SQLException {
        List<LedgerModel> monthlyLedger = null;

        try {
            monthlyLedger = ledgerDAO.getMonthlyLedger();
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }

        String currencyWrapper = "";
        for(int i = 0; i < monthlyLedger.size(); i++) {
            currencyWrapper = String.format("Rp %,.2f", monthlyLedger.get(i).getIncome());
            monthlyLedger.get(i).setCurrencyWrapper(currencyWrapper);
        }

        return monthlyLedger;
    }

    @Override
    public List<LedgerModel> getYearlyLedger() throws SQLException {
        List<LedgerModel> yearlyLedger = null;

        try {
            yearlyLedger = ledgerDAO.getYearlyLedger();
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }

        String currencyWrapper = "";
        for(int i = 0; i < yearlyLedger.size(); i++) {
            currencyWrapper = String.format("Rp %,.2f", yearlyLedger.get(i).getIncome());
            yearlyLedger.get(i).setCurrencyWrapper(currencyWrapper);
        }

        return yearlyLedger;
    }
}
