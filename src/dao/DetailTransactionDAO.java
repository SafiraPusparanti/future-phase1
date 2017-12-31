package dao;

import model.DetailTransactionModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailTransactionDAO {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/safirapusparanti?currentSchema=kanmakan";
    public static final String username = "postgres";
    public static final String password = "dbfira";

    Connection conn;

    public DetailTransactionDAO() {
        try {
            Class.forName(database);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void addDetailTransaction(DetailTransactionModel transaction) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO detail_transaction (transaction_id, product_id, sub_total, quantity) VALUES (?,?,?,?)");
        ps.setString(1, transaction.getTransactionId());
        ps.setString(2, transaction.getProductId());
        ps.setFloat(3, transaction.getSubTotal());
        ps.setInt(4, transaction.getQuantity());
        System.out.println(ps);
        ps.executeUpdate();
    }

    public List<DetailTransactionModel> getDetailTransactionList(String transactionId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT d.transaction_id, d.product_id, d.sub_total, d.quantity, p.name, p.price " +
                "FROM detail_transaction d, product p " +
                "WHERE d.transaction_id = ? AND d.product_id = p.product_id");
        ps.setString(1, transactionId);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();

        List<DetailTransactionModel> detailTransactionList = new ArrayList<DetailTransactionModel>();
        int i = 0;
        while (rs.next()) {
            detailTransactionList.add(new DetailTransactionModel(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getInt(4)));
            detailTransactionList.get(i).setProductName(rs.getString(5));
            detailTransactionList.get(i).setProductPrice(rs.getInt(6));

            i++;
         }
        return detailTransactionList;
    }
}
