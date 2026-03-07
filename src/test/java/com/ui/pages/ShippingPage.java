package com.ui.pages;

import com.utils.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShippingPage extends BrowserUtility {

    private static final By ACCEPT_TERMS_CHECKBOX_LOCATOR=By.id("uniform-cgv");
    private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR=By.name("processCarrier");


    public ShippingPage(WebDriver driver) {
        super(driver);
    }

    public PaymentPage goToPaymentPage(){
        clickOn(ACCEPT_TERMS_CHECKBOX_LOCATOR);
        clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
        return new PaymentPage(getDriver());
    }
}
