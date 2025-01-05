package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListener;
import Pages.P01_loginPage;
import Pages.P02_landingPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getEnvironmentValue;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListener.class})
public class TC02_LandingTestCases {


    @BeforeMethod
    public static void openBrowser() throws IOException {
        // handled key browser that we can sent that by mvn command line -Dbrowser=edge
        String browser = System.getProperty("browser") != null ? System.getProperty("browser")
                : getEnvironmentValue("environment", "browser");
        LogsUtils.info("Browser is: " + System.getProperty("browser"));
        setupDriver(browser);

        //  setupDriver(getEnvironmentValue("environment", "browser"));
        LogsUtils.info("chrome is opened");
        getDriver().navigate().to(getEnvironmentValue("environment", "BASE_URL"));
        LogsUtils.info("page is redirected to base URL");
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @Description("verify that user could add all products and checking them are displayed in cart icon ")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Business document", url = "https://www.saucedemo.com/inventory.html")
    @Issue("www.jira.com")
    @TmsLink("www.jira.com/zephyr/TC50")
    @Epic("Landing Page")
    @Feature("Add products to cart")
    @Story("as a user that could add products into cart and then number of selected products are displayed  into cart icon")
    @Test
    public static void CheckingNumberOfSelectedProductsTC() throws IOException {


        new P01_loginPage(getDriver()).enterUsername(DataUtils
                        .getJsonData("validLoginData", "username"))
                .enterPassword(DataUtils.getJsonData("validLoginData", "password"))
                .clickOnLoginButton()
                .addAllProductsToCart();
        Assert.assertTrue(new P02_landingPage(getDriver()).comparingNumberOfSelectedProductsWithCart());

        LogsUtils.trace("test case of selecting products and comparing with cart icon");
    }

    @Test
    public static void checkingOnSelecedNumberOfProducts() throws FileNotFoundException {
        new P01_loginPage(getDriver()).enterUsername(DataUtils
                        .getJsonData("validLoginData", "username"))
                .enterPassword(DataUtils.getJsonData("validLoginData", "password"))
                .clickOnLoginButton()
                .addRandomProductsIntoCart(3);
        Assert.assertTrue(new P02_landingPage(getDriver()).comparingNumberOfSelectedProductsWithCart());

    }


    @Test
    public static void clickingOnCart() throws IOException {
        new P01_loginPage(getDriver()).enterUsername(DataUtils
                        .getJsonData("validLoginData", "username"))
                .enterPassword(DataUtils.getJsonData("validLoginData", "password"))
                .clickOnLoginButton()
                .clickOnCartIcon();

        Assert.assertTrue(new P02_landingPage(getDriver()).verifyOnCartPageUrl(getEnvironmentValue("environment", "Cart_URL")));
    }

    @AfterMethod
    public static void quit() {
        quitDriver();

    }
}
