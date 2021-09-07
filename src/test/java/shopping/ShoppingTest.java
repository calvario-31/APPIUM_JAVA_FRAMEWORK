package shopping;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import models.ShoppingItemModel;
import models.UserDataModel;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.checkout.InformationPage;
import pageobjects.checkout.OverviewPage;
import pageobjects.checkout.SuccessPage;
import pageobjects.checkout.YourCartPage;
import pageobjects.credentials.MainPage;
import pageobjects.menu.TopMenuPage;
import pageobjects.shopping.ItemDetailPage;
import pageobjects.shopping.ShoppingPage;
import utilities.Base;
import utilities.datareader.DataReader;

import java.util.List;

public class ShoppingTest extends Base {
    private MainPage mainPage;
    private ShoppingPage shoppingPage;
    private ItemDetailPage itemDetailPage;
    private TopMenuPage topMenuPage;
    private YourCartPage yourCartPage;
    private InformationPage informationPage;
    private OverviewPage overviewPage;
    private SuccessPage successPage;

    @BeforeMethod(alwaysRun = true, description = "setup")
    public void setUp() {
        setup();
    }

    @Test(dataProvider = "shopping dp", groups = {"regression"})
    @Description("Verify the end to end shopping flow")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("8dvc3IEV")
    @Parameters({"item list", "user data"})
    public void shoppingTest(List<ShoppingItemModel> itemList, UserDataModel userDataModel) {
        mainPage.loginStandardUser();

        double sum = 0;
        for (ShoppingItemModel shoppingItemModel : itemList) {
            shoppingPage.goToDetail(shoppingItemModel.getName());
            itemDetailPage.addToCart(shoppingItemModel.getName(), shoppingItemModel.getPrice());
            sum += shoppingItemModel.getPrice();
        }

        Assert.assertEquals(topMenuPage.getItemCount(), itemList.size(),
                "Item count was not the same as the item list size");

        topMenuPage.goToCheckout();

        yourCartPage.continueCheckout();

        informationPage.fillForm(userDataModel.getFirstname(), userDataModel.getLastname(),
                userDataModel.getZipCode());

        Assert.assertEquals(overviewPage.getTotal(), sum,
                "The total from the data was not the same as the UI");

        overviewPage.finishCheckout();

        Assert.assertTrue(successPage.successPageIsDisplayed(),
                "Success page was not displayed");

        successPage.goToHome();

        Assert.assertTrue(shoppingPage.shoppingPageIsDisplayed(),
                "Shopping page was not displayed");

        topMenuPage.logout();
        Assert.assertTrue(mainPage.titleIsDisplayed(),
                "Login page was not displayed");
    }

    @AfterMethod(alwaysRun = true, description = "teardown")
    public void tearDown() {
        teardown();
    }

    @DataProvider(name = "shopping dp")
    public Object[][] shoppingDP() {
        return new Object[][]{
                {new DataReader().getShoppingList(), new UserDataModel()}
        };
    }

    @Override
    public void initPages() {
        mainPage = new MainPage(driver);
        shoppingPage = new ShoppingPage(driver);
        itemDetailPage = new ItemDetailPage(driver);
        topMenuPage = new TopMenuPage(driver);
        yourCartPage = new YourCartPage(driver);
        informationPage = new InformationPage(driver);
        overviewPage = new OverviewPage(driver);
        successPage = new SuccessPage(driver);
    }
}
