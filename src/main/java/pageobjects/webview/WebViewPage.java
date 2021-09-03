package pageobjects.webview;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.Page;
import utilities.Log;

import java.util.Set;

public class WebViewPage extends Page {
    private final By inputUrl = MobileBy.AccessibilityId("test-enter a https url here...");
    private final By buttonGoToSite = MobileBy.AccessibilityId("test-GO TO SITE");
    private final By bodyWebView = MobileBy.AccessibilityId("test-WEBVIEW SELECTION");

    public WebViewPage(AndroidDriver<AndroidElement> driver) {
        super(driver, 5);
    }

    @Step("Going to web page {0}")
    public void goToWebPage(String url) {
        waitPageToLoad();
        Log.info("Filling the url input");
        Log.debug("Url: " + url);
        find(inputUrl).sendKeys(url);
        Log.info("Clicking on the go to site button");
        find(buttonGoToSite).click();
    }

    @Step("Do something on the web page")
    public void loginOnWeb(String username, String password) {
        By inputUsername = By.id("user-name");
        By inputPassword = By.id("password");
        By buttonLogin = By.id("login-button");

        Log.info("Getting context handles");
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            Log.debug(contextName); //prints out something like NATIVE_APP  WEBVIEW_1
        }
        Log.info("Changing context to web view");
        driver.context((String) contextNames.toArray()[1]); // set context to WEBVIEW_1
        Log.info("Filling web username");
        Log.debug("username: " + username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputUsername)).sendKeys(username);
        Log.info("Filling web password");
        Log.debug("password: " + password);
        driver.findElement(inputPassword).sendKeys(password);
        Log.info("Clicking on web login button");
        driver.findElement(buttonLogin).click();
        Log.info("Changing context to mobile");
        driver.context((String) contextNames.toArray()[0]); // set context to NATIVE_APP
        Log.info("Pressing back button");
        pressBack();
    }

    @Step("Verifying the input is displayed")
    public boolean inputUrlIsDisplayed() {
        return elementIsDisplayed(inputUrl);
    }

    @Override
    protected void waitPageToLoad() {
        waitVisibility(bodyWebView);
    }
}
