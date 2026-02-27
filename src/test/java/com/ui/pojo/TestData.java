package com.ui.pojo;

import java.util.List;

// This class represents the ENTIRE JSON structure
// It wraps the list of users under the "data" key
public class TestData {

    // Matches the "data" array in the JSON file
    public List<User> data;

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
