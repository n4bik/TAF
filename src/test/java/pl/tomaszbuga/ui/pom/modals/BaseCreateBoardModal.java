package pl.tomaszbuga.ui.pom.modals;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.tomaszbuga.ui.framework.PageObject;
import pl.tomaszbuga.ui.pom.BoardPage;

@Log4j2
public abstract class BaseCreateBoardModal<T> extends PageObject {
    @FindBy(css = ".atlaskit-portal-container header h2")
    WebElement createBoardModalTitle;

    @FindBy(css = "[data-testid='create-board-title-input']")
    WebElement boardTitleInput;

    @FindBy(css = ".css-191o3mb")
    WebElement visibilityDropdown;

    @FindBy(css = "#react-select-2-option-0")
    WebElement privateVisibilityOption;

    @FindBy(css = "[data-testid='create-board-submit-button']")
    private WebElement createBoardSubmitButton;

    @Step("Get title of create board modal window")
    public String getCreateBoardModalTitle() {
        log.info("Get title of create board modal window");
        return getText(createBoardModalTitle);
    }

    @Step("Enter new board title {newTitle}")
    public T enterNewBoardTitle(String newTitle) {
        log.info("Enter new board title: {}", newTitle);
        clearTextInput(boardTitleInput);
        enterTextToInput(boardTitleInput, newTitle);
        return (T) this;
    }

    @Step("Expand visibility dropdown")
    public T expandVisibilityDropdown() {
        log.info("Expand visibility dropdown");
        clickOnWebElement(visibilityDropdown);
        return (T) this;
    }

    @Step("Select visibility: private")
    public T selectPrivateVisiblityOption() {
        log.info("Select visibility: private");
        clickOnWebElement(privateVisibilityOption);
        return (T) this;
    }

    @Step("Click on create board button")
    public BoardPage clickCreateNewBoardButton() {
        log.info("Click on create board button");
        clickOnWebElement(createBoardSubmitButton);
        return new BoardPage(driver);
    }
}
