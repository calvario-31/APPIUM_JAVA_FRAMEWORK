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

    @BeforeMethod(alwaysRun = true, description = "setup")
    public void setUp() {
        setup();
        initPages();
    }

    @Test(dataProvider = "web view dp", groups = {"regression", "smoke"})
    @Description("Verify the web view functionality")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("8dvc3IEV")
    @Parameters({"credentials", "url to test"})
    public void webViewTest(CredentialsModel credentialsModel, String url) {
        mainPage.login(credentialsModel.getUsername(), credentialsModel.getPassword());

        topMenuPage.goToWebView();

        webViewPage.goToWebPage(url);
        webViewPage.loginOnWeb(credentialsModel.getUsername(), credentialsModel.getPassword());

        Assert.assertTrue(webViewPage.inputUrlIsDisplayed(),
                "Web View page was not displayed");

        topMenuPage.logout();
        Assert.assertTrue(mainPage.titleIsDisplayed(),
                "Main page was not displayed");
    }

    @AfterMethod(alwaysRun = true, description = "teardown")
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

    @Override
    public void initPages() {
        mainPage = new MainPage(driver);
        topMenuPage = new TopMenuPage(driver);
        webViewPage = new WebViewPage(driver);
    }
}
