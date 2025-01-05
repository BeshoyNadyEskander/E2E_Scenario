package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListener;
import Pages.P01_loginPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getEnvironmentValue;
import static Utilities.DataUtils.getJsonData;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListener.class})
public class TC01_LoginTestCases {


    @BeforeMethod
    public void setup() throws IOException {
        // handled key browser that we can sent that by mvn command line -Dbrowser=edge
        String browser = System.getProperty("browser") != null ? System.getProperty("browser")
                : getEnvironmentValue("environment", "browser");
        LogsUtils.info("Browser is: " + System.getProperty("browser"));
        setupDriver(browser);
        // setupDriver(DataUtils.getEnvironmentValue("environment", "browser"));
        getDriver().navigate().to(DataUtils.getEnvironmentValue("environment", "BASE_URL"));
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(alwaysRun = true)
    public void validLoginTC() throws IOException {
        new P01_loginPage(getDriver())
                .enterUsername(getJsonData("validLoginData", "username"))
                .enterPassword(getJsonData("validLoginData", "password"))
                .clickOnLoginButton();
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }


}
