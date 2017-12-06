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

@WebServlet("/admin/ledger/weekly")
public class LedgerWeekly extends HttpServlet {
    LedgerService ledgerService = new LedgerServiceDatabase();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("masuk doGet weekly");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try{
            List<LedgerModel> weeklyLedger = ledgerService.getWeeklyLedger();
            System.out.println(new Gson().toJson(weeklyLedger));
            response.getWriter().write(new Gson().toJson(weeklyLedger));
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
