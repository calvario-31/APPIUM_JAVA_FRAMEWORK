package pageobjects.checkout;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageobjects.Page;
import utilities.Log;

public class YourCartPage extends Page {
    private final String checkoutButton = "test-CHECKOUT";
    private final By descriptionLabel = MobileBy.AndroidUIAutomator("text(\"DESCRIPTION\")");

    public YourCartPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @Step("Clicking on continue checkout")
    public void continueCheckout() {
        waitPageToLoad();
        Log.info("Clicking on continue checkout");
        scrollIntoDescription(checkoutButton).click();
    }

    @Override
    protected void waitPageToLoad() {
        waitVisibility(descriptionLabel, defaultTimeOut);
    }
}
