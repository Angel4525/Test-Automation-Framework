package com.utils;

import com.github.javafaker.Faker;
import com.ui.pojo.AddressPOJO;

import java.util.Locale;

// Generates fake address data using the Faker library.
public class FakeAddressUtility {

    public static AddressPOJO getFakeAddress(){

        // Create a Faker object using US locale
        Faker faker=new Faker(Locale.US);

        AddressPOJO addressPOJO=new AddressPOJO(faker.company().name(), faker.address().buildingNumber() ,
                faker.address().streetAddress(), faker.address().city(), faker.numerify("#####"),
                faker.phoneNumber().cellPhone(),faker.phoneNumber().cellPhone(),"other",
        "My Address", faker.address().state());

        return addressPOJO;
    }
}
