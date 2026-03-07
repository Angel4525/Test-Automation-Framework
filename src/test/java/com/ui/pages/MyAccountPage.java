package com.ui.pages;

import com.utils.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public final class MyAccountPage extends BrowserUtility {

    private static final By USER_NAME_LOCATOR= By.xpath("//a[@title='View my customer account']/span");
    private static final By SEARCH_TEXT_BOX_LOCATOR= By.id("search_query_top");
    private static final By ADD_NEW_ADDRESS_LINK_LOCATOR= By.xpath("//a[@title=\"Add my first address\"]");

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }


    //get user name text
public String getUserName(){
return getVisibleText(USER_NAME_LOCATOR);
}

    // Method used to search for a product in the search bar
public SearchResultPage searchForProduct(String productName){

    // Type the product name into the search box
    enterText(SEARCH_TEXT_BOX_LOCATOR,productName);

//click Enter to search product
enterSpecialKey(SEARCH_TEXT_BOX_LOCATOR, Keys.ENTER);
SearchResultPage searchResultPage= new SearchResultPage(getDriver());
return searchResultPage;
}

public AddressPage goToAddressPage(){
        clickOn(ADD_NEW_ADDRESS_LINK_LOCATOR);
        AddressPage addressPage= new AddressPage(getDriver());
        return addressPage;
}


}
