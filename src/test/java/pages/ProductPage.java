package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;


public class ProductPage {

    private final String productName = "Test Product";
    private final String productDescription = "The purpose of this product is merely for testing";
    private final String productPrice = "10.00";
    private final String productStock = "5";

    //Selectors
    private final String productNameField = "name-es_AR";
    private final String descriptionFrameId = "cke_1_contents";
    private final String descriptionBody = "body";
    private final String priceField = "price";
    private final String stockField = "stock";
    private final String productSubmitId = "product-submit";
    private final String submitButtonId = "submit-button";
    private final String firstProductCheckbox = "#product-table > tbody > tr:nth-child(1) > td > div > label > span > span:nth-child(2)";
    private final String productSelectId = "product-select";
    private final String sortOrderId = "sort-order-inline";

    public void handleProductCreation() {
        fillProductInfo();
        submitProductCreation();
    }

    public void fillProductInfo() {
        fillProductInfo(this.productName, this.productDescription, this.productPrice, this.productStock);
    }

    public void fillProductInfo(final String productName, final String productDescription, final String productPrice, final String productStock) {
        $(By.id(productNameField)).sendKeys(productName);

        switchTo().frame($(By.id(descriptionFrameId)).find(By.tagName("iframe")));
        $(By.tagName(descriptionBody)).sendKeys(productDescription);

        switchTo().parentFrame();

        $(By.id(priceField)).sendKeys(productPrice);
        $(By.id(stockField)).sendKeys(productStock);

    }

    private void submitProductCreation() {
        SelenideElement button = $(By.id(productSubmitId)).find(By.id(submitButtonId));
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
        $(By.cssSelector(firstProductCheckbox)).click();
        $(By.id(productSelectId)).selectOptionByValue("destroy");
    }


    public String getProductName() {
        return productName;
    }

    public void orderByCreatedDescending() {
        $(By.id(sortOrderId)).selectOptionByValue("created-descending");
    }
}
