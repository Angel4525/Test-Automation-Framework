package com.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LambdaTestUtility {

    // LambdaTest hub URL for remote Selenium sessions
    public static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";

    // Thread-safe storage for WebDriver instances
    private static ThreadLocal <WebDriver> driverLocal= new ThreadLocal<>();

    // Thread-safe storage for browser capabilities
    private static ThreadLocal<DesiredCapabilities> capabilitiesLocal= new ThreadLocal<>();


    // Initialize a LambdaTest session for given browser and test
    public static WebDriver initializeLambdaTestSession(String browser, String testName){


        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set browser type
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", "latest");
        Map<String, Object> ltOptions = new HashMap();
        ltOptions.put("user", "angelfg");
        ltOptions.put("accessKey", "LT_sc5GKLxZ7rquAOlBgj0C73xcoSOFxyHpSlzvGcpLWKISxve");
        ltOptions.put("build", "Selenium 4");

        // Test name
        ltOptions.put("name", testName);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "latest");
        capabilities.setCapability("LT:Options", ltOptions);

        capabilitiesLocal.set(capabilities);

        WebDriver driver= null;
        try {

            // Connect to LambdaTest remote WebDriver
            driver = new RemoteWebDriver(new URL(HUB_URL), capabilitiesLocal.get());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        // Store driver in thread-safe variable
        driverLocal.set(driver);

        return driverLocal.get();
    }

    // Close LambdaTest session
    public static void quitSession(){
        if (driverLocal.get()!= null){
            driverLocal.get().quit();
        }}}
