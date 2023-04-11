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

    @Test
    public void addNewBoardTest() {
        String expectedTitle = "TST-A Board";

        String boardTitleFromPage = homePage
                .clickLoginButton()
                .enterUsername()
                .clickContinueButton()
                .enterPassword()
                .clickLoginButton()
                .clickOnCreateNewBoardButton()
                .enterRequiredData()
                .getBoardTitle();

        assertEquals(boardTitleFromPage, expectedTitle);

        BoardPage boardPage = new BoardPage(getDriver());
        boardPage.removeBoard();
    }
}
