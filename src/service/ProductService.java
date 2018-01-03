package service;

import model.ProductModel;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    ProductModel getProduct(String productId) throws SQLException;
    List<ProductModel> getProductsByCategory(String categoryId) throws SQLException;
    List<ProductModel> getAvailableProductsByCategory(String categoryId) throws SQLException;
    String getMaxId(String category) throws SQLException;
    void addProduct(ProductModel product) throws SQLException;
    void setImage(String productId, String imageUrl) throws SQLException;
    void deleteProduct(String productId) throws SQLException;
    void toggleStatus(String productId) throws SQLException;
}
