package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private final By acceptCookiesButton = By.cssSelector("#wt-cli-accept-all-btn");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void acceptCookies() {
        try {
            if (waitForElementVisible(acceptCookiesButton).isDisplayed()) {
                click(acceptCookiesButton);
            }
        } catch (Exception e) {
            // Cookie popup görünmeyebilir, bu durumu handle ediyoruz
            System.out.println("Cookie popup not found or already accepted");
        }
    }

    protected void click(By locator) {
        waitForElementClickable(locator).click();
    }

    protected void sendKeys(By locator, String text) {
        waitForElementVisible(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        return waitForElementVisible(locator).getText();
    }
}