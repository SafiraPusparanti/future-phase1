package dao;

import model.TransactionModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/POS";
    public static final String username = "postgres";
    public static final String password = "aldoleonardo";

    Connection conn;

    public TransactionDAO() {
        try {
            Class.forName(database);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void addTransaction(TransactionModel transaction) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO transaction_pos (transaction_id, cashier_id, time_trans, total_price) VALUES (?,?,?,?)");
        ps.setString(1, transaction.getTransactionId());
        ps.setString(2, transaction.getCashierId());
        ps.setTimestamp(3, transaction.getTimeTrans());
        ps.setFloat(4, transaction.getTotalPrice());
        System.out.println(ps);
        ps.executeUpdate();
    }

    public String getMaxId() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT transaction_id FROM transaction_pos ORDER BY transaction_id DESC LIMIT 1");
        System.out.println(ps);

        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString(1);
    }

    public List<TransactionModel> getTransactionList(String startDate, String endDate) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT transaction_id, cashier_id, time_trans, total_price " +
                "FROM transaction_pos " +
                "WHERE time_trans BETWEEN ?::DATE AND ?::DATE");
        ps.setString(1, startDate);
        ps.setString(2, endDate);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();

        List<TransactionModel> transactionList = new ArrayList<TransactionModel>();
        while (rs.next()) {
            transactionList.add(new TransactionModel(rs.getString(1), rs.getString(2), rs.getTimestamp(3), rs.getFloat(4)));
        }
        return transactionList;
    }
}
