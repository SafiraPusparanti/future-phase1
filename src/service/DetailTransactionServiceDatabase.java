package service;

import dao.DetailTransactionDAO;
import model.DetailTransactionModel;

import java.sql.SQLException;
import java.util.List;

public class DetailTransactionServiceDatabase implements DetailTransactionService {
    DetailTransactionDAO detailTransactionDAO = new DetailTransactionDAO();

    @Override
    public List<DetailTransactionModel> getDetailTransactionList(String transactionId) throws SQLException {
        List<DetailTransactionModel> detailTransactionList = detailTransactionDAO.getDetailTransactionList(transactionId);

        String currencyWrapper = "";
        for(int i = 0; i < detailTransactionList.size(); i++) {
            currencyWrapper = String.format("Rp %,.2f", detailTransactionList.get(i).getSubTotal());
            detailTransactionList.get(i).setCurrencyWrapper(currencyWrapper);
        }

        return detailTransactionList;
    }
}
