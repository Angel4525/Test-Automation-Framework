package com.utils;

import com.constants.Environment;
import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Env;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

// Utility class to handle reading JSON configuration files
public class JSONUtility {

    // Reads the JSON config and returns the URL of the requested environment
    public static Env readJSON(Environment environment) {

        // Gson is a tool to convert JSON to Java objects
        Gson gson= new Gson();

        // Locate the JSON file relative to the project root directory
        File jsonFile= new File(System.getProperty("user.dir")+
                "/src/test/resources/config/config.json");

        FileReader fileReader= null;

        try {
            // Open the JSON file for reading
            fileReader = new FileReader(jsonFile);
        } catch (FileNotFoundException e) {
            // Crash nicely if the file is missing
            throw new RuntimeException(e);
        }

        // Convert the JSON file into a Config object.
        // .class is added due to the existence of config file so the two can be differentiated
        Config config=gson.fromJson(fileReader, Config.class);

        // Get the environment object(url) based on the name passed in .get
        Env env= config.getEnvironments().get("QA");

        // Return the URL of the environment
return env;
    }
}
