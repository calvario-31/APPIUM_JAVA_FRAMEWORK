package credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import models.CredentialsModel;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.credentials.MainPage;
import utilities.Base;
import utilities.datareader.DataReader;

public class LockedOutUserTest extends Base {
    private MainPage mainPage;

    @BeforeMethod(alwaysRun = true, description = "setup")
    public void setUp() {
        setup();
        initPages();
    }

    @Test(groups = {"regression", "smoke"})
    @Description("Verify locked out error message with tap")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("8dvc3IEV")
    public void lockedOutWithTapTest() {
        mainPage.loginLockedOutUser();
        Assert.assertTrue(mainPage.errorMessageIsDisplayed(),
                "Error message was not displayed");
    }

    @Test(groups = {"regression", "smoke"}, dataProvider = "locked out credentials")
    @Description("Verify locked out error message with send keys")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("8dvc3IEV")
    @Parameters({"credentials"})
    public void lockedOutWithSendKeysTest(CredentialsModel credentials) {
        mainPage.login(credentials.getUsername(), credentials.getPassword());
        Assert.assertTrue(mainPage.errorMessageIsDisplayed(),
                "Error message was not displayed");
    }

    @AfterMethod(alwaysRun = true, description = "teardown")
    public void tearDown() {
        teardown();
    }

    @DataProvider(name = "locked out credentials")
    public Object[][] lockedOutDataProvider() {
        return new Object[][]{
                {new DataReader().getLockedOutCredentials()}
        };
    }

    @Override
    public void initPages() {
        mainPage = new MainPage(driver);
    }
}
