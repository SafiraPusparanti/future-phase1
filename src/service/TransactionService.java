package service;

import model.TransactionModel;

import java.sql.SQLException;
import java.util.List;

public interface TransactionService {
    void addTransaction(TransactionModel trasaction) throws SQLException;
    String getMaxId() throws SQLException;
    List<TransactionModel> getTransactionList(String startDate, String endDate) throws SQLException;
}
