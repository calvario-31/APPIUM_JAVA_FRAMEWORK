package pageobjects.checkout;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageobjects.Page;
import utilities.Log;

public class YourCartPage extends Page {
    private final String buttonCheckout = "test-CHECKOUT";
    private final By labelDescription = MobileBy.AndroidUIAutomator("text(\"DESCRIPTION\")");

    public YourCartPage(AndroidDriver<AndroidElement> driver) {
        super(driver, 5);
    }

    @Step("Clicking on continue checkout")
    public void continueCheckout() {
        waitPageToLoad();
        Log.info("Clicking on continue checkout");
        scrollIntoDescription(buttonCheckout).click();
    }

    @Override
    protected void waitPageToLoad() {
        waitVisibility(labelDescription);
    }
}
