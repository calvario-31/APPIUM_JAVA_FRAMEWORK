package pageobjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Gestures;

public abstract class Page {
    protected AndroidDriver<AndroidElement> driver;
    protected Gestures gestures;
    protected final int defaultTimeOut = 5;

    public Page(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        gestures = new Gestures(this.driver);
    }

    protected AndroidElement scrollIntoDescription(String description) {
        return gestures.scrollIntoDescription(description);
    }

    protected AndroidElement scrollIntoText(String text) {
        return gestures.scrollIntoText(text);
    }

    protected AndroidElement scrollIntoTextContains(String text) {
        return gestures.scrollIntoTextContains(text);
    }

    protected AndroidElement find(By locator) {
        return driver.findElement(locator);
    }

    protected AndroidElement waitVisibility(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        return (AndroidElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected boolean elementIsDisplayed(By locator, int timeOut) {
        try {
            waitVisibility(locator, timeOut);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void toTop() {
        gestures.scrollToTop();
    }

    protected void generalSwipe(int x1, int y1, int x2, int y2) {
        gestures.generalSwipeByPercentages(x1, y1, x2, y2);
    }

    protected void pressBack() {
        gestures.pressBack();
    }

    protected abstract void waitPageToLoad();
}
