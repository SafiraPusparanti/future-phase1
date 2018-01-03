package dao;

import connection.ConnectDB;
import model.ProductModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    ConnectDB connDB;

    public ProductDAO() {
        this.connDB = new ConnectDB();
    }

    public List<ProductModel> getProductsByCategory(String categoryId) throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        List<ProductModel> products = new ArrayList<ProductModel>();

        ps = conn.prepareStatement("SELECT product_id, name, price, is_available, category_id, image_url FROM product WHERE category_id = ?");
        ps.setString(1, categoryId);
        rs = ps.executeQuery();
        while (rs.next()) {
            products.add(new ProductModel(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getBoolean(4), rs.getString(5), rs.getString(6)));
        }

        rs.close();
        ps.close();
        conn.close();

        return products;
    }

    public List<ProductModel> getAvailableProductsByCategory(String categoryId) throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ProductModel> products = new ArrayList<ProductModel>();


        ps = conn.prepareStatement("SELECT product_id, name, price, is_available, category_id, image_url FROM product WHERE category_id = ? AND is_available = TRUE");
        ps.setString(1, categoryId);
        rs = ps.executeQuery();

        while (rs.next()) {
            products.add(new ProductModel(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getBoolean(4), rs.getString(5), rs.getString(6)));
        }

        rs.close();
        ps.close();
        conn.close();

        return products;
    }

    public ProductModel getProduct(String productId) throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM product where product_id = ?");
        ps.setString(1, productId);
        ResultSet rs = ps.executeQuery();
        ProductModel product;

        if (rs.next()) {
            product = new ProductModel(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getBoolean(4), rs.getString(5), rs.getString(6));
        } else {
            product = null;
        }

        rs.close();
        ps.close();
        conn.close();

        return product;
    }

    public String getMaxId(String category) throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT product_id FROM product WHERE category_id = ? ORDER BY product_id DESC LIMIT 1");
        ps.setString(1, category);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String maxId = rs.getString(1);

        rs.close();
        ps.close();
        conn.close();

        return maxId;
    }

    public void addProduct(ProductModel product) throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO product (product_id, name, price, is_available, category_id, image_url) VALUES (?,?,?,?,?,?)");
        ps.setString(1, product.getProductId());
        ps.setString(2, product.getName());
        ps.setFloat(3, product.getPrice());
        ps.setBoolean(4, product.getIsAvailable());
        ps.setString(5, product.getCategoryId());
        ps.setString(6, product.getImageUrl());
        System.out.println(ps);
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public void deleteProduct(String productId) throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM product where product_id = ?");
        ps.setString(1, productId);
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public void setImage(String productId, String imageUrl) throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE product SET image_url = ? where product_id = ?");
        ps.setString(1, imageUrl);
        ps.setString(2, productId);
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public void toggleStatus(String productId) throws SQLException {
        Connection conn = connDB.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE product SET is_available = NOT is_available where product_id = ?");
        ps.setString(1, productId);
        ps.executeUpdate();

        ps.close();
        conn.close();
    }
}
