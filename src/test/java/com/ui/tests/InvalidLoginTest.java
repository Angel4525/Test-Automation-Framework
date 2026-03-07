package com.ui.tests;

import com.ui.pojo.User;
import com.utils.LoggerUtility;
import org.testng.annotations.Test;

import org.apache.logging.log4j.Logger;

import static org.testng.Assert.assertEquals;

public class InvalidLoginTest extends TestBase{

    Logger logger= LoggerUtility.getLogger(this.getClass());

    private static final String INVALID_EMAIL="incorrectEmail@hotmail.com";
    private static final String INVALID_PASSWORD="abcde1234";

    @Test(description = "Verifies if the correct error message is shown for invalid credentials", groups = {"e2e","smoke"})
    public void invalidLoginTest() {

        //
        assertEquals(homePage.goToSignInPage().doLoginWithInvalidCredentials(INVALID_EMAIL,INVALID_PASSWORD).getErrorMessage(),"Authentication failed.");
    }
}
