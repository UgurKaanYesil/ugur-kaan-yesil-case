package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    // Güncellenmiş locator'lar
    private final By insiderLogo = By.cssSelector("a[href='https://useinsider.com/']");
    private final By acceptCookiesButton = By.cssSelector("#wt-cli-accept-all-btn");
    // Platform menüsü için güncellenmiş locator
    private final By platformMenu = By.cssSelector("#navbarDropdownMenuLink");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoDisplayed() {
        return waitForElementVisible(insiderLogo).isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
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

    public boolean isPlatformMenuDisplayed() {
        try {
            return waitForElementVisible(platformMenu).isDisplayed();
        } catch (Exception e) {
            System.out.println("Platform menu not found with current locator");
            return false;
        }
    }
}