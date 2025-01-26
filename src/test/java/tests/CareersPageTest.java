package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.CareersPage;
import static org.testng.Assert.*;

public class CareersPageTest extends BaseTest {

    @Test
    public void testCareersPageBlocks() {
        try {
            // Ana sayfaya git ve maximize et
            driver.get("https://useinsider.com/");
            driver.manage().window().maximize();

            HomePage homePage = new HomePage(driver);

            // Cookie'leri kabul et ve sayfanın yüklenmesini bekle
            homePage.acceptCookies();
            Thread.sleep(2000);

            // Careers sayfasına git
            homePage.navigateToCareers();
            Thread.sleep(5000); // Careers sayfasının yüklenmesi için yeterli süre bekle

            CareersPage careersPage = new CareersPage(driver);

            // URL kontrolü
            String currentUrl = careersPage.getCurrentUrl();
            assertTrue(currentUrl.contains("careers"),
                    "Should be on the careers page. Current URL: " + currentUrl);

            // Blokların görünürlük kontrolleri
            assertTrue(careersPage.isLocationsBlockDisplayed(),
                    "Locations block should be displayed");

            assertTrue(careersPage.isTeamsBlockDisplayed(),
                    "Teams block should be displayed");

            assertTrue(careersPage.isLifeAtInsiderBlockDisplayed(),
                    "Life at Insider block should be displayed");

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test failed due to interruption: " + e.getMessage());
        }
    }
}