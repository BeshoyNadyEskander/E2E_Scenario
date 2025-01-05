package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class P06_finishingOrder {
    private final static By ThanksMessage = By.cssSelector("h2[data-test=\"complete-header\"]");
    private final WebDriver driver;

    public P06_finishingOrder(WebDriver driver) {

        this.driver = driver;
    }

    public String getThanksMessage() {
        return Utility.getText(driver, ThanksMessage);
    }

    public boolean checkOnVisiblityofMessage() {
        try {
            LogsUtils.info("check on message: " + getThanksMessage());
            Utility.generalWait(driver)
                    .until(ExpectedConditions
                            .visibilityOf(Utility.findWebElement(driver, ThanksMessage))).isDisplayed();
            return true;
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return false;
        }

    }
}
