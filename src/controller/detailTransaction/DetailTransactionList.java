package controller.detailTransaction;

import com.google.gson.Gson;
import model.DetailTransactionModel;
import service.DetailTransactionService;
import service.DetailTransactionServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/detail-transactions/list")
public class DetailTransactionList extends HttpServlet{
    DetailTransactionService detailTransactionService = new DetailTransactionServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String transactionId = request.getParameter("transactionId");
        try{
            List<DetailTransactionModel> detailTransactionList = detailTransactionService.getDetailTransactionList(transactionId);

            response.getWriter().write(new Gson().toJson(detailTransactionList));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
