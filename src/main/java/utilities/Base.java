package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.Listeners;
import utilities.listeners.SuiteListeners;
import utilities.listeners.TestListeners;

@Listeners({TestListeners.class, SuiteListeners.class})
public abstract class Base {
    protected AndroidDriver<AndroidElement> driver;

    protected void setup() {
        driver = new DriverManager().buildDriver();
    }

    protected void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public AndroidDriver<AndroidElement> getDriver() {
        return driver;
    }
}
