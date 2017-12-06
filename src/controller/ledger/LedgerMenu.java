package controller.ledger;

import service.LedgerService;
import service.LedgerServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/ledger")
public class LedgerMenu extends HttpServlet {
    LedgerService ledgerService = new LedgerServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = "/view/admin-ledger.jsp";

        //TODO: check whether user have already logged in as admin
        request.getRequestDispatcher(address).forward(request, response);
    }
}