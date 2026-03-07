package com.ui.pages;

import com.utils.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class SearchResultPage extends BrowserUtility {

    // Locator for the search result title
    private static final By PRODUCT_LISTING_TITLE_LOCATOR= By.xpath("//span[@class=\"lighter\"]");

    // Locator for ALL product names shown in the results
    private static final By All_PRODUCT_LIST_NAME= By.xpath("//h5[@itemprop='name']/a");

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    // Returns the title text from the search results page
    public String getSearchResultTitle(){
        return getVisibleText(PRODUCT_LISTING_TITLE_LOCATOR);
    }


    // This method checks if the search term appears in ANY product name
    public boolean isSearchTermInProductList(String serchTerm){

        // wait for product results to appear
        waitForVisibilityOfElements(All_PRODUCT_LIST_NAME);


        // Convert the search term into lowercase and split it into individual words
        //  "Printed Summer Dress" becomes -> ["printed", "summer", "dress"]
        List<String> keywords= Arrays.asList(serchTerm.toLowerCase().split(" "));

        // Get all product names displayed on the page
        List<String> productNamesList= getAllVisibleText(All_PRODUCT_LIST_NAME);

        boolean result= productNamesList.stream()

                //Check if ANY product name satisfies the condition
                .anyMatch(name->

                        // For each product name, check if ANY keyword appears in it
                  (keywords.stream().anyMatch(name.toLowerCase()::contains)));

        return result;
    }


    public ProductDetailPage clickOnTheProductAtIndex(int index){

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(All_PRODUCT_LIST_NAME));

       clickOn(getAllElements(All_PRODUCT_LIST_NAME).get(index));

       ProductDetailPage productDetailPage= new ProductDetailPage(getDriver());
       return productDetailPage;
    }
}
