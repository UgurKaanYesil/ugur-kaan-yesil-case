package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CareersPage extends BasePage {
    // Güncellenmiş locator'lar
    private final By locationsBlock = By.cssSelector("#career-find-our-calling");
    private final By teamsBlock = By.cssSelector("#career-find-our-calling");
    private final By lifeAtInsiderBlock = By.cssSelector("body > div.elementor.elementor-22610 > section.elementor-section.elementor-top-section.elementor-element.elementor-element-a8e7b90.elementor-section-full_width.elementor-section-height-default.elementor-section-height-default > div > div > div");

    // Yeni locator'lar
    private final By seeAllQaJobsButton = By.cssSelector("a[href='https://useinsider.com/careers/open-positions/?department=qualityassurance']");
    private final By jobsList = By.cssSelector(".position-list .position-list-item");
    private final By locationFilterDropdown = By.cssSelector("#select2-filter-by-location-container");
    private final By locationOptions = By.cssSelector("li.select2-results__option");

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

    public void clickSeeAllQaJobs() {
        try {
            waitForPageLoad();
            scrollToElement(seeAllQaJobsButton);
            click(seeAllQaJobsButton);
            waitForPageLoad();
        } catch (Exception e) {
            System.out.println("Error clicking See All QA Jobs button: " + e.getMessage());
            throw e;
        }
    }

    public void filterByLocation(String location) throws InterruptedException {
        try {
            waitForPageLoad();

            // Dropdown'ı aç
            WebElement dropdown = waitForElementClickable(locationFilterDropdown);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
            Thread.sleep(2000);

            // Select2 dropdown açıldıktan sonra seçenekleri bul
            List<WebElement> options = driver.findElements(locationOptions);

            // Seçenekler arasında Istanbul, Turkey'i bul ve tıkla
            for (WebElement option : options) {
                if (option.getText().contains("Istanbul, Turkey")) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
                    break;
                }
            }

            waitForPageLoad();
            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println("Error filtering by location: " + e.getMessage());
            throw e;
        }
    }


    public boolean areJobsListDisplayed() {
        try {
            waitForPageLoad();
            return !driver.findElements(jobsList).isEmpty();
        } catch (Exception e) {
            System.out.println("Error checking jobs list: " + e.getMessage());
            return false;
        }
    }

    private void scrollToElement(By locator) {
        try {
            WebElement element = waitForElementVisible(locator);
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                    element
            );
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Error scrolling to element: " + e.getMessage());
        }
    }
}