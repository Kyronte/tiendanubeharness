package tests;

import static com.codeborne.selenide.Condition.text;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.AdminNavbar;
import pages.LoginPage;
import pages.ProductPage;
import tests.interfaces.TestInterface;

import static com.codeborne.selenide.Selenide.*;

public class ProductTest implements TestInterface {

    private LoginPage loginPage;
    private AdminNavbar adminNavbar;
    private ProductPage productPage;

    @Before
    public void setUp() {
        this.loginPage = new LoginPage();
        this.adminNavbar = new AdminNavbar();
        this.productPage = new ProductPage();
    }

    @Test
    public void productCanBeCreated() {
        open(loginPage.getUrl());

        loginPage.handleUserLogin();

        adminNavbar.addNewProduct();

        productPage.handleProductCreation();

        adminNavbar.goToMyProducts();

        productPage.orderByCreatedDescending();

        $(By.xpath("//*[@id=\"product-table\"]/tbody/tr/td[3]/p/a")).shouldHave(text(productPage.getProductName()));
    }

    @After
    public void cleanUp() {
        productPage.removeAllProducts();
    }
}