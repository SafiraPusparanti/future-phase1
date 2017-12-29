package dao;

import model.LedgerModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LedgerDAO {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/safirapusparanti?currentSchema=kanmakan";
    public static final String username = "postgres";
    public static final String password = "dbfira";

    Connection conn;

    public LedgerDAO() {

        try {
            Class.forName(database);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public List<LedgerModel> getWeeklyLedger() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT SUM(total_price) AS weekly_income, " +
                "(DATE_TRUNC('week', time_trans) + '6 days'::interval)::DATE AS weekly_date, " +
                "TO_CHAR((DATE_TRUNC('week', time_trans) + '6 days'::interval)::DATE, 'Mon dd, yyyy') AS ledger_name, " +
                "(DATE_TRUNC('week', time_trans))::DATE AS start_date " +
                "FROM transaction_pos " +
                "GROUP BY weekly_date, ledger_name, start_date " +
                "ORDER BY weekly_date DESC");
        ResultSet rs = ps.executeQuery();

        List<LedgerModel> weeklyLedger = new ArrayList<LedgerModel>();
        while (rs.next()) {
            weeklyLedger.add(new LedgerModel(rs.getFloat(1), rs.getString(3), rs.getString(4), rs.getString(2)));
        }
        return weeklyLedger;
    }

    public List<LedgerModel> getMonthlyLedger() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT SUM(total_price) AS monthly_income, " +
                "DATE_TRUNC('month', time_trans)::DATE AS monthly_date, " +
                "TO_CHAR(time_trans, 'Month yyyy') AS ledger_name, " +
                "(DATE_TRUNC('month', time_trans) + '1 month'::interval - '1 day'::interval)::DATE AS end_date " +
                "FROM transaction_pos " +
                "GROUP BY monthly_date, ledger_name, end_date " +
                "ORDER BY monthly_date DESC");
        ResultSet rs = ps.executeQuery();

        List<LedgerModel> monthlyLedger = new ArrayList<LedgerModel>();
        while (rs.next()) {
            monthlyLedger.add(new LedgerModel(rs.getFloat(1), rs.getString(3), rs.getString(2), rs.getString(4)));
        }
        return monthlyLedger;
    }

    public List<LedgerModel> getYearlyLedger() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT SUM(total_price) AS yearly_income, " +
                "DATE_TRUNC('year', time_trans)::DATE AS yearly_date, " +
                "TO_CHAR(time_trans, 'yyyy') AS ledger_name, " +
                "(DATE_TRUNC('year', time_trans) + '1 year'::interval - '1 day'::interval)::DATE AS end_date " +
                "FROM transaction_pos " +
                "GROUP BY yearly_date, ledger_name, end_date " +
                "ORDER BY yearly_date DESC");
        ResultSet rs = ps.executeQuery();

        List<LedgerModel> yearlyLedger = new ArrayList<LedgerModel>();
        while (rs.next()) {
            yearlyLedger.add(new LedgerModel(rs.getFloat(1), rs.getString(3), rs.getString(2), rs.getString(4)));
        }
        return yearlyLedger;
    }
}
