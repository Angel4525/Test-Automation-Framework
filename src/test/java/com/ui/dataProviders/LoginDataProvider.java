package com.ui.dataProviders;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utils.CSVReaderUtility;
import com.utils.ExcelReaderUtility;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

// This class provides test data to TestNG tests
public class LoginDataProvider {

    // TestNG DataProvider that feeds login data into tests
    // Iterator lets TestNG loop through test data one test case at a time
    @DataProvider(name = "LoginTestDataProvider")
    public Iterator<Object[]> loginDataProvider()throws FileNotFoundException {

        // Gson object used to convert JSON into Java objects
        Gson gson= new Gson();

        // Locates the JSON test data file in the project
        File testDataFile= new File(System.getProperty("user.dir")+
                "/testData/logindata.json");

        // Reads the JSON file
        FileReader fileReader= new FileReader(testDataFile);

        // Converts JSON into a TestData object
TestData data= gson.fromJson(fileReader, TestData.class);

        // List that will be returned to TestNG
        // Each Object[] represents ONE test execution
        List<Object[]> dataToReturn= new ArrayList<Object[]>();

        // Loop through each user from the JSON file
        for (User user: data.getData()){

            // Wrap each User object in an Object[]
            // This matches TestNG DataProvider requirements
            dataToReturn.add(new Object[]{user});
        }

        // Convert the list into an Iterator and return it
        return dataToReturn.iterator();

    }

    // This DataProvider supplies login data from a CSV file
    @DataProvider(name = "LoginTestCSVDataProvider")
    public Iterator<User> loginCSVDataProvider(){

        // Calls the CSV utility method to read the CSV file
        // Returns an Iterator of User objects
    return CSVReaderUtility.readCSVFile("loginData.csv");
    }

    @DataProvider(name = "LoginTestExcelDataProvider")
    public Iterator<User> loginExcelDataProvider(){

        // Calls the Excel reader method and supplies data to TestNG tests
        return ExcelReaderUtility.readExcelFile("LoginData.xlsx");
    }
}
