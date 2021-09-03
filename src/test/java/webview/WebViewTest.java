package webview;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import models.CredentialsModel;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.credentials.MainPage;
import pageobjects.menu.TopMenuPage;
import pageobjects.webview.WebViewPage;
import utilities.Base;
import utilities.datareader.DataReader;

public class WebViewTest extends Base {
    private MainPage mainPage;
    private TopMenuPage topMenuPage;
    private WebViewPage webViewPage;

    @BeforeMethod(alwaysRun = true, description = "setting up the driver")
    public void setUp() {
        setup();
    }

    @Test(dataProvider = "web view dp", groups = {"regression", "smoke"})
    @Description("Verify the web view functionality")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("8dvc3IEV")
    @Parameters({"credentials", "url to test"})
    public void webViewTest(CredentialsModel credentialsModel, String url) {
        mainPage = new MainPage(driver);
        mainPage.login(credentialsModel.getUsername(), credentialsModel.getPassword());

        topMenuPage = new TopMenuPage(driver);
        topMenuPage.goToWebView();

        webViewPage = new WebViewPage(driver);
        webViewPage.goToWebPage(url);
        webViewPage.loginOnWeb(credentialsModel.getUsername(), credentialsModel.getPassword());

        Assert.assertTrue(webViewPage.inputUrlIsDisplayed());
        topMenuPage.logout();
        Assert.assertTrue(mainPage.titleIsDisplayed());
    }

    @AfterMethod(alwaysRun = true, description = "tearing down the driver")
    public void tearDown() {
        teardown();
    }

    @DataProvider(name = "web view dp")
    public Object[][] webViewDP() {
        DataReader dataReader = new DataReader();
        return new Object[][]{
                {dataReader.getStandardCredentials(), dataReader.getSauceLabsUrl()}
        };
    }
}
