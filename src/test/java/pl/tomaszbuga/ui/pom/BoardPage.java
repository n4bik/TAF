package pl.tomaszbuga.ui.pom;

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

    @FindBy(partialLinkText = "More")
    private WebElement showMoreActionsButton;

    @FindBy(partialLinkText = "Close board")
    private WebElement closeBoardButton;

    @FindBy(css = "input[value='Close']")
    private WebElement confirmCloseButton;

    @FindBy(css = "[data-testid='close-board-delete-board-button']")
    private WebElement permanentlyRemoveBoardButton;

    @FindBy(css = "[data-testid='close-board-delete-board-confirm-button']")
    private WebElement confirmPermanentlyRemoveButton;

    public BoardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getBoardTitle() {
        waitUntilElementIsVisible(boardTitle);
        return boardTitle.getText();
    }

    public DashboardPage removeBoard() {
        clickOnWebElement(ellipsisButton);
        clickOnWebElement(showMoreActionsButton);
        clickOnWebElement(closeBoardButton);
        clickOnWebElement(confirmCloseButton);
        clickOnWebElement(permanentlyRemoveBoardButton);
        clickOnWebElement(confirmPermanentlyRemoveButton);
        return new DashboardPage(driver);
    }
}
