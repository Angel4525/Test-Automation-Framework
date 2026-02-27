package com.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtility {

    // Reads a CSV file and converts each row into a User object
    public static Iterator<User> readCSVFile(String fileName) {

        // Create a File object pointing to the CSV file location
        File csvFile= new File(System.getProperty("user.dir")+
                "/testData/"+fileName);

        // FileReader will be used to read the CSV file
        FileReader fileReader= null;

        // CSVReader helps read CSV rows line by line
        CSVReader csvReader;

        // This array will hold one row of CSV data at a time
        String [] line;

        // List to store User objects created from CSV rows
        List<User> userList = null;

        // Temporary User object for each row
        User userData;

        try {
            // Open the CSV file
            fileReader= new FileReader(csvFile);
            // Initialize CSVReader with the FileReader
            csvReader= new CSVReader(fileReader);

            // Read and ignore the first row (header row)
             csvReader.readNext();

            // Initialize the list that will store all users
            userList= new ArrayList<>();

            // Read each remaining row until there are no more rows
            while ((line= csvReader.readNext())!= null){

                // Create a User object using values from the CSV row
                // line[0] = username, line[1] = password
             userData= new User(line[0], line[1]);

                // Add the User object to the list
                userList.add(userData);
         }


        } catch (FileNotFoundException e) {

            // Handles case where the CSV file cannot be found
            e.printStackTrace();
        }
        catch (CsvValidationException | IOException e) {

            // Handles CSV parsing errors or file reading issues
            e.printStackTrace();
        }

        // Convert the list of users into an Iterator and return it
        // This allows TestNG to consume one User at a time
return userList.iterator();
    }
}
