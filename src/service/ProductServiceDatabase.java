package service;

import dao.ProductDAO;
import model.ProductModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceDatabase implements ProductService {
    ProductDAO productDAO = new ProductDAO();

    @Override
    public ProductModel getProduct(String productId) throws SQLException {
        ProductModel product = null;

        try {
            product = productDAO.getProduct(productId);
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }
        return product;

    }

    @Override
    public List<ProductModel> getProductsByCategory(String categoryId) throws SQLException {
        List<ProductModel> products = new ArrayList<ProductModel>();

        try {
            products = productDAO.getProductsByCategory(categoryId);
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<ProductModel> getAvailableProductsByCategory(String categoryId) throws SQLException {
        List<ProductModel> products = new ArrayList<ProductModel>();

        try {
            products = productDAO.getAvailableProductsByCategory(categoryId);
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }

        return products;

    }

    @Override
    public String getMaxId(String category) throws SQLException {
        String maxId = null;

        try {
            maxId = productDAO.getMaxId(category);
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }

        return maxId;

    }

    @Override
    public void addProduct(ProductModel product) throws SQLException {
        try {
            productDAO.addProduct(product);
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void setImage(String productId, String imageUrl) throws SQLException {
        try {
            productDAO.setImage(productId, imageUrl);
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(String productId) throws SQLException {
        try {
            productDAO.deleteProduct(productId);
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void toggleStatus(String productId) throws SQLException {
        try {
            productDAO.toggleStatus(productId);
        } catch (SQLException e) {
            System.out.println("Failure on selecting data from tables : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
