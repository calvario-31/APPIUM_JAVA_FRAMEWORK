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

    @BeforeMethod(alwaysRun = true, description = "setup")
    public void setUp() {
        setup();
        initPages();
    }

    @Test(groups = {"failed"})
    @Description("Verify the screenshot functionality")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("8dvc3IEV")
    public void dummyFailTest() {
        mainPage.loginStandardUser();

        Assert.assertTrue(mainPage.errorMessageIsDisplayed(),
                "Error message was not displayed");
    }

    @AfterMethod(alwaysRun = true, description = "teardown")
    public void tearDown() {
        teardown();
    }

    @Override
    public void initPages() {
        mainPage = new MainPage(driver);
    }
}
