package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class CareersPage extends BasePage {
    private final By locationsBlock = By.cssSelector("#career-find-our-calling");
    private final By teamsBlock = By.cssSelector("#career-find-our-calling");
    private final By lifeAtInsiderBlock = By.cssSelector("body > div.elementor.elementor-22610 > section.elementor-section.elementor-top-section.elementor-element.elementor-element-a8e7b90.elementor-section-full_width.elementor-section-height-default.elementor-section-height-default > div > div > div");
    private final By seeAllQaJobsButton = By.cssSelector("a[href='https://useinsider.com/careers/open-positions/?department=qualityassurance']");
    private final By jobsList = By.cssSelector(".position-list .position-list-item");
    private final By jobListContainer = By.id("jobs-list");
    private final By jobItems = By.cssSelector(".position-list-item-wrapper");
    private final By jobPositions = By.cssSelector(".position-title");
    private final By jobDepartments = By.cssSelector(".position-department");
    private final By jobLocations = By.cssSelector(".position-location");


    public CareersPage(WebDriver driver) {
        super(driver);
    }

    private void waitForPageLoad() {
        try {
            Thread.sleep(5000);
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

            String script = String.format(
                    "var select2 = $(\"select[data-select2-id='filter-by-location']\"); " +
                            "select2.val('%s').trigger('change');",
                    location
            );
            ((JavascriptExecutor) driver).executeScript(script);

            Thread.sleep(2000);
            waitForPageLoad();

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

    public boolean checkJobListings() {
        try {
            waitForPageLoad();
            Thread.sleep(3000); // Sayfa yüklenmesi için ek bekleme

            WebElement container = waitForElementVisible(jobListContainer);
            List<WebElement> jobListItems = driver.findElements(jobItems);

            if (jobListItems.isEmpty()) {
                System.out.println("No job listings found");
                return false;
            }

            System.out.println("Found " + jobListItems.size() + " job listings");

            for (WebElement jobItem : jobListItems) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                WebElement positionElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(jobItem, jobPositions));
                WebElement departmentElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(jobItem, jobDepartments));
                WebElement locationElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(jobItem, jobLocations));

                String position = positionElement.getText().trim();
                String department = departmentElement.getText().trim();
                String location = locationElement.getText().trim();

                System.out.println("\nChecking job listing:");
                System.out.println("Position: " + position);
                System.out.println("Department: " + department);
                System.out.println("Location: " + location);

                boolean positionCheck = position.toLowerCase().contains("qa") ||
                        position.toLowerCase().contains("quality assurance");
                boolean departmentCheck = department.equals("Quality Assurance");
                boolean locationCheck = location.equals("Istanbul, Turkey");

                if (!positionCheck || !departmentCheck || !locationCheck) {
                    System.out.println("❌ Job listing content doesn't match criteria");
                    if (!positionCheck) System.out.println("Position check failed");
                    if (!departmentCheck) System.out.println("Department check failed");
                    if (!locationCheck) System.out.println("Location check failed");
                    return false;
                }
                System.out.println("✓ Job listing matches all criteria");
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error checking job listings: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public String clickViewRoleButton() {
        try {
            waitForPageLoad();
            Thread.sleep(2000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.presenceOfElementLocated(jobListContainer));

            List<WebElement> jobItems = driver.findElements(By.cssSelector(".position-list-item-wrapper"));
            if (jobItems.isEmpty()) {
                throw new NoSuchElementException("No job items found");
            }

            Actions actions = new Actions(driver);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", jobItems.get(0));
            Thread.sleep(2000);

            actions.moveToElement(jobItems.get(0)).perform();
            Thread.sleep(1000);

            WebElement viewRoleBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector(".position-list-item-wrapper:hover .btn-navy")));

            String leverUrl = viewRoleBtn.getAttribute("href");

            js.executeScript("arguments[0].click();", viewRoleBtn);

            wait.until(driver -> driver.getWindowHandles().size() > 1);

            return leverUrl;

        } catch (Exception e) {
            System.out.println("Error in clickViewRoleButton: " + e.getMessage());
            throw new RuntimeException("Failed to click View Role button", e);
        }
    }

    public boolean isLeverApplicationFormDisplayed() {
        try {
            waitForPageLoad();
            return driver.findElement(By.cssSelector(".posting-header")).isDisplayed() &&
                    driver.getCurrentUrl().contains("jobs.lever.co");
        } catch (Exception e) {
            System.out.println("Error checking Lever application form: " + e.getMessage());
            return false;
        }
    }
}