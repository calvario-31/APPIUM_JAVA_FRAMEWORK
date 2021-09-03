package models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;

@ExcelSheet("itemData")
public class ShoppingItemModel {
    @ExcelCellName("name")
    private String name;
    @ExcelCellName("price")
    private double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "name: " + name + " " + "price: " + price;
    }
}
