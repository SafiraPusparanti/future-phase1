package service;

import dao.LedgerDAO;
import model.LedgerModel;

import java.sql.SQLException;
import java.util.List;

public class LedgerServiceDatabase implements LedgerService {
    LedgerDAO ledgerDAO = new LedgerDAO();

    @Override
    public List<LedgerModel> getWeeklyLedger() throws SQLException{
        return ledgerDAO.getWeeklyLedger();
    }

    @Override
    public List<LedgerModel> getMonthlyLedger() throws SQLException {
        return ledgerDAO.getMonthlyLedger();
    }

    @Override
    public List<LedgerModel> getYearlyLedger() throws SQLException {
        return ledgerDAO.getYearlyLedger();
    }
}
