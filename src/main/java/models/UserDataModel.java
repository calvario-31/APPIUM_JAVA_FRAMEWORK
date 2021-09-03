package models;

import com.github.javafaker.Faker;

public class UserDataModel {
    private final String firstname;
    private final String lastname;
    private final String zipCode;

    public UserDataModel() {
        Faker faker = new Faker();
        firstname = faker.name().firstName();
        lastname = faker.name().lastName();
        zipCode = faker.address().zipCode();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "firstname: " + firstname + " " + "lastname: " + lastname + " " + "zipCode: " + zipCode;
    }
}
