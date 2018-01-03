package controller.transaction;

import com.google.gson.Gson;
import model.TransactionModel;
import service.TransactionService;
import service.TransactionServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/transactions/list")
public class TransactionList extends HttpServlet {
    TransactionService transactionService = new TransactionServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        try{
            List<TransactionModel> transactionList = transactionService.getTransactionList(startDate, endDate);

            try {
                response.getWriter().write(new Gson().toJson(transactionList));
            } catch (IOException e) {
                System.out.println("Error : " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e){
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
