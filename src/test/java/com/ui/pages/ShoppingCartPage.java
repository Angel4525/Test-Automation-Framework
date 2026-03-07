package com.ui.pages;

import com.utils.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage extends BrowserUtility {

    //must be written like this due to product detail having same a[@title='Proceed to checkout'
    //selenium must diferentiate the two
    private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR=
            By.xpath("//p[contains(@class, 'cart_navigation')]/a[@title='Proceed to checkout']");


    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }


    public ConfirmAddressPage goToConfirmAddressPage(){
        clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
        return new ConfirmAddressPage(getDriver());
    }
}
