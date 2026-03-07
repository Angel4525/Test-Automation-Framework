package com.ui.tests;

import com.ui.pages.AddressPage;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.AddressPOJO;
import com.utils.FakeAddressUtility;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// This is a TestNG test that verifies a user can add a new address for the first time
public class AddNewFirstAddressTest extends TestBase{

   private MyAccountPage myAccountPage;
   private AddressPage addressPage;
   private AddressPOJO address;

    //signing in with valid credentials
    @BeforeMethod(description = "Valid First Time user logs into the application")
    public void setup() {
        myAccountPage = homePage.goToSignInPage().login("fakeeeeemail@hotmail.com", "fakeemail123");

        address= FakeAddressUtility.getFakeAddress();
    }

    @Test
    public void addNewAddress() {

        // Navigate to the address page and save the address
        String newAddress = myAccountPage.goToAddressPage().saveAddress(address);

        // Verify the address alias matches what we created
        Assert.assertEquals(newAddress, address.getAddressAlias().toUpperCase());
    }
}
