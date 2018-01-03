package controller.cashier;

import model.CartItemModel;
import model.DetailTransactionModel;
import model.TransactionModel;
import service.DetailTransactionService;
import service.DetailTransactionServiceDatabase;
import service.TransactionService;
import service.TransactionServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cashier/pay")
public class CashierPay extends HttpServlet {
    TransactionService transactionService = new TransactionServiceDatabase();
    DetailTransactionService detailTransactionService = new DetailTransactionServiceDatabase();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, CartItemModel> shoppingCart = (HashMap<String, CartItemModel>) request.getSession().getAttribute("cart");

        int idNo = 0;
        try {
            String strIdNo = transactionService.getMaxId();
            strIdNo = strIdNo.substring(2, 12);
            idNo = Integer.parseInt(strIdNo);
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }

        String transactionId = "TR" + String.format("%010d", ++idNo);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try{
            TransactionModel transaction = new TransactionModel( transactionId,
                    (String) request.getSession().getAttribute("userId"),
                    timestamp,
                    (float) request.getSession().getAttribute("totalPrice"));
            transactionService.addTransaction(transaction);
        } catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }

        for (String key : shoppingCart.keySet()) {
            DetailTransactionModel detailTransaction = new DetailTransactionModel(
                    transactionId,
                    shoppingCart.get(key).getProductId(),
                    shoppingCart.get(key).getSubTotal(),
                    shoppingCart.get(key).getQuantity());

            try {
                detailTransactionService.addDetailTransaction(detailTransaction);
            } catch (SQLException e) {
                System.out.println("Error : " + e.getMessage());
                e.printStackTrace();
            }
        }

        request.getSession().setAttribute("cart", null);
        request.getSession().setAttribute("totalPrice", "0");
    }
}
