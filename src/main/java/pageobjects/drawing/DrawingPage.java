package pageobjects.drawing;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageobjects.Page;
import utilities.Log;

public class DrawingPage extends Page {
    private final By buttonClear = MobileBy.AccessibilityId("test-CLEAR");
    private final By buttonSave = MobileBy.AccessibilityId("test-SAVE");
    private final By buttonOk = MobileBy.id("android:id/button1");

    public DrawingPage(AndroidDriver<AndroidElement> driver) {
        super(driver, 5);
    }

    @Step("Drawing x on the canvas")
    public void drawX() {
        waitPageToLoad();
        Log.info("Drawing x on the canvas");
        generalSwipe(30, 30, 60, 60);
        generalSwipe(60, 30, 30, 60);
        Log.info("Clicking on save button");
        find(buttonSave).click();
        Log.info("Clicking on ok button");
        waitVisibility(buttonOk).click();
        Log.info("Clicking on clear button");
        waitVisibility(buttonClear).click();
    }

    @Override
    protected void waitPageToLoad() {
        waitVisibility(buttonClear);
    }
}
