package pages.product;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.AdminNavbar;

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

    private final String firstProductNameInTable = "#product-table > tbody > tr:nth-child(1) > td:nth-child(3) > p:nth-child(1) > a:nth-child(1)";
    private final String firstProductOriginalPriceInTable = "#product-table > tbody > tr:nth-child(1) > td:nth-child(4) > ul > li > div > div:nth-child(2) > div > div > span";
    private final String firstProductStockInTable = "#product-table > tbody > tr:nth-child(1) > td:nth-child(4) > ul > li > div > div:nth-child(1) > div > div > span";

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

    public String convertCurrencyToString(String currency) {
        String s;
        s = currency.substring(1);
        return s.replace(",", ".");
    }

    public void removeFirstProduct() {
        AdminNavbar adminNavbar = new AdminNavbar();
        adminNavbar.goToMyProducts();
        $(By.cssSelector(firstProductCheckbox)).click();
        $(By.id(productSelectId)).selectOptionByValue("destroy");
    }

    public Product fetchProductFromPage() {
        Product productFromPage = new Product();
        productFromPage.setName($(By.cssSelector(firstProductNameInTable)).getText());
        productFromPage.setPrice($(By.cssSelector(firstProductOriginalPriceInTable)).getText());
        productFromPage.setStock($(By.cssSelector(firstProductStockInTable)).getText());
        return productFromPage;
    }

    public void orderByCreatedDescending() {
        $(By.id(sortOrderId)).selectOptionByValue("created-descending");
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductStock() {
        return productStock;
    }
}
