package controller.transaction;

import service.TransactionService;
import service.TransactionServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/transactions")
public class TransactionMenu extends HttpServlet {
    TransactionService transaction = new TransactionServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/admin-transactions.jsp";

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);

        //TODO: check whether user have already logged in as admin
        request.getRequestDispatcher(address).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
