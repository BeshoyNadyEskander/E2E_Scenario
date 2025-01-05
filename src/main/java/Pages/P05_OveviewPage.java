package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_OveviewPage {
    private final static By subTotalItems = By.cssSelector("div[data-test=\"subtotal-label\"]");
    private final static By TaxLocator = By.cssSelector("div[data-test=\"tax-label\"]");
    private final static By totalPrice = By.cssSelector("div[data-test=\"total-label\"]");
    private final static By finishLocator = By.id("finish");
    private final WebDriver driver;

    public P05_OveviewPage(WebDriver driver) {

        this.driver = driver;
    }

    public double getSubPrice() {
        return Double.parseDouble(Utility.getText(driver, subTotalItems).replace("Item total: $", ""));

    }

    public double getTax() {
        return Double.parseDouble(Utility.getText(driver, TaxLocator).replace("Tax: $", ""));
    }

    public double getTotalPrice() {
        LogsUtils.info("Actual price: " + Utility.getText(driver, totalPrice).replace("Total: $", ""));
        return Double.parseDouble(Utility.getText(driver, totalPrice).replace("Total: $", ""));
    }


    public String calculatePrice() {
        LogsUtils.info("calculated :" + (getSubPrice() + getTax()));
        return String.valueOf(getSubPrice() + getTax());
    }

    public boolean comparingTotalPrice() {
        return calculatePrice().equals(String.valueOf(getTotalPrice()));
    }

    public P06_finishingOrder clicOnFinishButton() {
        Utility.clickingOnElement(driver, finishLocator);
        return new P06_finishingOrder(driver);
    }

}
