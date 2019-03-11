package pages;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class AdminNavbar {


    public void addNewProduct() {
        $(By.id("accordion-menu")).find(By.cssSelector("li[data-code='products']")).click();
        $(By.cssSelector("#item-3 > ul > li:nth-child(2) > a")).click();
    }

    public void goToMyProducts() {
        $(By.cssSelector("#item-3 > ul > li:nth-child(1) > a")).click();
    }

}
