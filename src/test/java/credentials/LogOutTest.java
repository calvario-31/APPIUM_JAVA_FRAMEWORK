package credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.credentials.MainPage;
import pageobjects.menu.TopMenuPage;
import utilities.Base;

public class LogOutTest extends Base {
    private MainPage mainPage;
    private TopMenuPage topMenuPage;

    @BeforeMethod(alwaysRun = true, description = "setup")
    public void setUp() {
        setup();
        initPages();
    }

    @Test(groups = {"smoke"})
    @Description("Verify the logout functionality")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("8dvc3IEV")
    public void logoutTest() {
        mainPage.loginStandardUser();

        topMenuPage.logout();

        Assert.assertTrue(mainPage.titleIsDisplayed(),
                "Title was not displayed");
    }

    @AfterMethod(alwaysRun = true, description = "teardown")
    public void tearDown() {
        teardown();
    }

    @Override
    public void initPages() {
        mainPage = new MainPage(driver);
        topMenuPage = new TopMenuPage(driver);
    }
}
