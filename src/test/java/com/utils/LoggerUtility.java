package com.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Utility class used to create and provide a Logger
public class LoggerUtility {

    // Marking it private prevents anyone from creating an object of LoggerUtility
    private LoggerUtility() {
    }

    // Returns a Logger for the given class
    //<?> is a class of any type- int, string...
    public static Logger getLogger(Class<?> clazz){

        // Create a local logger reference
        Logger logger=null;

        // Check if the logger has not been created yet
        if (logger==null) {

            // LogManager creates or retrieves a logger
            logger = LogManager.getLogger(clazz);
        }
        return logger;
    }
}
