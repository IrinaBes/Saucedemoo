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

    public void addToCart(String goodsName) {
        By addToCart = By.xpath(String.format(ADD_TO_CART_BUTTON_PATTERN, goodsName));
        driver.findElement(addToCart).click();
    }

    @Step("Добавление товара в корзину")
    public ProductsPage addToCart(int index) {
        driver.findElements(By.xpath("//*[text()='Add to cart']")).get(index).click();
        return this;
    }

    @Step("Ожидаем прогрузки карточек товаров")
    public ProductsPage isOpen() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Add to cart']")));
        return this;
    }

    @Step("Открытие корзины")
    public ProductsPage openCart() {
        driver.findElement(By.xpath("//*[@data-test='shopping-cart-link']")).click();
        return this;
    }
}
