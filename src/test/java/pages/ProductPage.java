package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;


public class ProductPage {

    private final String productName = "Test Product";
    private final String productDescription = "The purpose of this product is merely for testing";
    private final String productPrice = "10.00";
    private final String productStock = "5";

    public void handleProductCreation() {
        fillProductInfo();
        submitProductCreation();
    }

    public void fillProductInfo() {
        fillProductInfo(this.productName, this.productDescription, this.productPrice, this.productStock);
    }

    public void fillProductInfo(final String productName, final String productDescription, final String productPrice, final String productStock) {
        $(By.id("name-es_AR")).sendKeys(productName);

        switchTo().frame($(By.id("cke_1_contents")).find(By.tagName("iframe")));
        $(By.tagName("body")).sendKeys(productDescription);

        switchTo().parentFrame();

        $(By.id("price")).sendKeys(productPrice);
        $(By.id("stock")).sendKeys(productStock);

    }

    private void submitProductCreation() {
        SelenideElement button = $(By.id("product-submit")).find(By.id("submit-button"));
        hasPublishProductBeenClicked(button);
    }

    private boolean hasPublishProductBeenClicked(SelenideElement button) {
        if(button.getValue().equals("Publicar producto")) {
            button.click();
            return hasPublishProductBeenClicked(button);
        } else {
            return true;
        }
    }

    public void removeAllProducts() {
        AdminNavbar adminNavbar = new AdminNavbar();
        adminNavbar.goToMyProducts();
        $(By.xpath("//*[@id=\"product-table\"]/tbody/tr[1]/td/div/label/span/span[2]")).click();
        $(By.id("product-select")).selectOptionByValue("destroy");
    }


    public String getProductName() {
        return productName;
    }

    public void orderByCreatedDescending() {
        $(By.id("sort-order-inline")).selectOptionByValue("created-descending");
    }
}
