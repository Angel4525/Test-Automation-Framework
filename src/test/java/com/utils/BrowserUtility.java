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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//parent classes always marked as abstract
public abstract class BrowserUtility {

    // Using ThreadLocal to store the WebDriver instance
    // This ensures that each thread (like in parallel tests) gets its own separate WebDriver
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriverWait wait;

    public BrowserUtility(WebDriver driver) {
        this.driver.set(driver);
        wait=new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    //select browser
    public BrowserUtility(Browser browserName) {
        if (browserName == Browser.CHROME) {
            driver.set(new ChromeDriver());
            wait=new WebDriverWait(driver.get(),Duration.ofSeconds(10));

        } else if (browserName == Browser.EDGE) {
            driver.set(new EdgeDriver());
            wait=new WebDriverWait(driver.get(),Duration.ofSeconds(10));

        } else if (browserName == Browser.FIREFOX) {
            driver.set(new FirefoxDriver());
            wait=new WebDriverWait(driver.get(),Duration.ofSeconds(10));

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
                wait=new WebDriverWait(driver.get(),Duration.ofSeconds(10));

            } else {
                driver.set(new ChromeDriver());
                wait=new WebDriverWait(driver.get(),Duration.ofSeconds(10));

            }
        } else if (browserName == Browser.EDGE) {
            if (isHeadless) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=old");

                // Disable the Graphics Processing Unit (GPU) aka visible browser window
                options.addArguments("disable-gpu");
                driver.set(new EdgeDriver(options));
                wait=new WebDriverWait(driver.get(),Duration.ofSeconds(10));

            } else {
                driver.set(new EdgeDriver());
                wait=new WebDriverWait(driver.get(),Duration.ofSeconds(10));

            }
        } else if (browserName == Browser.FIREFOX) {
            if (isHeadless) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=old");
                driver.set(new FirefoxDriver(options));
                wait=new WebDriverWait(driver.get(),Duration.ofSeconds(10));

            } else {
                driver.set(new FirefoxDriver());
                wait=new WebDriverWait(driver.get(),Duration.ofSeconds(10));

            }
        }
    }

    //call driver
    public WebDriver getDriver() {
        return driver.get();
    }

    //get for website address
    public void goToWebsite(String url) {
        driver.get().get(url);
    }

    //maximize window
    public void maximizeWindow() {
        driver.get().manage().window().maximize();
    }

    //function for clicking
    public void clickOn(By locator) {

      WebElement element=  wait.until(ExpectedConditions.elementToBeClickable(locator));
    element.click();
    }

    //function for inputting text
    public void enterText(By locator, String text) {
        WebElement element=  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(text);
    }

    // Function for getting visible text from an element using a locator
    public String getVisibleText(By locator) {
        WebElement element = driver.get().findElement(locator);

        return element.getText();
    }

    // Function for getting visible text using an already existing WebElement
    // Example: If a button says "Sign In", this will return "Sign In".
    public String getVisibleText(WebElement element) {
        return element.getText();
    }

    // Function to capture a screenshot and save it to disk
    public String takeScreenShot(String name) {

        // Convert driver into TakesScreenshot interface
        TakesScreenshot screenshot = (TakesScreenshot) driver.get();

        // Capture screenshot as a file
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);

        // Get current date and time
        Date date = new Date();

        // Format time to avoid invalid filename characters
        SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
        String timeStamp = format.format(date);

        // Build the file path for the screenshot
        String path = "./screenshots/" + name + " - " + timeStamp + ".png";

        // Create a file at the given path
        File screenshotFile = new File(path);

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

    // This method sends a special keyboard key to an element.
    // Example: pressing ENTER in a search box.
    public void enterSpecialKey(By locator, Keys keyToEnter) {
        WebElement element=  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(keyToEnter);
    }

    // This method gets the visible text from MULTIPLE elements on a page.
    // Example: getting all product names from a search results page.
    public List<String> getAllVisibleText(By locator) {

        // Find all elements that match the locator
        List<WebElement> elementList = driver.get().findElements(locator);

        // Create a list to store the text from each element
        List<String> visibleTextList = new ArrayList<>();

        for (WebElement element : elementList) {
            visibleTextList.add(getVisibleText(element));
        }
        return visibleTextList;
    }

    public void clearText(By locator) {

        WebElement element=  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        element.clear();
    }


    // Waits until elements become visible on the page
// This helps prevent automation failures caused by slow loading elements
   public void waitForVisibilityOfElements(By locator) {

       wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }


    // Selects an option from a dropdown menu
    public void selectFromDropDown(By dropDownLocator, String optionToSelect) {

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(dropDownLocator));

        try {
            // --- APPROACH A: THE STANDARD WAY
            // We use Selenium's built-in 'Select' class, which is designed for <select> tags.
            Select select = new Select(element);

            // This looks for the exact text you see on the screen (e.g., "California").
            select.selectByVisibleText(optionToSelect);
        } catch (Exception e) {

// --- APPROACH B: THE "FORCE" WAY (IF APPROACH A FAILS)
            // Sometimes websites hide the real dropdown and show a "fake" styled one.
            // If Selenium can't "see" the element to click it, we use JavaScript to force it.

            System.out.println("Standard Select failed, attempting JS fallback for: " + optionToSelect);

// This script runs directly in the browser. It loops through every option
            // in the dropdown until it finds a match for your text, then selects it.
            ((JavascriptExecutor) driver.get()).executeScript(
                    "var sel = arguments[0]; " +
                            "for(var i=0; i<sel.options.length; i++){ " +
                            "  if(sel.options[i].text == arguments[1]){ " +
                            "    sel.options[i].selected = true; " +
                            "    break; " +
                            "  } " +
                            "}", element, optionToSelect);

            // When we change a value using code, the website might not "notice."
            // We manually trigger a 'change' event to tell the website: "Hey, something was picked!"
            ((JavascriptExecutor) driver.get()).executeScript(
                    "arguments[0].dispatchEvent(new Event('change'));", element);
        }
    }

    // This method finds and returns ALL elements on the page that match a locator.
    public List<WebElement> getAllElements (By locator){

            // Find all elements that match the locator
            List<WebElement> elementList = driver.get().findElements(locator);

            return elementList;
        }

// This method performs a click action on a WebElement
    public void clickOn(WebElement element) {
        element.click();
    }

    }

