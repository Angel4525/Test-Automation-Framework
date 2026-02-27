package com.utils;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//parent classes always marked as abstract
public abstract class BrowserUtility {

// Using ThreadLocal to store the WebDriver instance
    // This ensures that each thread (like in parallel tests) gets its own separate WebDriver
    private static ThreadLocal<WebDriver> driver= new ThreadLocal<>();

    public BrowserUtility(WebDriver driver) {
        this.driver.set(driver);
    }

    //select browser
    public BrowserUtility(Browser browserName) {
        if(browserName==Browser.CHROME){
             driver.set(new ChromeDriver());
        }
        else if(browserName== Browser.EDGE){
            driver.set(new EdgeDriver());
        }
        else if (browserName==Browser.FIREFOX) {
            driver.set(new FirefoxDriver());
        }
    }

    //select browser
    // Headless mode means the browser runs in the background without opening a UI window
    public BrowserUtility(Browser browserName, boolean isHeadless) {
        if (browserName == Browser.CHROME) {

            // If headless mode is enabled
            if (isHeadless) {
                // Create ChromeOptions to customize browser behavior
                ChromeOptions options = new ChromeOptions();

                // Run Chrome without opening a visible browser window
                options.addArguments("--headless=old");

                // Set a screen size so elements render correctly in headless mode
                options.addArguments("--window-size=1920,1080");

                // Launch Chrome with the specified options and store it in ThreadLocal
                driver.set(new ChromeDriver(options));
            } else {
                driver.set(new ChromeDriver());
            }
        } else if (browserName == Browser.EDGE) {
            if (isHeadless) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=old");

                // Disable the Graphics Processing Unit (GPU) aka visible browser window
                options.addArguments("disable-gpu");
                driver.set(new EdgeDriver(options));
            } else {
                driver.set(new EdgeDriver());
            }
        } else if (browserName == Browser.FIREFOX) {
            if (isHeadless) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=old");
                driver.set(new FirefoxDriver(options));
            } else {
                driver.set(new FirefoxDriver());
            }
        }
    }

//call driver
    public WebDriver getDriver() {
        return driver.get();
    }

    //get for website address
    public void goToWebsite(String url){
driver.get().get(url);
    }

    //maximize window
    public void maximizeWindow(){
        driver.get().manage().window().maximize();
    }

    //function for clicking
    public void clickOn(By locator){
        WebElement element=driver.get().findElement(locator);
        element.click();
    }

    //function for inputting text
    public void enterText(By locator, String text){
        WebElement emailTextBoxWebElement= driver.get().findElement(locator);
        emailTextBoxWebElement.sendKeys(text);
    }

    //function for getting text visible on website
public String getVisibleText(By locator){
        WebElement element= driver.get().findElement(locator);
        return element.getText();
}

    // Function to capture a screenshot and save it to disk
   public String takeScreenShot(String name){

       // Convert driver into TakesScreenshot interface
       TakesScreenshot screenshot= (TakesScreenshot) driver.get();

       // Capture screenshot as a file
       File screenshotData= screenshot.getScreenshotAs(OutputType.FILE);

       // Get current date and time
       Date date= new Date();

       // Format time to avoid invalid filename characters
       SimpleDateFormat format= new SimpleDateFormat("HH-mm-ss");
       String timeStamp= format.format(date);

       // Build the file path for the screenshot
       String path= System.getProperty("user.dir")+"/screenshots/"+name+" - "+timeStamp+".png";

       // Create a file at the given path
 File screenshotFile= new File(path);

       try {
           // Save the screenshot file to disk
           FileUtils.copyFile(screenshotData, screenshotFile);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

       // Return the screenshot path for reporting/logging
       return path;
   }

    // Quit the browser and clean up the ThreadLocal driver
    public void quit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // important for parallel execution
        }
    }

}
