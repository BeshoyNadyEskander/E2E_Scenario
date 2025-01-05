package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_checkoutPage {
    private final WebDriver driver;
    private final By fNameLocator = By.id("first-name");
    private final By lNameLocator = By.id("last-name");
    private final By zipCodeLocator = By.id("postal-code");
    private final By continueButtonLocator = By.id("continue");

    public P04_checkoutPage(WebDriver driver) {
        this.driver = driver;
    }


    public P04_checkoutPage fillingInformationBill(String fName, String lName, String zipCode) {
        Utility.sendData(driver, fNameLocator, fName);
        Utility.sendData(driver, lNameLocator, lName);
        Utility.sendData(driver, zipCodeLocator, zipCode);
        return this;
    }

    public P05_OveviewPage clickingOnContinueButton() {
        Utility.clickingOnElement(driver, continueButtonLocator);
        return new P05_OveviewPage(driver);
    }


}
