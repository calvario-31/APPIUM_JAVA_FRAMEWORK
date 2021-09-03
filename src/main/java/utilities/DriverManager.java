package utilities;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class DriverManager {
    private AndroidDriver<AndroidElement> driver;
    public static String osVersion;
    public static String deviceName;
    public static boolean runOnServer;

    public AndroidDriver<AndroidElement> buildDriver() {
        if (runOnServer) {
            Log.info("Building remote driver");
            driver = buildRemoteDriver();
        } else {
            Log.info("Building local driver");
            driver = buildLocalDriver();
        }
        return driver;
    }

    public AndroidDriver<AndroidElement> buildLocalDriver() {
        try {
            String appiumUrl = "http://localhost:4723/wd/hub";

            File fileAPK = new File("src/main/resources/apk/sauceLabs.apk");

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
            desiredCapabilities.setCapability("autoGrantPermissions", true);
            desiredCapabilities.setCapability("appWaitActivity", "com.swaglabsmobileapp.MainActivity");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, osVersion);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityType.APP, fileAPK.getAbsolutePath());

            driver = new AndroidDriver<>(new URL(appiumUrl), desiredCapabilities);
            return driver;
        } catch (Exception e) {
            e.printStackTrace();
            Log.error("Failed building local driver");
            return null;
        }
    }

    public AndroidDriver<AndroidElement> buildRemoteDriver() {
        try {
            String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
            String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
            String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
            String username = System.getenv("BROWSERSTACK_USERNAME");
            String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
            String app = System.getenv("BROWSERSTACK_APP_ID");
            String browserStackUrl = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

            DesiredCapabilities desiredCapabilities = DesiredCapabilities.android();
            desiredCapabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
            desiredCapabilities.setCapability(MobileCapabilityType.APP, app);
            desiredCapabilities.setCapability("autoGrantPermissions", true);
            desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability("appWaitActivity", "com.swaglabsmobileapp.MainActivity");
            desiredCapabilities.setCapability("os_version", osVersion);
            desiredCapabilities.setCapability("real_mobile", true);
            desiredCapabilities.setCapability("browserstack.appium_version", "1.21.0");
            desiredCapabilities.setCapability("browserstack.local", browserstackLocal);
            desiredCapabilities.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);
            desiredCapabilities.setCapability("build", buildName);
            desiredCapabilities.setCapability("browserstack.debug", "true");  // for enabling visual logs
            desiredCapabilities.setCapability("browserstack.console", "info");  // to enable console logs at the info level. You can also use other log levels here
            desiredCapabilities.setCapability("browserstack.networkLogs", "true");  // to enable network logs to be logged


            driver = new AndroidDriver<>(new URL(browserStackUrl), desiredCapabilities);
            return driver;
        } catch (Exception e) {
            e.printStackTrace();
            Log.error("Failed building remote driver");
            return null;
        }
    }


    public static void assignDriverParameters() {
        DriverManager.runOnServer = System.getenv("JOB_NAME") != null;

        if (DriverManager.runOnServer) {
            DriverManager.osVersion = System.getProperty("osVersion");
            DriverManager.deviceName = System.getProperty("deviceName");
        } else {
            String deviceName = System.getProperty("deviceName");
            String osVersion = System.getProperty("deviceName");
            if (deviceName == null) {
                Log.info("Setting default emulator name to mobile_emulator");
                deviceName = "mobile_emulator";
            }
            if(osVersion == null) {
                Log.info("Setting default os version to 11");
                osVersion = "11";
            }
            DriverManager.deviceName = deviceName;
            DriverManager.osVersion = osVersion;
        }
    }

    public static void writeEnvVariables() {
        Log.info("Writing environmental variables to the report");
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Platform", "Android")
                        .put("Platform version", osVersion)
                        .put("Device Name", deviceName)
                        .put("APK", "SauceLabs")
                        .build());
    }

    @Attachment(value = "Screenshot failure", type = "image/png")
    public byte[] getScreenshot(AndroidDriver<AndroidElement> driver) {
        Log.info("Taking screenshot");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
