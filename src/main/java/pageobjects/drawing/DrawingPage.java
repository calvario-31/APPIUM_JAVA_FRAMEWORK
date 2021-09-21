package pageobjects.drawing;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageobjects.Page;
import utilities.Log;

public class DrawingPage extends Page {
    private final By clearButton = MobileBy.AccessibilityId("test-CLEAR");
    private final By saveButton = MobileBy.AccessibilityId("test-SAVE");
    private final By okButton = MobileBy.id("android:id/button1");

    public DrawingPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @Step("Drawing x on the canvas")
    public void drawX() {
        waitPageToLoad();
        Log.info("Drawing x on the canvas");
        generalSwipe(30, 30, 60, 60);
        generalSwipe(60, 30, 30, 60);
        Log.info("Clicking on save button");
        find(saveButton).click();
        Log.info("Clicking on ok button");
        waitVisibility(okButton, defaultTimeOut).click();
        Log.info("Clicking on clear button");
        waitVisibility(clearButton, defaultTimeOut).click();
    }

    @Override
    protected void waitPageToLoad() {
        waitVisibility(clearButton, defaultTimeOut);
    }
}
