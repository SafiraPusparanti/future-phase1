package service;

import dao.LedgerDAO;
import model.LedgerModel;

import java.sql.SQLException;
import java.util.List;

public class LedgerServiceDatabase implements LedgerService {
    LedgerDAO ledgerDAO = new LedgerDAO();

    @Override
    public List<LedgerModel> getWeeklyLedger() throws SQLException{
        List<LedgerModel> weeklyLedger = ledgerDAO.getWeeklyLedger();

        String currencyWrapper = "";
        for(int i = 0; i < weeklyLedger.size(); i++) {
            currencyWrapper = String.format("Rp %,.2f", weeklyLedger.get(i).getIncome());
            weeklyLedger.get(i).setCurrencyWrapper(currencyWrapper);
        }

        return weeklyLedger;
    }

    @Override
    public List<LedgerModel> getMonthlyLedger() throws SQLException {
        List<LedgerModel> monthlyLedger =  ledgerDAO.getMonthlyLedger();

        String currencyWrapper = "";
        for(int i = 0; i < monthlyLedger.size(); i++) {
            currencyWrapper = String.format("Rp %,.2f", monthlyLedger.get(i).getIncome());
            monthlyLedger.get(i).setCurrencyWrapper(currencyWrapper);
        }

        return monthlyLedger;
    }

    @Override
    public List<LedgerModel> getYearlyLedger() throws SQLException {
        List<LedgerModel> yearlyLedger = ledgerDAO.getYearlyLedger();

        String currencyWrapper = "";
        for(int i = 0; i < yearlyLedger.size(); i++) {
            currencyWrapper = String.format("Rp %,.2f", yearlyLedger.get(i).getIncome());
            yearlyLedger.get(i).setCurrencyWrapper(currencyWrapper);
        }

        return yearlyLedger;
    }
}
