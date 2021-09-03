package utilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.Dimension;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

public class Gestures {
    private final AndroidDriver<AndroidElement> driver;

    public Gestures(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }

    public AndroidElement scrollIntoDescription(String description) {
        return driver.findElementByAndroidUIAutomator(
                "UiScrollable(scrollable(true)).scrollIntoView(description(\"" + description + "\"))");
    }

    public AndroidElement scrollIntoText(String text) {
        return driver.findElementByAndroidUIAutomator(
                "UiScrollable(scrollable(true)).scrollIntoView(text(\"" + text + "\"))");
    }

    public AndroidElement scrollIntoTextContains(String textContains) {
        return driver.findElementByAndroidUIAutomator(
                "UiScrollable(scrollable(true)).scrollIntoView(textContains(\"" + textContains + "\"))");
    }

    public void tap(AndroidElement element) {
        new TouchAction<>(driver)
                .tap(tapOptions()
                        .withElement(element(element)))
                .perform();
    }

    public void longTap(AndroidElement element, int duration) {
        new TouchAction<>(driver)
                .longPress(longPressOptions()
                        .withElement(element(element))
                        .withDuration(ofSeconds(duration)))
                .release()
                .perform();
    }

    public void generalSwipeByPercentages(int x1, int y1, int x2, int y2) {
        double firstXPercentage = x1 / 100.0;
        double firstYPercentage = y1 / 100.0;
        double secondXPercentage = x2 / 100.0;
        double secondYPercentage = y2 / 100.0;

        Dimension size = driver.manage().window().getSize();

        int xFirstPoint = (int) (size.width * firstXPercentage);
        int yFirstPoint = (int) (size.height * firstYPercentage);
        int xSecondPoint = (int) (size.width * secondXPercentage);
        int ySecondPoint = (int) (size.height * secondYPercentage);
        new TouchAction<>(driver)
                .press(point(xFirstPoint, yFirstPoint))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(xSecondPoint, ySecondPoint))
                .release()
                .perform();
    }

    public void scrollToTop() {
        driver.findElementByAndroidUIAutomator(
                "new UiScrollable(scrollable(true)).scrollToBeginning(10)");
    }

    public void pressBack() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }
}
