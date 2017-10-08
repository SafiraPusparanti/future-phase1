package Model;

public class ProductModel {
    private String productId;
    private String name;
    private int price;
    private boolean status;
    private String categoryId;
    private String imageUrl;

    public ProductModel(String productId, String name, int price, boolean status, String categoryId, String imageUrl) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.status = status;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
