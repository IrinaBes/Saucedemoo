package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
    private final By title = By.cssSelector("[class='title']");
    private final By title2 = By.xpath("//*[text()='Products']");
    private static final String ADD_TO_CART_BUTTON_PATTERN
            = "//div[text()='%s']//ancestor::div[@class='inventory_item']//button";
    private final By ADD_TO_CART_TEXT = By.xpath("//*[text()='Add to cart']");
    private final By SHOPPING_CART_LINC = By.xpath("//*[@data-test='shopping-cart-link']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверяем название товара")
    public String getTitle() {
        return driver.findElement(title).getText();
    }

    @Step("Проверяем, что отображен заголовок страницы")
    public boolean titleIsDisplayed() {
        return driver.findElement(title2).isDisplayed();
    }

    @Step("Добавление товара в корзину")
    public ProductsPage addToCart(int index) {
        driver.findElements(ADD_TO_CART_TEXT).get(index).click();
        return this;
    }

    @Step("Ожидаем прогрузки карточек товаров")
    public ProductsPage isOpen() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TO_CART_TEXT));
        return this;
    }

    @Step("Открытие корзины")
    public ProductsPage openCart() {
        driver.findElement(SHOPPING_CART_LINC).click();
        return this;
    }
}
