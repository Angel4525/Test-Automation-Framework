package com.utils;

import com.ui.pojo.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtility {

    // This method reads an Excel file and returns an Iterator of User objects
    public static Iterator<User> readExcelFile(String fileName){

        // Create a File object pointing to the Excel file location
        File xlsxFile= new File(System.getProperty("user.dir")+
                "/testData/"+ fileName);

        // Workbook object used to read .xlsx Excel files
        XSSFWorkbook xssfWorkbook= null;

        // List that will store User objects created from Excel rows
        List<User> userList= null;

        // Represents a sheet inside the Excel workbook
        XSSFSheet xssfSheet;

        // Used to loop through rows in the sheet
        Iterator<Row> rowIterator;

        // Represents a single row in the Excel sheet
        Row row;

        // Represents individual cells in a row
        Cell emailAddressCell;
        Cell passwordCell;

        // User object created from Excel data
        User user;

        try {

            // Open the Excel workbook using the file
            xssfWorkbook = new XSSFWorkbook(xlsxFile);

            // Initialize the list that will store all users
            userList= new ArrayList<>();

            // Get the Excel sheet named "LoginTestData"
             xssfSheet= xssfWorkbook.getSheet("LoginTestData");

            // Get an iterator to loop through all rows in the sheet
            rowIterator= xssfSheet.iterator();

            //skipping the header
            rowIterator.next();

            // Loop through remaining rows while data exists
            while (rowIterator.hasNext()) {

                // Get the next row from the sheet
                row = rowIterator.next();

                // Get the first cell (column 0) → email
                emailAddressCell = row.getCell(0);

                // Get the second cell (column 1) → password
                passwordCell = row.getCell(1);

                // Create a User object using email and password from Excel
                user = new User(emailAddressCell.toString(), passwordCell.toString());


                // Add the User object to the list
                userList.add(user);

                // Close the workbook after reading
                xssfWorkbook.close();
            }


        } catch (IOException e) {
            // Convert checked exception into runtime exception
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            // Thrown if the Excel file format is invalid
            throw new RuntimeException(e);
        }

        // Return an iterator so TestNG can consume the data one User at a time
        return userList.iterator();
    }
}
