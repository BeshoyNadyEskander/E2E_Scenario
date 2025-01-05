package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListener;
import Pages.P01_loginPage;
import Pages.P02_landingPage;
import Pages.P03_cartPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getEnvironmentValue;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListener.class})
public class TC03_cartTestCase {


    @BeforeMethod(alwaysRun = true)
    public static void openBrowser() throws IOException {

        // handled key browser that we can sent that by mvn command line -Dbrowser=edge
        String browser = System.getProperty("browser") != null ? System.getProperty("browser")
                : getEnvironmentValue("environment", "browser");
        LogsUtils.info("Browser is: " + System.getProperty("browser"));
        setupDriver(browser);

        // setupDriver(getEnvironmentValue("environment", "browser"));
        LogsUtils.info("chrome is opened");
        getDriver().navigate().to(getEnvironmentValue("environment", "BASE_URL"));
        LogsUtils.info("page is redirected to base URL");
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @Description("comparing between total prices of selected products in landing page with total prices in cart page")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Business document", url = "https://www.saucedemo.com")
    @Issue("www.jira.com")
    @TmsLink("www.jira.com/zephyr/TC50")
    @Epic("WebSite")
    @Feature("cart Feature")
    @Story("as a user that price of selected products that are equal total price in cart page")
    @Test(groups = {"smoke"})
    public static void comparingTotalPriceOfSelectedProductsWithCartPrice() throws IOException {
        String totalPrice = new P01_loginPage(getDriver()).enterUsername(DataUtils
                        .getJsonData("validLoginData", "username"))
                .enterPassword(DataUtils.getJsonData("validLoginData", "password"))
                .clickOnLoginButton()
                .addRandomProductsIntoCart(2)
                .getTotalPriceOfSelectedProducts();
        new P02_landingPage(getDriver()).clickOnCartIcon();
        Assert.assertTrue(new P03_cartPage(getDriver()).comparingTotalPriceOfSelectedProducts(totalPrice));
    }

    @AfterMethod
    public static void quit() {
        quitDriver();

    }
}

