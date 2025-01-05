package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_cartPage {
    private final static By priceOfSelectedProducts = By.xpath("//button[.=\"Remove\"]//preceding-sibling::div[@class=\"inventory_item_price\"]");
    static Double totalPrice = 0.0;
    private final WebDriver driver;
    private final By checkOutButton = By.id("checkout");

    public P03_cartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTotalPriceInCartPage() {

        try {
            List<WebElement> priceOfselectedProducts = driver.findElements(priceOfSelectedProducts);
            for (int i = 1; i <= priceOfselectedProducts.size(); i++) {
                By priceOfSelectedProductsLocator = By.xpath("(//button[.=\"Remove\"]//preceding-sibling::div[@class=\"inventory_item_price\"])[" + i + "]");
                String priceText = Utility.getText(driver, priceOfSelectedProductsLocator);
                totalPrice += Double.parseDouble(priceText.replace("$", ""));
            }
            LogsUtils.info("total price: " + totalPrice);
            return String.valueOf(totalPrice);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "price 0 due to didn't selected products";
        }

    }

    public boolean comparingTotalPriceOfSelectedProducts(String totalPrice) {
        return getTotalPriceInCartPage().equals(totalPrice);
    }

    public P04_checkoutPage clickingOnCheckoutButton() {
        Utility.clickingOnElement(driver, checkOutButton);
        return new P04_checkoutPage(driver);
    }
}
