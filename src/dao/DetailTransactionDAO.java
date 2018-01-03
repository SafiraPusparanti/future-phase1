package dao;

import connection.ConnectDB;
import model.DetailTransactionModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailTransactionDAO {
    ConnectDB connDB;

    public DetailTransactionDAO() {
        this.connDB = new ConnectDB();
    }

    public void addDetailTransaction(DetailTransactionModel transaction) throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("INSERT INTO detail_transaction (transaction_id, product_id, sub_total, quantity) VALUES (?,?,?,?)");
            ps.setString(1, transaction.getTransactionId());
            ps.setString(2, transaction.getProductId());
            ps.setFloat(3, transaction.getSubTotal());
            ps.setInt(4, transaction.getQuantity());
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

    public List<DetailTransactionModel> getDetailTransactionList(String transactionId) throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DetailTransactionModel> detailTransactionList = new ArrayList<DetailTransactionModel>();

        try {
            ps = conn.prepareStatement("SELECT d.transaction_id, d.product_id, d.sub_total, d.quantity, p.name, p.price " +
                    "FROM detail_transaction d, product p " +
                    "WHERE d.transaction_id = ? AND d.product_id = p.product_id");
            ps.setString(1, transactionId);
            System.out.println(ps);
            rs = ps.executeQuery();

            int i = 0;
            while (rs.next()) {
                detailTransactionList.add(new DetailTransactionModel(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getInt(4)));
                detailTransactionList.get(i).setProductName(rs.getString(5));
                detailTransactionList.get(i).setProductPrice(rs.getInt(6));

                i++;
            }
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();

        } finally {
            rs.close();
            ps.close();
            conn.close();

            return detailTransactionList;
        }
    }
}
