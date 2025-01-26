package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class HomePage extends BasePage {

    private final By insiderLogo = By.cssSelector("a[href='https://useinsider.com/']");
    private final By platformMenu = By.cssSelector("#navbarDropdownMenuLink");

    //Careers Page locators
    private final By companyMenuItems = By.cssSelector("#navbarDropdownMenuLink");
    private final By careersLink = By.cssSelector("#navbarNavDropdown > ul:nth-child(1) > li.nav-item.dropdown.show > div > div.new-menu-dropdown-layout-6-mid-container > a:nth-child(2)");

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


    public boolean isPlatformMenuDisplayed() {
        try {
            return waitForElementVisible(platformMenu).isDisplayed();
        } catch (Exception e) {
            System.out.println("Platform menu not found with current locator");
            return false;
        }
    }

    public void navigateToCareers() {
        try {
            // Sayfanın yüklenmesi için kısa bir bekleme
            Thread.sleep(2000);

            // Company menüsünü bul (5. element)
            List<WebElement> menuItems = driver.findElements(companyMenuItems);
            WebElement companyMenu = menuItems.get(4); // 5. element (0-based index)

            // Company menüsüne tıkla
            Actions actions = new Actions(driver);
            actions.moveToElement(companyMenu).click().perform();

            // Menünün açılmasını bekle
            Thread.sleep(1000);

            // Careers linkine tıkla
            WebElement careers = waitForElementVisible(careersLink);
            actions.moveToElement(careers).click().perform();

            // Sayfa yüklenmesini bekle
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}