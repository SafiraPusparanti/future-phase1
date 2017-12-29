package service;

import dao.TransactionDAO;
import model.TransactionModel;

import java.sql.SQLException;
import java.util.List;

public class TransactionServiceDatabase implements TransactionService {
    TransactionDAO transactionDAO = new TransactionDAO();

    @Override
    public List<TransactionModel> getTransactionList(String startDate, String endDate) throws SQLException {
        List<TransactionModel> transactionList = transactionDAO.getTransactionList(startDate, endDate);

        String currencyWrapper = "";

        for(int i = 0; i < transactionList.size(); i++) {
            currencyWrapper = String.format("Rp %,.2f", transactionList.get(i).getTotalPrice());
            transactionList.get(i).setCurrencyWrapper(currencyWrapper);
        }

        return transactionList;
    }
}
