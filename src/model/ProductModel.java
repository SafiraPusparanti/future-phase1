package model;

public class ProductModel {
    private String productId;
    private String name;
    private float price;
    private boolean isAvailable;
    private String categoryId;
    private String imageUrl;
    private String currencyWrapper;

    public ProductModel(String productId, String name, float price, boolean isAvailable, String categoryId, String imageUrl) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean status) {
        this.isAvailable = isAvailable;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getCurrencyWrapper() {
        return currencyWrapper;
    }

    public void setCurrencyWrapper(String currencyWrapper) {
        this.currencyWrapper = currencyWrapper;
    }
}
