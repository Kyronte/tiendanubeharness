package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.AdminNavbar;
import pages.LoginPage;
import pages.helper.ProductHelper;
import pages.product.ProductPage;
import tests.interfaces.TestInterface;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class ProductTest implements TestInterface {

    private LoginPage loginPage;
    private AdminNavbar adminNavbar;
    private ProductPage productPage;

    @Before
    public void setUp() {
        this.loginPage = new LoginPage();
        this.adminNavbar = new AdminNavbar();
        this.productPage = new ProductPage();
        //init product hlper?
    }

    @Test
    public void productCanBeCreated() {
        open(loginPage.getUrl());

        loginPage.handleUserLogin();

        adminNavbar.addNewProduct();

        productPage.handleProductCreation();

        adminNavbar.goToMyProducts();

        productPage.orderByCreatedDescending();

        assertEquals(productPage.getProduct().getName(), productPage.fetchProductFromPage().getName());
        assertEquals(productPage.getProduct().getPrice(), ProductHelper.convertCurrencyToString(productPage.fetchProductFromPage().getPrice()));
        assertEquals(productPage.getProduct().getStock(), productPage.fetchProductFromPage().getStock());


    }

    @After
    public void cleanUp() {
        productPage.removeFirstProduct();
    }
}