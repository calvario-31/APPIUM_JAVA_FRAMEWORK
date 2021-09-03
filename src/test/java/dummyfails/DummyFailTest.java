package dummyfails;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.credentials.MainPage;
import utilities.Base;

public class DummyFailTest extends Base {
    private MainPage mainPage;

    @BeforeMethod(alwaysRun = true, description = "setting up the driver")
    public void setUp() {
        setup();
    }

    @Test(groups = {"failed"})
    @Description("Verify the screenshot functionality")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("8dvc3IEV")
    public void dummyFailTest() {
        mainPage = new MainPage(driver);
        mainPage.loginStandardUser();

        Assert.assertTrue(mainPage.errorMessageIsDisplayed(),
                "Error message was not displayed");
    }

    @AfterMethod(alwaysRun = true, description = "tearing down the driver")
    public void tearDown() {
        teardown();
    }
}
