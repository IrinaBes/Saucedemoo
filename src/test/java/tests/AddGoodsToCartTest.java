package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;
import static user.UserFactory.withAdminPermission;

public class AddGoodsToCartTest extends BaseTest {
    @Epic("Модуль логина интернет-магазина")
    @Feature("Юридические лица")
    @Story("STG")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Savinova Irina iri311083@gmail.com")
    @Test(description = "Проверяем, что товары добавлены в корзину")
    public void checkGoodsInCart() {
        //productsPage.addToCart("Sauce Labs Backpack");
        LoginPage
                .open()
                .Login(withAdminPermission());
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
}
