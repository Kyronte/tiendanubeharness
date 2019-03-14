package pages.product;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.AdminNavbar;
import pages.helper.ProductHelper;

import static com.codeborne.selenide.Selenide.*;


public class ProductPage {

    private ProductProvider productProvider;
    private Product product;

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

    public ProductPage() {
        productProvider = new ProductProvider();
    }

    public void handleProductCreation() {
        fillProductInfo();
        submitProductCreation();
    }

    private void fillProductInfo() {
        product = productProvider.createProduct();
        fillProductInfo(product);
    }

    public void fillProductInfo(Product p) {
        $(By.id(productNameField)).sendKeys(p.getName());

        switchTo().frame($(By.id(descriptionFrameId)).find(By.tagName("iframe")));
        $(By.tagName(descriptionBody)).sendKeys(p.getDescription());

        switchTo().parentFrame();

        $(By.id(priceField)).sendKeys(p.getPrice());
        $(By.id(stockField)).sendKeys(p.getStock());

    }

    private void submitProductCreation() {
        SelenideElement button = $(By.id(productSubmitId)).find(By.id(submitButtonId));
        ProductHelper.hasPublishProductBeenClicked(button);
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

    public Product getProduct() {
        return product;
    }
}
