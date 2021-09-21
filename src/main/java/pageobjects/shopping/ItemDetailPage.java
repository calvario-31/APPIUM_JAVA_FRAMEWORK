package pageobjects.shopping;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageobjects.Page;
import utilities.Log;

public class ItemDetailPage extends Page {
    private final By backToProductsButton = MobileBy.AccessibilityId("test-BACK TO PRODUCTS");
    private final String addToCartButton = "test-ADD TO CART";
    private final String priceLabel = "test-Price";

    public ItemDetailPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @Step("Add item: {0} to cart with price: {1}")
    public void addToCart(String itemName, double itemPrice) {
        waitPageToLoad();
        Log.info("Verifying item name is displayed");
        Log.debug("Item name: " + itemName);
        scrollIntoText(itemName);
        Log.info("Verifying price matches the example data");
        String priceText = scrollIntoDescription(priceLabel).getText();
        Log.debug("priceText: " + priceText);
        Assert.assertEquals(Double.parseDouble(priceText.substring(1)), itemPrice);
        Log.info("Clicking on add to cart");
        scrollIntoDescription(addToCartButton).click();
        Log.info("Clicking on back to products");
        find(backToProductsButton).click();
    }

    @Override
    protected void waitPageToLoad() {
        waitVisibility(backToProductsButton, defaultTimeOut);
    }
}
