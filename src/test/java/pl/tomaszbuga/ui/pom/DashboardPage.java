package pl.tomaszbuga.ui.pom;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.ui.framework.PageObject;

@Log4j2
public class DashboardPage extends PageObject {
    @FindBy(css = "[data-testid='create-board-tile']")
    private WebElement createNewBoardButton;

    @FindBy(css = "[title='\uD83C\uDF51']")
    private WebElement orangeBackgroundButton;

    @FindBy(css = "[data-testid='create-board-title-input']")
    private WebElement boardTitleInput;

    @FindBy(css = ".css-191o3mb")
    private WebElement visibilityDropdown;

    @FindBy(css = "#react-select-2-option-0")
    private WebElement privateVisibilityOption;

    @FindBy(css = "[data-testid='create-board-submit-button']")
    private WebElement createBoardSubmitButton;

    public DashboardPage(WebDriver driver) {
        String pageObjectName = this.getClass().getSimpleName();

        this.driver = driver;
        PageFactory.initElements(driver, this);
        isPageLoaded(pageObjectName, createNewBoardButton);
    }

    @Step("Click on Create new board button")
    public DashboardPage clickOnCreateNewBoardButton() {
        log.info("Click on Create new board button");
        clickOnWebElement(createNewBoardButton);
        return this;
    }

    @Step("Select background: orange")
    public DashboardPage selectOrangeBackground() {
        log.info("Select background: orange");
        clickOnWebElement(orangeBackgroundButton);
        return this;
    }

    @Step("Enter new board title {newTitle}")
    public DashboardPage enterNewBoardTitle(String newTitle) {
        log.info("Enter new board title: {}", newTitle);
        enterTextToInput(boardTitleInput, newTitle);
        return this;
    }

    @Step("Expand visibility dropdown")
    public DashboardPage expandVisibilityDropdown() {
        log.info("Expand visibility dropdown");
        clickOnWebElement(visibilityDropdown);
        return this;
    }

    @Step("Select visibility: private")
    public DashboardPage selectPrivateVisiblityOption() {
        log.info("Select visibility: private");
        clickOnWebElement(privateVisibilityOption);
        return this;
    }

    @Step("Click on create board button")
    public BoardPage clickCreateNewBoardButton() {
        log.info("Click on create board button");
        clickOnWebElement(createBoardSubmitButton);
        return new BoardPage(driver);
    }

    @Step("Enter required data and press Create button")
    public BoardPage enterRequiredData() {
        log.info("Enter required data and press Create button");
        return selectOrangeBackground()
                .enterNewBoardTitle("TST-A Board")
                .expandVisibilityDropdown()
                .selectPrivateVisiblityOption()
                .clickCreateNewBoardButton();
    }
}
