package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

import static Utilities.Utility.generalWait;

public class P02_landingPage {
    private static final By titleHomePageLocator = By.cssSelector("div[class=\"app_logo\"]");
    private static final By addToCartButtonForAllProducts = By.xpath("//button[contains(@class,\"btn_inventory \")]");
    private static final By NumberOfProductOnCartIconButton = By.xpath("//span[@class=\"shopping_cart_badge\"]");
    private static final By NumberOfSelectedProduct = By.xpath("//button[.=\"Remove\"]");
    private static final By cartIcon = By.cssSelector("a[class=shopping_cart_link]");
    static Double totalPrice = 0.0;
    private static List<WebElement> allProducts;
    private static List<WebElement> selectedProducts;
    private final WebDriver driver;


    public P02_landingPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getCartIcon() {
        // getter to could access locator cart icon (private) and can call this method into iinvoked to take full screen
        return NumberOfProductOnCartIconButton;
    }

    public boolean assertTitleHomePage(String expeectedValue) {
        return Utility.getText(driver, titleHomePageLocator).equals(expeectedValue);
    }

    public P02_landingPage addAllProductsToCart() {

        allProducts = driver.findElements(addToCartButtonForAllProducts);
        LogsUtils.info("size of products: " + allProducts.size());
        for (int i = 1; i <= allProducts.size(); i++) {
            By addToCartButtonForAllProducts = By.xpath("(//button[contains(@class,\"btn_inventory \")])[" + i + "]"); //dyn locator
            Utility.clickingOnElement(driver, addToCartButtonForAllProducts);
        }
        return this;
    }


    public String getNumberOfProductOnCartIcon() {

        try {
            LogsUtils.info("number of products in art icon: " + Utility.getText(driver, NumberOfProductOnCartIconButton));
            return Utility.getText(driver, NumberOfProductOnCartIconButton); // if selected prodcut in cart icon

        } catch (Exception e) {
            LogsUtils.error(e.getMessage()); // error if didn't selected product
            return "didn't selected product size = 0";
        }

    }

    public String getNumberOfSelectedProducts() {
        try {

            selectedProducts = driver.findElements(NumberOfSelectedProduct);
            LogsUtils.info("number of selected product : " + (selectedProducts.size()));
            return String.valueOf(selectedProducts.size());
        } catch (Exception e) {
            LogsUtils.error(e.getMessage()); // error if didn't add products
            return "didn't add product size = 0";
        }

    }


    public P02_landingPage addRandomProductsIntoCart(int numberProductsNeedToSelect) {

        Set<Integer> randomProducts = Utility.selectedRandomUniqueNumber(numberProductsNeedToSelect,
                driver.findElements(addToCartButtonForAllProducts));
        LogsUtils.info("total number of selected random products " + randomProducts.size());
        for (int random : randomProducts) {
            LogsUtils.info("number random of products: " + random);
            By addToCartButtonRandomlProducts = By.xpath("(//button[contains(@class,\"btn_inventory \")])[" + random + "]"); //dyn locator
            Utility.clickingOnElement(driver, addToCartButtonRandomlProducts);
            //LogsUtils.info("name of selected products " + Utility.getText(driver, addToCartButtonRandomlProducts));
        }
        return this;
    }

    public boolean comparingNumberOfSelectedProductsWithCart() {
        return getNumberOfProductOnCartIcon().equals(getNumberOfSelectedProducts());
    }

    public P03_cartPage clickOnCartIcon() {
        Utility.clickingOnElement(driver, cartIcon);
        return new P03_cartPage(driver);
    }

    public boolean verifyOnCartPageUrl(String expectedUrl) {
        try {
            LogsUtils.info(expectedUrl);
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedUrl));
            LogsUtils.info(driver.getCurrentUrl());
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return false;
        }

        return true;
    }

    public String getTotalPriceOfSelectedProducts() {

        try {
            List<WebElement> priceOfselectedProducts = driver.findElements(By.xpath("//button[.=\"Remove\"]//preceding-sibling::div[@class=\"inventory_item_price\"]"));
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
}
