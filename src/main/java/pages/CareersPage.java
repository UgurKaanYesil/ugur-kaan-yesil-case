package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CareersPage extends BasePage {
    // Güncellenmiş locator'lar
    private final By locationsBlock = By.cssSelector("#career-find-our-calling");
    private final By teamsBlock = By.cssSelector("#career-find-our-calling");
    private final By lifeAtInsiderBlock = By.cssSelector("body > div.elementor.elementor-22610 > section.elementor-section.elementor-top-section.elementor-element.elementor-element-a8e7b90.elementor-section-full_width.elementor-section-height-default.elementor-section-height-default > div > div > div");

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    private void waitForPageLoad() {
        try {
            Thread.sleep(5000); // Sayfa yüklenmesi için bekle
            new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState").equals("complete"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void scrollToElement(By locator) {
        try {
            waitForPageLoad();

            // Elementi bul
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));

            // Smooth scroll
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                    element
            );

            Thread.sleep(1000); // Scroll animasyonu için bekle

        } catch (Exception e) {
            System.out.println("Error scrolling to element: " + e.getMessage());
        }
    }

    public boolean isLocationsBlockDisplayed() {
        try {
            scrollToElement(locationsBlock);
            return new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.visibilityOfElementLocated(locationsBlock))
                    .isDisplayed();
        } catch (Exception e) {
            System.out.println("Locations block not found: " + e.getMessage());
            return false;
        }
    }

    public boolean isTeamsBlockDisplayed() {
        try {
            scrollToElement(teamsBlock);
            return new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.visibilityOfElementLocated(teamsBlock))
                    .isDisplayed();
        } catch (Exception e) {
            System.out.println("Teams block not found: " + e.getMessage());
            return false;
        }
    }

    public boolean isLifeAtInsiderBlockDisplayed() {
        try {
            scrollToElement(lifeAtInsiderBlock);
            return new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.visibilityOfElementLocated(lifeAtInsiderBlock))
                    .isDisplayed();
        } catch (Exception e) {
            System.out.println("Life at Insider block not found: " + e.getMessage());
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}