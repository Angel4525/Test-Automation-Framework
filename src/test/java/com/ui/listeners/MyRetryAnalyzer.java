package com.ui.listeners;

import com.constants.Environment;
import com.utils.JSONUtility;
import com.utils.PropertiesUtility;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//IRetryAnalyzer is an interface in TestNG
//will only be called when test fails
public class MyRetryAnalyzer implements IRetryAnalyzer {

    //reading from properties file
    private static final int MAX_NUMBER_OF_ATTEMPTS= Integer.parseInt(PropertiesUtility.readProperty(Environment.DEV,"MAX_NUMBER_OF_ATTEMPTS"));

    //reading from Json File
    //private static final int MAX_NUMBER_OF_ATTEMPTS= JSONUtility.readJSON(Environment.QA).getMAX_NUMBER_OF_ATTEMPTS();

    private static int currentAttempt=1;

    // ITestResult contains information about the failed test
    // Called automatically after a test failure
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (currentAttempt<=MAX_NUMBER_OF_ATTEMPTS){
            currentAttempt++;
            return true;
        }
        return false;
    }
}
