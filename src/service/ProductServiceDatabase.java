package service;

import dao.ProductDAO;
import model.ProductModel;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceDatabase implements ProductService {
    ProductDAO productDAO = new ProductDAO();


    @Override
    public ProductModel getProduct(String productId) throws SQLException {
        return productDAO.getProduct(productId);
    }

    @Override
    public List<ProductModel> getProductsByCategory(String categoryId) throws SQLException {
        return productDAO.getProductsByCategory(categoryId);
    }

    @Override
    public List<ProductModel> getCashierProductsByCategory(String categoryId) throws SQLException {
        return productDAO.getCashierProductsByCategory(categoryId);
    }

    @Override
    public String getMaxId(String category) throws SQLException {
        return productDAO.getMaxId(category);
    }

    @Override
    public void addProduct(ProductModel product) throws SQLException {
        productDAO.addProduct(product);
    }

    @Override
    public void setImage(String productId, String imageUrl) throws SQLException {
        productDAO.setImage(productId, imageUrl);
    }

    @Override
    public void deleteProduct(String productId) throws SQLException {
        productDAO.deleteProduct(productId);
    }

    @Override
    public void toggleStatus(String productId) throws SQLException {
        productDAO.toggleStatus(productId);
    }
}
