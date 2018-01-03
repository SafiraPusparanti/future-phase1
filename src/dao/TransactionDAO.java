package dao;

import connection.ConnectDB;
import model.TransactionModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    ConnectDB connDB;

    public TransactionDAO() {
        this.connDB = new ConnectDB();
    }

    public void addTransaction(TransactionModel transaction) throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("INSERT INTO transaction_pos (transaction_id, cashier_id, time_trans, total_price) VALUES (?,?,?,?)");
            ps.setString(1, transaction.getTransactionId());
            ps.setString(2, transaction.getCashierId());
            ps.setTimestamp(3, transaction.getTimeTrans());
            ps.setFloat(4, transaction.getTotalPrice());
            System.out.println(ps);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();

        } finally {
            rs.close();
            ps.close();
            conn.close();
        }
    }

    public String getMaxId() throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String maxId = null;

        try {
            ps = conn.prepareStatement("SELECT transaction_id FROM transaction_pos ORDER BY transaction_id DESC LIMIT 1");
            System.out.println(ps);

            rs = ps.executeQuery();
            rs.next();
            maxId = rs.getString(1);

        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();

        } finally {
            rs.close();
            ps.close();
            conn.close();

            return maxId;
        }
    }

    public List<TransactionModel> getTransactionList(String startDate, String endDate) throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String maxId = null;
        List<TransactionModel> transactionList = new ArrayList<TransactionModel>();

        try {
            ps = conn.prepareStatement("SELECT transaction_id, cashier_id, time_trans, total_price " +
                    "FROM transaction_pos " +
                    "WHERE time_trans BETWEEN ?::DATE AND ?::DATE");
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            System.out.println(ps);
            rs = ps.executeQuery();

            while (rs.next()) {
                transactionList.add(new TransactionModel(rs.getString(1), rs.getString(2), rs.getTimestamp(3), rs.getFloat(4)));
            }

        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();

        } finally {
            rs.close();
            ps.close();
            conn.close();

            return transactionList;
        }
    }
}
