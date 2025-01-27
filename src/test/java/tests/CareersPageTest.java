package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.CareersPage;
import static org.testng.Assert.*;

public class CareersPageTest extends BaseTest {

    @Test
    public void testCareersPageBlocks() {
        try {
            driver.get("https://useinsider.com/");
            driver.manage().window().maximize();

            HomePage homePage = new HomePage(driver);

            homePage.acceptCookies();
            Thread.sleep(2000);

            homePage.navigateToCareers();
            Thread.sleep(5000);

            CareersPage careersPage = new CareersPage(driver);

            String currentUrl = careersPage.getCurrentUrl();
            assertTrue(currentUrl.contains("careers"),
                    "Should be on the careers page. Current URL: " + currentUrl);

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

            careersPage.clickSeeAllQaJobs();
            Thread.sleep(3000);

            careersPage.filterByLocation("Istanbul, Turkey");
            Thread.sleep(2000);

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

            careersPage.clickSeeAllQaJobs();
            Thread.sleep(3000);

            careersPage.filterByLocation("Istanbul, Turkey");
            Thread.sleep(2000);

            String leverUrl = careersPage.clickViewRoleButton();
            Thread.sleep(3000);

            assertTrue(careersPage.isLeverApplicationFormDisplayed(),
                    "Lever Application form should be displayed");

            assertTrue(driver.getCurrentUrl().startsWith(leverUrl),
                    "URL should match the Lever application URL");

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Test failed due to interruption: " + e.getMessage());
        }
    }
}