package com.ui.pages;

import com.constants.Browser;
import static com.constants.Environment.*;

import com.constants.Environment;
import com.utils.BrowserUtility;
import static com.utils.PropertiesUtility.*;

import com.utils.JSONUtility;
import com.utils.PropertiesUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//always mark child classes as final
public final class  HomePage extends BrowserUtility {

    //sign in button locator on webpage
    private static final By SIGN_IN_LOCATOR= By.xpath("//a[contains(text(),'Sign in')]");

    public HomePage(Browser browserName, boolean isHeadless) {
        super(browserName, isHeadless);
        goToWebsite(JSONUtility.readJSON(QA).getUrl());
    }

    //Create another constructor that takes Driver
    public HomePage(WebDriver driver) {
        super(driver);
        goToWebsite(JSONUtility.readJSON(QA).getUrl());
    }

//From homepage to Sign in Page. Create sign in page object
    public SignInPage goToSignInPage(){
clickOn(SIGN_IN_LOCATOR);
SignInPage signInPage= new SignInPage(getDriver());
return signInPage;
}
}
