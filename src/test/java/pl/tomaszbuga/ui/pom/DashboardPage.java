package pl.tomaszbuga.ui.pom;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.ui.framework.PageObject;
import pl.tomaszbuga.ui.pom.modals.CreateBoardModal;

@Log4j2
public class DashboardPage extends PageObject {
    @FindBy(css = "[data-testid='create-board-tile']")
    private WebElement createNewBoardButton;

    public DashboardPage(WebDriver driver) {
        String pageObjectName = this.getClass().getSimpleName();

        this.driver = driver;
        PageFactory.initElements(driver, this);
        isPageLoaded(pageObjectName, createNewBoardButton);
    }

    @Step("Click on Create new board button")
    public CreateBoardModal clickOnCreateNewBoardButton() {
        log.info("Click on Create new board button");
        clickOnWebElement(createNewBoardButton);
        return new CreateBoardModal(driver);
    }
}
