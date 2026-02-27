package com.ui.tests;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utils.BrowserUtility;
import com.utils.LambdaTestUtility;
import com.utils.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.sql.DriverManager;

import static com.constants.Browser.*;


import static com.constants.Browser.CHROME;

public class TestBase {

   protected HomePage homePage;

    // Logger instance for this class (used to print logs)
    Logger logger= LoggerUtility.getLogger(this.getClass());

    // Flag to check if the test should run on LambdaTest (cloud browser platform)
    private boolean isLambdaTest;


    // @Parameters tells TestNG: "These values will come from the XML file (testng.xml)"
    @Parameters({"browser","isLambdaTest","isHeadless"})
    @BeforeMethod(description = "Load home page")
    public void setup(

         // Optional means Default to "chrome" if XML doesn't provide
            @Optional("chrome") String browser,
            @Optional("true") boolean isLambdaTest,
            @Optional("true") boolean isHeadless,
            ITestResult result){

        this.isLambdaTest=isLambdaTest;
        WebDriver lambdaDriver;

        // If running tests on LambdaTest (cloud browser platform)
        if (isLambdaTest){

            // Initialize a cloud browser session with the given browser and test name
            lambdaDriver=LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());

            // Create homepage object using the LambdaTest browser
            homePage=new HomePage(lambdaDriver);
        }

        // Else- If running tests locally
        else {
            logger.info("Load the Homepage of the website");

            // Open homepage on a local browser (Chrome, Firefox, Edge, etc.)
            // 'isHeadless' means the browser runs without windows opening
            homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
        }
    }


    //parent of Homepage Class is browserUtility
public BrowserUtility getInstance(){

    // Allows child test classes to access browser actions
    return homePage;
}


// Runs after each test to clean up
@AfterMethod(description = "Tear down the browser")
    public void tearDown() {

    if (isLambdaTest) {
        // Close the remote LambdaTest session
        LambdaTestUtility.quitSession();
    } else {
        // Close the local browser
        homePage.quit();
    }
}
}
