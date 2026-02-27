package com.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// This class helps create and manage HTML test reports using ExtentReports library
public class ExtentReporterUtility {

    // Single instance of ExtentReports used across the application
    private  static ExtentReports extentReports;

    // ThreadLocal ensures that each test running in parallel has its own ExtentTest instance
    private static ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();


    // Sets up the ExtentReports with a SparkReporter which defines how the report will look
    public static void setupSparkReporter(String reportName) {

        // Create a SparkReporter that will generate the report in the given file path
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + reportName);

    extentReports = new ExtentReports();

        // Attach the SparkReporter to ExtentReports so it knows where and how to generate the report
        extentReports.attachReporter(extentSparkReporter);
}

    // Creates a new test in the report with the given name
public static void createExtentTest(String testName){
    ExtentTest test= extentReports.createTest(testName);

    // Store this test in ThreadLocal so that parallel tests don't overwrite each other
    extentTest.set(test);
}

    // Returns the current test instance for logging steps or info
public static ExtentTest getTest(){
    return extentTest.get();
}

    //dumps data into reports.html file, finalizing the report
public static void flushReport(){
    extentReports.flush();
}

}
