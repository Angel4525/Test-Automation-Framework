package com.ui.pages;

import com.constants.Size;
import com.utils.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailPage extends BrowserUtility {

    private static final By SIZE_DROP_DOWN_LOCATOR= By.id("group_1");
    private static final By ADD_TO_CART_BUTTON_LOCATOR= By.name("Submit");
    private static final By PROCEED_TO_CHECKOUT_LOCATOR= By.xpath("//a[@title='Proceed to checkout']");


    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailPage changeSize(Size size){
    selectFromDropDown(SIZE_DROP_DOWN_LOCATOR, size.toString());
    return new ProductDetailPage(getDriver());
    }

    public ProductDetailPage addToCart(){
        clickOn(ADD_TO_CART_BUTTON_LOCATOR);
        return new ProductDetailPage(getDriver());

    }

    public ShoppingCartPage proceedToCheckout(){

        clickOn(PROCEED_TO_CHECKOUT_LOCATOR);

        return new ShoppingCartPage(getDriver());
    }

}
