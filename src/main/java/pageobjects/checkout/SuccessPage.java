package pageobjects.checkout;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageobjects.Page;
import utilities.Log;

public class SuccessPage extends Page {
    private final By backToHomeButton = MobileBy.AccessibilityId("test-BACK HOME");
    private final By bodySuccess = MobileBy.AccessibilityId("test-CHECKOUT: COMPLETE!");

    public SuccessPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @Step("Verifying success page is displayed")
    public boolean successPageIsDisplayed() {
        Log.info("Verifying success page is displayed");
        return elementIsDisplayed(bodySuccess, defaultTimeOut);
    }

    @Step("Clicking on back to home")
    public void goToHome() {
        Log.info("Clicking on back to home");
        find(backToHomeButton).click();
    }

    @Override
    protected void waitPageToLoad() {

    }
}
