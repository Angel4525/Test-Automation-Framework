package com.ui.tests;

import static com.constants.Browser.*;

import com.ui.listeners.TestListener;
import com.ui.pages.HomePage;
import com.ui.pages.MyAccountPage;
import static org.testng.Assert.*;

import com.ui.pojo.User;
import com.utils.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//If multiple listeners are attached use the following format-> @Listeners({com.ui.listeners.TestListener.class},{...})
@Listeners(com.ui.listeners.TestListener.class)
public class LoginTest extends TestBase{



    //describe what the test does
    //dataProvider + dataProviderClass tell TestNG to pull test data from an external class
    @Test(description = "Verifies if the valid user is able to login to application", groups = {"e2e","sanity"},
    dataProviderClass = com.ui.dataProviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
    public void loginTest(User user) {

        // Uses the email and password from the User object
        // Logs in and retrieves the displayed username
        // Assert username
        assertEquals(homePage.goToSignInPage().login(user.getEmailAddress(), user.getPassword())
                .getUserName(),"J aguirr");
    }

    @Test(description = "Verifies if the valid user is able to login to application", groups = {"e2e","sanity"},
            dataProviderClass = com.ui.dataProviders.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider",
    retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
    public void loginCSVTest(User user) {

        assertEquals(homePage.goToSignInPage().login(user.getEmailAddress(), user.getPassword())
                .getUserName(),"J aguirr");
    }

    @Test(description = "Verifies if the valid user is able to login to application", groups = {"e2e","sanity"},
            dataProviderClass = com.ui.dataProviders.LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider",
    retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
    public void loginExcelTest(User user) {

// Uses the User data from Excel to log in
        // Then verifies the logged-in username matches the expected value
        assertEquals(homePage.goToSignInPage().login(user.getEmailAddress(), user.getPassword())
                .getUserName(),"J aguirr");

    }
}
