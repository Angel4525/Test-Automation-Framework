package com.utils;

import com.constants.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {

    /*
      Reads a value from a .properties file based on:
     the environment (dev, qa, prod, etc.) and the property name(key)
     */
    public static String readProperty(Environment environment, String propertyName) {

        //print the current project root directory
       // System.out.println(System.getProperty("user.dir"));

        //build the full path to the properties file
        File propFile= new File(System.getProperty("user.dir")+
                "/src/test/resources/config/"+environment+".properties");

        // FileReader is used to read the contents of the file
        // It starts as null because we will initialize it in the try block
        FileReader fileReader= null;

        // Properties is a Java class used to store key-value pairs
        // from a .properties file
        Properties properties= new Properties();

        try {

            // Opens the properties file so Java can read it
            fileReader = new FileReader(propFile);

            // Loads all key-value pairs from the file into the Properties object
            properties.load(fileReader);
        }
        catch (FileNotFoundException e) {

            // This runs if the file path is incorrect or the file doesn't exist
            throw new RuntimeException(e);
        }
        catch (IOException e) {

            // This runs if there is an issue reading the file
            throw new RuntimeException(e);
        }

        // Retrieves the value associated with the given property name
        // propertyName is converted to uppercase to match the key format
        String value= properties.getProperty(propertyName.toUpperCase());

        // Returns the value found in the properties file
        return value;
    }
}
