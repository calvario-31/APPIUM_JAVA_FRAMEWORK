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

    @BeforeMethod(alwaysRun = true, description = "setting up the driver")
    public void setUp() {
        setup();
    }

    @Test(dataProvider = "shopping dp", groups = {"regression"})
    @Description("Verify the end to end shopping flow")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("8dvc3IEV")
    @Parameters({"item list", "user data"})
    public void shoppingTest(List<ShoppingItemModel> itemList, UserDataModel userDataModel) {
        mainPage = new MainPage(driver);
        mainPage.loginStandardUser();

        shoppingPage = new ShoppingPage(driver);
        itemDetailPage = new ItemDetailPage(driver);

        double sum = 0;
        for (ShoppingItemModel shoppingItemModel : itemList) {
            shoppingPage.goToDetail(shoppingItemModel.getName());
            itemDetailPage.addToCart(shoppingItemModel.getName(), shoppingItemModel.getPrice());
            sum += shoppingItemModel.getPrice();
        }

        topMenuPage = new TopMenuPage(driver);
        Assert.assertEquals(topMenuPage.getItemCount(), itemList.size(),
                "Item count was not the same as the item list size");

        topMenuPage.goToCheckout();

        yourCartPage = new YourCartPage(driver);
        yourCartPage.continueCheckout();

        informationPage = new InformationPage(driver);
        informationPage.fillForm(userDataModel.getFirstname(), userDataModel.getLastname(),
                userDataModel.getZipCode());

        overviewPage = new OverviewPage(driver);
        Assert.assertEquals(overviewPage.getTotal(), sum,
                "The total from the data was not the same as the UI");

        overviewPage.finishCheckout();

        successPage = new SuccessPage(driver);
        Assert.assertTrue(successPage.successPageIsDisplayed(),
                "Success page was not displayed");

        successPage.goToHome();

        Assert.assertTrue(shoppingPage.shoppingPageIsDisplayed(),
                "Shopping page was not displayed");

        topMenuPage.logout();
        Assert.assertTrue(mainPage.titleIsDisplayed(),
                "Login page was not displayed");
    }

    @AfterMethod(alwaysRun = true, description = "tearing down the driver")
    public void tearDown() {
        teardown();
    }

    @DataProvider(name = "shopping dp")
    public Object[][] shoppingDP() {
        return new Object[][]{
                {new DataReader().getShoppingList(), new UserDataModel()}
        };
    }
}
