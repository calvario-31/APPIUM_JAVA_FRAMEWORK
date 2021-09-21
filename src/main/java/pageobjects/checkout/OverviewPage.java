package pageobjects.checkout;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageobjects.Page;
import utilities.Log;

public class OverviewPage extends Page {
    private final String itemTotalLabel = "Item total:";
    private final String finishButton = "test-FINISH";
    private final By itemsList = MobileBy.AccessibilityId("test-CHECKOUT: OVERVIEW");

    public OverviewPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @Step("Getting item total")
    public double getTotal() {
        waitPageToLoad();
        Log.info("Getting item total");
        String priceText = scrollIntoTextContains(itemTotalLabel).getText();
        Log.debug("Item total: " + priceText);
        return Double.parseDouble(priceText.substring(13));
    }

    @Step("Clicking on finish button")
    public void finishCheckout() {
        Log.info("Clicking on finish button");
        scrollIntoDescription(finishButton).click();
    }

    @Override
    protected void waitPageToLoad() {
        waitVisibility(itemsList, defaultTimeOut);
    }
}
