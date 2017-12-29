package service;

import model.DetailTransactionModel;

import java.sql.SQLException;
import java.util.List;

public interface DetailTransactionService {
    List<DetailTransactionModel> getDetailTransactionList(String transactionId) throws SQLException;
}
