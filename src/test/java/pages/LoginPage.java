package pages;

import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.By;

public class LoginPage {

    private final String url = "https://www.tiendanube.com/login";
    private final String username = "automation+interview@tiendanube.com";
    private final String pàssword = "interview123";

    //Selectors
    private final String emailFieldSelector = "user-mail";
    private final String passwordFieldSelector = "pass";
    private final String loginButtonSelector = "login";

    public void handleUserLogin() {
        $(By.id(emailFieldSelector)).sendKeys(username);
        $(By.id(passwordFieldSelector)).sendKeys(pàssword);
        $(By.name(loginButtonSelector)).click();

    }

    public String getUrl() {
        return url;
    }

}
