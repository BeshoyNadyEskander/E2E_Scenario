package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driverThreadLocal= new ThreadLocal<>();

    public static void  setupDriver(String browser)
    {
        switch (browser.toLowerCase())
        {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driverThreadLocal.set(new ChromeDriver(chromeOptions));
                break;
            case "firefox":
                driverThreadLocal.set(new FirefoxDriver());
                break;

            default:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driverThreadLocal.set(new EdgeDriver(edgeOptions));
        }
    }

    // return driver
    public  static WebDriver getDriver()
    {
        return  driverThreadLocal.get();
    }

    // create quit driver
    public  static  void quitDriver()
    {
        getDriver().quit();
        driverThreadLocal.remove(); // remove list thread driver
    }
}
