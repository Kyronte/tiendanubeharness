package pages;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class AdminNavbar {


    public void addNewProduct() {
        $(By.id("accordion-menu")).find(By.cssSelector("li[data-code='products']")).click();
        $(By.xpath("//*[@id=\"item-3\"]/ul/li[2]/a")).click();
    }

    public void goToMyProducts() {
        $(By.xpath("//*[@id=\"item-3\"]/ul/li[1]/a")).click();
    }

}
