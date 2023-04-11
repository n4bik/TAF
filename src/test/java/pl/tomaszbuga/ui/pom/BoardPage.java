package pl.tomaszbuga.ui.pom;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.ui.framework.PageObject;

@Log4j2
public class BoardPage extends PageObject {
    @FindBy(tagName = "h1")
    private WebElement boardTitle;

    @FindBy(css = "[aria-label='Show menu']")
    private WebElement ellipsisButton;

    @FindBy(css = ".js-open-more")
    private WebElement showMoreActionsButton;

    @FindBy(css = ".js-close-board")
    private WebElement closeBoardButton;

    @FindBy(css = ".js-confirm")
    private WebElement confirmCloseButton;

    @FindBy(css = "[data-testid='close-board-delete-board-button']")
    private WebElement permanentlyRemoveBoardButton;

    @FindBy(css = "[data-testid='close-board-delete-board-confirm-button']")
    private WebElement confirmPermanentlyRemoveButton;

    public BoardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Get board title")
    public String getBoardTitle() {
        waitUntilElementIsVisible(boardTitle);
        String boardTitleText = boardTitle.getText();
        log.info("Get board title: " + boardTitleText);
        return boardTitleText;
    }

    @Step("Open board menu")
    public BoardPage openBoardMenu() {
        log.info("Open board menu");
        clickOnWebElement(ellipsisButton);
        return this;
    }

    @Step("Click show more actions button")
    public BoardPage clickShowMoreActionsButton() {
        log.info("Click show more actions button");
        clickOnWebElement(showMoreActionsButton);
        return this;
    }

    @Step("Click close board button")
    public BoardPage clickCloseBoardButton() {
        log.info("Click close board button");
        clickOnWebElement(closeBoardButton);
        return this;
    }

    @Step("Click confirm close button")
    public BoardPage clickConfirmCloseButton() {
        log.info("Click confirm close button");
        clickOnWebElement(confirmCloseButton);
        return this;
    }

    @Step("Click permanently remove board button")
    public BoardPage clickPermanentlyRemoveBoardButton() {
        log.info("Click permanently remove board button");
        clickOnWebElement(permanentlyRemoveBoardButton);
        return this;
    }

    @Step("Click confirm permanently remove button")
    public DashboardPage clickConfirmPermanentlyRemoveButton() {
        log.info("Click confirm permanently remove button");
        clickOnWebElement(confirmPermanentlyRemoveButton);
        return new DashboardPage(driver);
    }

    @Step("Remove board")
    public DashboardPage removeBoard() {
        return openBoardMenu()
                .clickShowMoreActionsButton()
                .clickCloseBoardButton()
                .clickConfirmCloseButton()
                .clickPermanentlyRemoveBoardButton()
                .clickConfirmPermanentlyRemoveButton();
    }
}
