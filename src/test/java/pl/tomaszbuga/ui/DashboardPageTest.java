package pl.tomaszbuga.ui;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.ui.framework.BaseTest;
import pl.tomaszbuga.ui.pom.BoardPage;
import pl.tomaszbuga.ui.pom.HomePage;

import static org.testng.Assert.assertEquals;

public class DashboardPageTest extends BaseTest {
    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    private void setup() {
        homePage = new HomePage(getDriver());
        homePage.openHomePage();
    }

    @Test(groups = {"Smoke", "Regression"})
    public void addNewBoardTest() {
        String expectedTitle = "TST-A Board";

        String boardTitleFromPage = homePage
                .clickLoginButton()
                .enterUsername()
                .clickContinueButton()
                .enterPassword()
                .clickLoginButton()
                .clickOnCreateNewBoardButton()
                .clickStartWithATemplateButton()
                .selectTemplateByIndex(0)
                .enterRequiredData()
                .getBoardTitle();

        assertEquals(boardTitleFromPage, expectedTitle);

        BoardPage boardPage = new BoardPage(getDriver());
        boardPage.removeBoard();
    }
}
