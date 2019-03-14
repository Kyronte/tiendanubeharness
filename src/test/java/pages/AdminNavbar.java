package pages;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class AdminNavbar {

    //Selectors
    private final String accordionMenuSelector = "accordion-menu";
    private final String productMenuSelector = "li[data-code='products']";
    private final String addNewProductLinkSelector = "#item-3 > ul > li:nth-child(2) > a";
    private final String myProductsLinkSelector = "#item-3 > ul > li:nth-child(1) > a";

    public void addNewProduct() {
        $(By.id(accordionMenuSelector)).find(By.cssSelector(productMenuSelector)).click();
        $(By.cssSelector(addNewProductLinkSelector)).click();
    }

    public void goToMyProducts() {
        $(By.cssSelector(myProductsLinkSelector)).click();
    }

}
