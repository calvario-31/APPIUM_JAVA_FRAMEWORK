package pageobjects.checkout;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageobjects.Page;
import utilities.Log;

public class InformationPage extends Page {
    private final By inputUsername = MobileBy.AccessibilityId("test-First Name");
    private final By inputPassword = MobileBy.AccessibilityId("test-Last Name");
    private final By inputZipcode = MobileBy.AccessibilityId("test-Zip/Postal Code");
    private final By buttonContinue = MobileBy.AccessibilityId("test-CONTINUE");
    private final By bodyInfo = MobileBy.AccessibilityId("test-Checkout: Your Info");

    public InformationPage(AndroidDriver<AndroidElement> driver) {
        super(driver, 5);
    }

    @Step("Filling the form with firstname: {0}, lastname: {1}, zipcode: {2}")
    public void fillForm(String firstname, String lastname, String zipcode) {
        waitPageToLoad();
        Log.info("Filling firstname");
        Log.debug("Firstname: " + firstname);
        find(inputUsername).sendKeys(firstname);
        Log.info("Filling lastname");
        Log.debug("Lastname: " + lastname);
        find(inputPassword).sendKeys(lastname);
        Log.info("Filling zipcode");
        Log.debug("Zipcode: " + zipcode);
        find(inputZipcode).sendKeys(zipcode);
        Log.info("Clicking on continue");
        find(buttonContinue).click();
    }

    @Override
    protected void waitPageToLoad() {
        waitVisibility(bodyInfo);
    }
}
