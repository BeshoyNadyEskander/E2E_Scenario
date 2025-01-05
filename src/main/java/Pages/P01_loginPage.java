package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_loginPage {

    private final By usernameLocator = By.id("user-name");
    private final By passwordLocator = By.id("password");
    private final By loginButtonLocator = By.id("login-button");
    private final WebDriver driver;

    public P01_loginPage(WebDriver driver) {
        this.driver = driver;
    }

    public P01_loginPage enterUsername(String usernameText) {
        Utility.sendData(driver, usernameLocator, usernameText);
        return this;
    }

    public P01_loginPage enterPassword(String passwordText) {
        Utility.sendData(driver, passwordLocator, passwordText);
        return this;
    }

    public P02_landingPage clickOnLoginButton() {
        Utility.clickingOnElement(driver, loginButtonLocator);
        return new P02_landingPage(driver);
    }
}
