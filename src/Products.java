public class Products {
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Products(String productName, double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    private String productName;
    private double productPrice;
}
