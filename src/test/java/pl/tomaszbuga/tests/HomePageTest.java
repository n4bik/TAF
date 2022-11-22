package pl.tomaszbuga.tests;

import org.testng.annotations.Test;
import pl.tomaszbuga.framework.BaseTest;
import pl.tomaszbuga.pom.HomePage;

public class HomePageTest extends BaseTest {
    HomePage homePage;

    @Test(groups = "Regression", description = "Verify that home page opens")
    void openHomePageTest() {
        homePage = new HomePage(getDriver());
        homePage.openHomePage();
    }

    @Test(groups = {"Smoke", "Regression"}, description = "Verify user can click a search button")
    void clickSearchButtonTest() {
        homePage = new HomePage(getDriver());
        homePage.openHomePage();
        homePage.clickSearchButton();
    }

}
