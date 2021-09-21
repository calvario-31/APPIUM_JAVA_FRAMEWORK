package pageobjects.checkout;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageobjects.Page;
import utilities.Log;

public class InformationPage extends Page {
    private final By usernameInput = MobileBy.AccessibilityId("test-First Name");
    private final By passwordInput = MobileBy.AccessibilityId("test-Last Name");
    private final By zipcodeInput = MobileBy.AccessibilityId("test-Zip/Postal Code");
    private final By continueButton = MobileBy.AccessibilityId("test-CONTINUE");
    private final By bodyInfo = MobileBy.AccessibilityId("test-Checkout: Your Info");

    public InformationPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @Step("Filling the form with firstname: {0}, lastname: {1}, zipcode: {2}")
    public void fillForm(String firstname, String lastname, String zipcode) {
        waitPageToLoad();
        Log.info("Filling firstname");
        Log.debug("Firstname: " + firstname);
        find(usernameInput).sendKeys(firstname);
        Log.info("Filling lastname");
        Log.debug("Lastname: " + lastname);
        find(passwordInput).sendKeys(lastname);
        Log.info("Filling zipcode");
        Log.debug("Zipcode: " + zipcode);
        find(zipcodeInput).sendKeys(zipcode);
        Log.info("Clicking on continue");
        find(continueButton).click();
    }

    @Override
    protected void waitPageToLoad() {
        waitVisibility(bodyInfo, defaultTimeOut);
    }
}
