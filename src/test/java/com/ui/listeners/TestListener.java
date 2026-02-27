package com.ui.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase;
import com.utils.BrowserUtility;
import com.utils.ExtentReporterUtility;
import com.utils.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

// This class listens to TestNG test execution events and calls listed methods ad different points in the test cycle
public class TestListener implements ITestListener {

    // Create a logger for this class using our LoggerUtility
// 'this.getClass()' gets the runtime class of the current object (TestListener)
// This allows the logger to know which class is writing logs
    Logger logger= LoggerUtility.getLogger(this.getClass());



//runs once BEFORE any tests in the suite start. Useful for setup logging or initializing resources
    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite Started");
        ExtentReporterUtility.setupSparkReporter("report.html");
    }

    // This method runs RIGHT BEFORE each individual test method starts. It gives us details about the test that is about to run
    @Override
    public void onTestStart(ITestResult result) {

        // Logs the name of the test method being executed
        logger.info(result.getMethod().getMethodName());

        // Logs the description provided in the @Test annotation
        logger.info(result.getMethod().getDescription());

        // Logs the groups the test belongs to (e.g., e2e, sanity)
        // Arrays.toString() converts the groups array into readable text
        logger.info(Arrays.toString(result.getMethod().getGroups()));

        ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
    }

    // This method runs when a test PASSES successfully
    @Override
    public void onTestSuccess(ITestResult result) {
logger.info(result.getMethod().getMethodName()+" PASSED");


ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName()+" PASSED");
    }

    // This method runs when a test FAILS
    @Override
    public void onTestFailure(ITestResult result) {
        logger.error(result.getMethod().getMethodName()+" FAILED");

        // Logs the error message that caused the test to fail
        // getThrowable() contains the exception thrown during the test
        logger.error(result.getThrowable().getMessage());


        ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName()+" FAILED");

// Log the reason why the test failed in the Extent report
        ExtentReporterUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());

// Get the instance of the test class where the failure happened
        Object testClass=result.getInstance();

        // Cast the test class to TestBase and get its BrowserUtility instance
        BrowserUtility browserUtility= ((TestBase)testClass).getInstance();

        // Log info to indicate a screenshot is being captured for the failed test
        logger.info("Capturing screenshot for the failed tests");

        // Take a screenshot and save it with the test method name
        String screenshotPath= browserUtility.takeScreenShot(result.getMethod().getMethodName());

        // Log info to indicate the screenshot will be attached to the HTML report
        logger.info("Attaching the screenshot to the HTML FILE");

        // Attach the captured screenshot to the Extent HTML report
        ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenshotPath);
    }

    // This method runs when a test is SKIPPED
    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn(result.getMethod().getMethodName()+" SKIPPED");

        ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName()+" SKIPPED");
    }

    // This method runs once AFTER all tests in the suite finish
    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite Completed");

        ExtentReporterUtility.flushReport();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.warn(result.getMethod().getMethodName() +
                " FAILED but within success percentage");

        ExtentReporterUtility.getTest()
                .log(Status.WARNING,
                        result.getMethod().getMethodName() +
                                " FAILED but within success percentage");
    }
}
