package service;

import model.LedgerModel;

import java.sql.SQLException;
import java.util.List;

public interface LedgerService {
    List<LedgerModel> getWeeklyLedger() throws SQLException;
    List<LedgerModel> getMonthlyLedger() throws SQLException;
    List<LedgerModel> getYearlyLedger() throws SQLException;
}
