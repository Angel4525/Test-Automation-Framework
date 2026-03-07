package com.ui.tests;

import com.constants.Size;
import com.ui.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductCheckoutTest extends TestBase{


    private static final String SEARCH_TERM= "Printed Summer Dress";
    private SearchResultPage searchResultPage;

    @BeforeMethod(description = "user logs into application and searches for a product")
    public void setup(){
        searchResultPage=homePage.goToSignInPage().login("fakeeeeemail@hotmail.com", "fakeemail123")
                .searchForProduct(SEARCH_TERM);
    }


    @Test(description = "Verify if logged in user is able to buy a dress", groups = {"e2e","smoke"})
    public void checkoutTest(){
        String result= searchResultPage.clickOnTheProductAtIndex(2).changeSize(Size.L).addToCart().proceedToCheckout()
                .goToConfirmAddressPage().goToShippingPage().goToPaymentPage().makePaymentByWire();

        Assert.assertTrue(result.contains("complete"));
    }
}
