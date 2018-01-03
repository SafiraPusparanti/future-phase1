package controller.ledger;

import com.google.gson.Gson;
import model.LedgerModel;
import service.LedgerService;
import service.LedgerServiceDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/ledger/yearly")
public class LedgerYearly extends HttpServlet {
    LedgerService ledgerService = new LedgerServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try{
            List<LedgerModel> yearlyLedger = ledgerService.getYearlyLedger();

            try {
                response.getWriter().write(new Gson().toJson(yearlyLedger));
            } catch (IOException e) {
                System.out.println("Error : " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
    }
}