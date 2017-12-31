package service;

import model.DetailTransactionModel;

import java.sql.SQLException;
import java.util.List;

public interface DetailTransactionService {
    void addDetailTransaction(DetailTransactionModel detailTransaction) throws SQLException;
    List<DetailTransactionModel> getDetailTransactionList(String transactionId) throws SQLException;
}
