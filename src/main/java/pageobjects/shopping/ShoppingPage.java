package pageobjects.shopping;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageobjects.Page;
import utilities.Log;

public class ShoppingPage extends Page {
    private final By title = MobileBy.AccessibilityId("test-Cart drop zone");

    public ShoppingPage(AndroidDriver<AndroidElement> driver) {
        super(driver, 5);
    }

    @Step("Going to detail of item: {0}")
    public void goToDetail(String itemName) {
        waitPageToLoad();
        Log.info("Clicking on the item name");
        scrollIntoText(itemName).click();
    }

    @Step("Verify shopping page is displayed")
    public boolean shoppingPageIsDisplayed() {
        Log.info("Verify shopping page is displayed");
        return elementIsDisplayed(title);
    }

    @Override
    protected void waitPageToLoad() {
        waitVisibility(title);
    }
}
