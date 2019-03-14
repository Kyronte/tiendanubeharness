package pages.product;

public class ProductProvider {

    private final String productName = "Test Product";
    private final String productDescription = "The purpose of this product is merely for testing";
    private final String productPrice = "10.00";
    private final String productStock = "5";

    public Product createProduct() {
        return new Product(productName, productDescription, productPrice, productStock);
    }
}
