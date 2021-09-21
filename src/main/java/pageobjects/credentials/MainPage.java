package pageobjects.credentials;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pageobjects.Page;
import utilities.Log;

public class MainPage extends Page {
    private final By usernameInput = MobileBy.AccessibilityId("test-Username");
    private final By passwordInput = MobileBy.AccessibilityId("test-Password");
    private final By loginButton = MobileBy.AccessibilityId("test-LOGIN");
    private final By errorMessage = MobileBy.AccessibilityId("test-Error message");
    private final By title = MobileBy.className("android.widget.ImageView");
    private final String standardUser = "test-standard_user";
    private final String lockedOutUser = "test-locked_out_user";

    public MainPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @Step("Login into the app with username {0} and password {1}")
    public void login(String username, String password) {
        waitPageToLoad();
        Log.info("Filling username");
        Log.debug("Username: " + username);
        find(usernameInput).sendKeys(username);
        Log.info("Filling password");
        Log.debug("Password: " + password);
        find(passwordInput).sendKeys(password);
        Log.info("Clicking on login button");
        find(loginButton).click();
    }

    @Step("Login with locked out user")
    public void loginLockedOutUser() {
        waitPageToLoad();
        Log.info("Scrolling into locked out user and click");
        scrollIntoDescription(lockedOutUser).click();
        Log.info("Scrolling to top");
        toTop();
        Log.info("Clicking on login button");
        find(loginButton).click();
    }

    @Step("Login with standard user")
    public void loginStandardUser() {
        waitPageToLoad();
        Log.info("Scrolling into standard out user and click");
        scrollIntoDescription(standardUser).click();
        Log.info("Scrolling to top");
        toTop();
        Log.info("Clicking on login button");
        find(loginButton).click();
    }

    @Step("Verifying error message is displayed")
    public boolean errorMessageIsDisplayed() {
        Log.info("Verifying error message is displayed");
        return elementIsDisplayed(errorMessage, defaultTimeOut);
    }

    @Step("Verifying title is displayed")
    public boolean titleIsDisplayed() {
        return elementIsDisplayed(title, defaultTimeOut);
    }

    @Override
    protected void waitPageToLoad() {
        waitVisibility(title, defaultTimeOut);
    }
}
