package dao;

import model.ProductModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/safirapusparanti?currentSchema=kanmakan";
    public static final String username = "postgres";
    public static final String password = "dbfira";

    Connection conn;

    public ProductDAO() {
        try {
            Class.forName(database);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public List<ProductModel> getProductsByCategory(String categoryId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT product_id, name, price, is_available, category_id, image_url FROM product WHERE category_id = ?");
        ps.setString(1, categoryId);
        ResultSet rs = ps.executeQuery();
        List<ProductModel> products = new ArrayList<ProductModel>();
        while (rs.next()) {
            products.add(new ProductModel(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getBoolean(4), rs.getString(5), rs.getString(6)));
        }
        return products;
    }

    public ProductModel getProduct(String productId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM product where product_id = ?");
        ps.setString(1, productId);

        ResultSet rs = ps.executeQuery();

        ProductModel output;
        if (rs.next()) {
            output = new ProductModel(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getBoolean(4), rs.getString(5), rs.getString(6));
        } else {
            output = null;
        }
        return output;
    }

    public String getMaxId(String category) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT product_id FROM product WHERE category_id = ? ORDER BY product_id DESC LIMIT 1");
        ps.setString(1, category);
        System.out.println(ps);

        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString(1);
    }

    public void addProduct(ProductModel product) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO product (product_id, name, price, is_available, category_id, image_url) VALUES (?,?,?,?,?,?)");
        ps.setString(1, product.getProductId());
        ps.setString(2, product.getName());
        ps.setFloat(3, product.getPrice());
        ps.setBoolean(4, product.getIsAvailable());
        ps.setString(5, product.getCategoryId());
        ps.setString(6, product.getImageUrl());
        System.out.println(ps);
        ps.executeUpdate();
    }

    public void deleteProduct(String productId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM product where product_id = ?");
        ps.setString(1, productId);
        ps.executeUpdate();
    }

    public void setImage(String productId, String imageUrl) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE product SET image_url = ? where product_id = ?");
        ps.setString(1, imageUrl);
        ps.setString(2, productId);
        ps.executeUpdate();
    }

    public void toggleStatus(String productId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE product SET is_available = NOT is_available where product_id = ?");
        ps.setString(1, productId);
        ps.executeUpdate();
    }
}
