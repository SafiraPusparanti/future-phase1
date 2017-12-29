package service;

import model.TransactionModel;

import java.sql.SQLException;
import java.util.List;

public interface TransactionService {
    List<TransactionModel> getTransactionList(String startDate, String endDate) throws SQLException;
}
