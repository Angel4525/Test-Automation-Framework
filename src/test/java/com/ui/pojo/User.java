package com.ui.pojo;

//represents one user from the JSON file
public class User {

    //match 'emailAddress' and 'password from Json'. They have to be typed the same
    private String emailAddress;
    private String password;


    public User(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User " +
                "emailAddress='" + emailAddress + '\'' +
                ", password='" + password;
    }
}
