package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.UserFactory;

import static enums.DepartmentNaming.PRODUCTS;
import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertFalse;

public class LoginTest extends BaseTest {
    @Epic("Модуль логина интернет-магазина")
    @Feature("Юридические лица")
    @Story("STG")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Savinova Irina iri311083@gmail.com")
    @Test(description = "Проверка авторизации")
    public void correctLogin() {
        LoginPage
                .open()
                .Login(UserFactory.withAdminPermission());
        assertTrue(productsPage.titleIsDisplayed());
        assertEquals(productsPage.getTitle(), PRODUCTS.getDisplayName());
        productsPage
                .isOpen()
                .addToCart(0)
                .addToCart(2)
                .addToCart(3)
                .openCart();
        assertTrue(cartPage.getProductsNames().contains("Sauce Labs Backpack"));
        assertEquals(cartPage.getProductsNames().size(), 3);
        assertFalse(cartPage.getProductsNames().isEmpty());
    }

    @DataProvider(name = "incorrectLoginDate")
    public Object[][] loginData() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"}
        };
    }

    @Test(dataProvider = "incorrectLoginDate")
    public void incorrectLogin(String user, String pass, String errorMsg) {
        LoginPage
                .open()
                .fillLoginInput(user)
                .fillPasswordInput(pass)
                .clickSubmitBtn();
        assertEquals(LoginPage.getErrorMsg(), errorMsg);
    }
}
