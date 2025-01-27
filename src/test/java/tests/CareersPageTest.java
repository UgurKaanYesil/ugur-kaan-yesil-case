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

    @Test
    public void testQAJobsFiltering() {
        try {
            driver.get("https://useinsider.com/careers/quality-assurance/");
            driver.manage().window().maximize();

            CareersPage careersPage = new CareersPage(driver);

            careersPage.acceptCookies();
            Thread.sleep(3000);

            careersPage.clickSeeAllQaJobs();
            Thread.sleep(3000);

            // Sadece lokasyon filtresi uygula
            careersPage.filterByLocation("Istanbul, Turkey");
            Thread.sleep(2000);

            assertTrue(careersPage.areJobsListDisplayed(),
                    "Jobs list should be displayed after filtering");

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test failed due to interruption: " + e.getMessage());
        }
    }

    @Test
    public void testQAJobsFilteringAndContent() {
        try {
            driver.get("https://useinsider.com/careers/quality-assurance/");
            driver.manage().window().maximize();

            CareersPage careersPage = new CareersPage(driver);

            careersPage.acceptCookies();
            Thread.sleep(3000);

            // See All QA Jobs butonuna tıkla
            careersPage.clickSeeAllQaJobs();
            Thread.sleep(3000);

            // Istanbul, Turkey lokasyonunu filtrele
            careersPage.filterByLocation("Istanbul, Turkey");
            Thread.sleep(2000);

            // İş ilanlarının içeriğini kontrol et
            assertTrue(careersPage.checkJobListings(),
                    "All jobs should contain 'Quality Assurance' in position and department, and 'Istanbul, Turkey' in location");

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test failed due to interruption: " + e.getMessage());
        }
    }

    @Test
    public void testViewRoleRedirection() {
        try {
            driver.get("https://useinsider.com/careers/quality-assurance/");
            driver.manage().window().maximize();

            CareersPage careersPage = new CareersPage(driver);

            careersPage.acceptCookies();
            Thread.sleep(3000);

            // See All QA Jobs butonuna tıkla
            careersPage.clickSeeAllQaJobs();
            Thread.sleep(3000);

            // Istanbul, Turkey lokasyonunu filtrele
            careersPage.filterByLocation("Istanbul, Turkey");
            Thread.sleep(2000);

            // View Role butonuna tıkla ve Lever URL'sini al
            String leverUrl = careersPage.clickViewRoleButton();
            Thread.sleep(3000);

            // Lever Application form sayfasının açıldığını kontrol et
            assertTrue(careersPage.isLeverApplicationFormDisplayed(),
                    "Lever Application form should be displayed");

            // URL'in Lever domain'inde olduğunu kontrol et
            assertTrue(driver.getCurrentUrl().startsWith(leverUrl),
                    "URL should match the Lever application URL");

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test failed due to interruption: " + e.getMessage());
        }
    }
}