package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListener;
import Pages.P01_loginPage;
import Pages.P02_landingPage;
import Pages.P03_cartPage;
import Pages.P04_checkoutPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import Utilities.Utility;
import com.github.javafaker.Faker;
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
public class TC04_checkoutTestcase {


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

    @Description("checkout  ")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Business document", url = "https://www.saucedemo.com")
    @Issue("www.jira.com")
    @TmsLink("www.jira.com/zephyr/TC50")
    @Epic("WebSite")
    @Feature("checkout Feature")
    @Story("checkout info steps")
    @Test
    public static void checkoutStepOneTC() throws IOException {
        //TODO : login steps
        new P01_loginPage(getDriver()).enterUsername(DataUtils
                        .getJsonData("validLoginData", "username"))
                .enterPassword(DataUtils.getJsonData("validLoginData", "password"))
                .clickOnLoginButton();
        //TODO: add products into cart
        new P02_landingPage(getDriver())
                .addRandomProductsIntoCart(3)
                .clickOnCartIcon();
        //TODO: go to checkout page
        new P03_cartPage(getDriver())
                .clickingOnCheckoutButton();
        //TODO: fill info bill
        new P04_checkoutPage(getDriver())
                .fillingInformationBill(DataUtils.getJsonData("billInfo", "firstName")
                        , DataUtils.getJsonData("billInfo", "lastName")
                        , new Faker().number().digits(5))
                .clickingOnContinueButton();

        //TODO Verify on url
        Assert.assertTrue(Utility.VerifyURL(getDriver(), getEnvironmentValue("environment", "CHECKOUT_URL")));

    }

    @AfterMethod
    public static void quit() {
        quitDriver();

    }

}
