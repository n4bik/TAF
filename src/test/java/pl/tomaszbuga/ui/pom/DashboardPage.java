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

    @Step("Enter required data and press Create button")
    public BoardPage enterRequiredData() {
        log.info("Enter required data and press Create button");
        clickOnWebElement(orangeBackgroundButton);
        enterTextToInput(boardTitleInput, "TST-A Board");
        clickOnWebElement(visibilityDropdown);
        clickOnWebElement(privateVisibilityOption);
        clickOnWebElement(createBoardSubmitButton);
        return new BoardPage(driver);
    }
}
