package pages;

import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.By;

public class LoginPage {

    private final String url = "https://www.tiendanube.com/login";
    private final String username = "automation+interview@tiendanube.com";
    private final String pàssword = "interview123";

    public void handleUserLogin() {
        $(By.id("user-mail")).sendKeys(username);
        $(By.id("pass")).sendKeys(pàssword);
        $(By.name("login")).click();

    }

    public String getUrl() {
        return url;
    }

}
