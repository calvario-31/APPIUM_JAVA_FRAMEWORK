package utilities.datareader;

import com.poiji.bind.Poiji;
import models.CredentialsModel;
import models.ShoppingItemModel;

import java.io.File;
import java.util.List;

public class DataReader {
    private final String EXCEL_PATH = "src/test/resources/data/testData.xlsx";
    private final String sauceLabsUrl = "https://www.saucedemo.com";

    public CredentialsModel getStandardCredentials() {
        return Poiji.fromExcel(new File(EXCEL_PATH), CredentialsModel.class).get(0);
    }

    public CredentialsModel getLockedOutCredentials() {
        return Poiji.fromExcel(new File(EXCEL_PATH), CredentialsModel.class).get(1);
    }

    public String getSauceLabsUrl() {
        return sauceLabsUrl;
    }

    public List<ShoppingItemModel> getShoppingList() {
        return Poiji.fromExcel(new File(EXCEL_PATH), ShoppingItemModel.class);
    }

}
