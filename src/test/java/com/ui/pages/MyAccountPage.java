package com.ui.pages;

import com.utils.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class MyAccountPage extends BrowserUtility {

    //Locator
    private static final By USER_NAME_LOCATOR= By.xpath("//a[@title='View my customer account']/span");


    public MyAccountPage(WebDriver driver) {
        super(driver);
    }


    //get user name text
public String getUserName(){
return getVisibleText(USER_NAME_LOCATOR);
}

}
