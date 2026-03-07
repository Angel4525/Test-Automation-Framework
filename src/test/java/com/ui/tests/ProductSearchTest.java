package com.ui.tests;

import com.ui.pages.MyAccountPage;
import com.ui.pojo.User;
import com.utils.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductSearchTest extends TestBase {

    private MyAccountPage myAccountPage;

    private static final String SEARCH_TERM = "Printed Summer Dress";


    //signing in with valid credentials
    @BeforeMethod(description = "Valid user logs into the application")
    public void setup() {
        myAccountPage = homePage.goToSignInPage().login("fakeeeeemail@hotmail.com", "fakeemail123");
    }

    // Test that verifies searching for a product works correctly
    @Test(description = "Verifies if the user is able to search for a product and correct products are displayed", groups = {"e2e", "smoke", "sanity"})
    public void verifyProductSearchTest() {

        // Search for the product and check if results contain the search term
        boolean actualResult = myAccountPage.searchForProduct(SEARCH_TERM).isSearchTermInProductList(SEARCH_TERM);

        // Verify the result is true
        Assert.assertEquals(actualResult, true);
    }
}
