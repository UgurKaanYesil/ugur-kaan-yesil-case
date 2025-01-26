package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import static org.testng.Assert.*;

public class HomePageTest extends BaseTest {

    @Test
    public void testHomePageIsOpened() {
        // Test verileri
        String expectedUrl = "https://useinsider.com/";
        String expectedTitle = "#1 Leader in Individualized, Cross-Channel CX — Insider";

        // Ana sayfaya git
        driver.get(expectedUrl);
        HomePage homePage = new HomePage(driver);

        try {
            // Cookie'leri kabul et
            homePage.acceptCookies();

            // Sayfa yüklenmesi için kısa bir bekleme
            Thread.sleep(2000);

            // Assertions
            assertTrue(homePage.isLogoDisplayed(), "Insider logo should be displayed");
            assertTrue(driver.getCurrentUrl().contains(expectedUrl), "URL should contain expected URL");
            assertEquals(homePage.getPageTitle(), expectedTitle, "Page title should match expected title");
            assertTrue(homePage.isPlatformMenuDisplayed(), "Platform menu should be displayed");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}