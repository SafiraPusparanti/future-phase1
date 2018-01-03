package service;

import dao.TransactionDAO;
import model.TransactionModel;

import java.sql.SQLException;
import java.util.List;

public class TransactionServiceDatabase implements TransactionService {
    TransactionDAO transactionDAO = new TransactionDAO();

    @Override
    public void addTransaction(TransactionModel trasaction) throws SQLException {
        try {
            transactionDAO.addTransaction(trasaction);
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String getMaxId() throws SQLException {
        String maxId = null;

        try {
            maxId = transactionDAO.getMaxId();
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }

        return maxId;
    }

    @Override
    public List<TransactionModel> getTransactionList(String startDate, String endDate) throws SQLException {
        List<TransactionModel> transactionList = null;

        try {
            transactionList = transactionDAO.getTransactionList(startDate, endDate);
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }

        String currencyWrapper = "";

        for (int i = 0; i < transactionList.size(); i++) {
            currencyWrapper = String.format("Rp %,.2f", transactionList.get(i).getTotalPrice());
            transactionList.get(i).setCurrencyWrapper(currencyWrapper);
        }

        return transactionList;
    }
}
