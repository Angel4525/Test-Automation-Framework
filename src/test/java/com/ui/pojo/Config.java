package com.ui.pojo;

import java.util.Map;

// Represents the whole json file.
public class Config {

 // The key is the String("DEV", "QA", "UAT")
    //the value is the ENV(url)
    Map<String, Env> environments;

    public Map<String, Env> getEnvironments() {
        return environments;
    }

    public void setEnvironments(Map<String, Env> environments) {
        this.environments = environments;
    }
}
