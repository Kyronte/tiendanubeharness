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

        $(By.cssSelector("#product-table > tbody > tr:nth-child(1) > td:nth-child(3) > p:nth-child(1) > a:nth-child(1)")).shouldHave(text(productPage.getProductName()));


    }

    @After
    public void cleanUp() {
        productPage.removeAllProducts();
    }
}