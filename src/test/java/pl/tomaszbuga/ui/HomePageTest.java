package pl.tomaszbuga.ui;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.ui.framework.BaseTest;
import pl.tomaszbuga.ui.pom.HomePage;

public class HomePageTest extends BaseTest {
    HomePage homePage;

    @BeforeMethod
    private void setup() {
        homePage = new HomePage(getDriver());
    }

    @Test(groups = "Regression", description = "Verify that home page opens")
    void openHomePageTest() {
        homePage.openHomePage();
    }

    @Test(groups = {"Smoke", "Regression"}, description = "Verify user can click a search button")
    void clickSearchButtonTest() {
        homePage.openHomePage();
        homePage.clickSearchButton();
    }

}
