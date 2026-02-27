package com.ui.pages;

import com.utils.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class SignInPage extends BrowserUtility {


    //Locators:
   private static final By EMAIL_TEXT_BOX_LOCATOR= By.id("email");
    private static final By PASSWORD_TEXT_BOX_LOCATOR= By.id("passwd");
    private static final By SUBMIT_BUTTON_LOCATOR= By.id("SubmitLogin");


    public SignInPage(WebDriver driver) {
        super(driver);
    }

//From Sign in Page to my account page. Create MyAccountPage object
    public MyAccountPage login(String email,String password){
        enterText(EMAIL_TEXT_BOX_LOCATOR, email);
        enterText(PASSWORD_TEXT_BOX_LOCATOR, password);
        clickOn(SUBMIT_BUTTON_LOCATOR);
        MyAccountPage myAccountPage= new MyAccountPage(getDriver());
        return myAccountPage;
    }





    /*              Delete Code Above
    //Locators:
   private static final By EMAIL_TEXT_BOX_LOCATOR= By.id("email");
    private static final By PASSWORD_TEXT_BOX_LOCATOR= By.id("passwd");
    private static final By SUBMIT_BUTTON_LOCATOR= By.id("SubmitLogin");


    public SignInPage(WebDriver driver) {
        super(driver);
    }

//From Sign in Page to my account page. Create MyAccountPage object
    public MyAccountPage login(String email,String password){
        enterText(EMAIL_TEXT_BOX_LOCATOR, email);
        enterText(PASSWORD_TEXT_BOX_LOCATOR, password);
        clickOn(SUBMIT_BUTTON_LOCATOR);
        MyAccountPage myAccountPage= new MyAccountPage(getDriver());
        return myAccountPage;
    }

     */
}
