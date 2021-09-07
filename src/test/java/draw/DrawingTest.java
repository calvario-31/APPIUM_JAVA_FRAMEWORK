package draw;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import models.CredentialsModel;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.credentials.MainPage;
import pageobjects.drawing.DrawingPage;
import pageobjects.menu.TopMenuPage;
import utilities.Base;
import utilities.datareader.DataReader;

public class DrawingTest extends Base {
    private MainPage mainPage;
    private TopMenuPage topMenuPage;
    private DrawingPage drawingPage;

    @BeforeMethod(alwaysRun = true, description = "setup")
    public void setUp() {
        setup();
        initPages();
    }

    @Test(dataProvider = "valid credentials", groups = {"regression"})
    @Description("Verify the drawing functionality")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("8dvc3IEV")
    @Parameters({"credentials"})
    public void drawingXTest(CredentialsModel credentialsModel) {
        mainPage.login(credentialsModel.getUsername(), credentialsModel.getPassword());

        topMenuPage.goToDrawing();

        drawingPage.drawX();

        topMenuPage.logout();

        Assert.assertTrue(mainPage.titleIsDisplayed(),
                "Title was not displayed");
    }

    @AfterMethod(alwaysRun = true, description = "teardown")
    public void tearDown() {
        teardown();
    }

    @DataProvider(name = "valid credentials")
    public Object[][] validCredentialsDP() {
        return new Object[][]{
                {new DataReader().getStandardCredentials()}
        };
    }

    @Override
    public void initPages() {
        mainPage = new MainPage(driver);
        topMenuPage = new TopMenuPage(driver);
        drawingPage = new DrawingPage(driver);
    }
}
