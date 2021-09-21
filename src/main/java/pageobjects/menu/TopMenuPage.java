package pageobjects.menu;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageobjects.Page;
import utilities.Log;

public class TopMenuPage extends Page {
    private final By burgerMenu = MobileBy.AccessibilityId("test-Menu");
    private final By logoutOption = MobileBy.AccessibilityId("test-LOGOUT");
    private final By drawingOption = MobileBy.AccessibilityId("test-DRAWING");
    private final By webViewOption = MobileBy.AccessibilityId("test-WEBVIEW");
    private final By checkoutButton = MobileBy.AccessibilityId("test-Cart");
    private final By itemCount = MobileBy.AndroidUIAutomator(
            "description(\"test-Cart\").childSelector(className(\"android.widget.TextView\"))");

    public TopMenuPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @Step("Logging out")
    public void logout() {
        waitPageToLoad();
        Log.info("Clicking on the menu burger");
        find(burgerMenu).click();
        Log.info("Clicking on logout");
        waitVisibility(logoutOption, defaultTimeOut).click();
    }

    @Step("Go to drawing")
    public void goToDrawing() {
        waitPageToLoad();
        Log.info("Clicking on the menu burger");
        find(burgerMenu).click();
        Log.info("Clicking on drawing");
        waitVisibility(drawingOption, defaultTimeOut).click();
    }

    @Step("Go to web view")
    public void goToWebView() {
        waitPageToLoad();
        Log.info("Clicking on the menu burger");
        find(burgerMenu).click();
        Log.info("Clicking on web view");
        waitVisibility(webViewOption, defaultTimeOut).click();
    }

    @Step("Getting the item count text")
    public int getItemCount() {
        waitPageToLoad();
        Log.info("Getting the item count text");
        String text = find(itemCount).getText();
        Log.debug("Item count: " + text);
        return Integer.parseInt(text);
    }

    @Step("Going to checkout")
    public void goToCheckout() {
        Log.info("Clicking on checkout");
        find(checkoutButton).click();
    }

    @Override
    protected void waitPageToLoad() {
        waitVisibility(burgerMenu, defaultTimeOut);
    }
}
