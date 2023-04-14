package pl.tomaszbuga.ui.pom.modals;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.ui.pom.BoardPage;

@Log4j2
public class CreateBoardModal extends BaseCreateBoardModal<CreateBoardModal> {

    @FindBy(css = "[title='\uD83C\uDF51']")
    private WebElement orangeBackgroundButton;

    @FindBy(css = "[data-testid='create-from-template-button']")
    private WebElement startWithATemplateButton;

    public CreateBoardModal(WebDriver driver) {
        String pageObjectName = this.getClass().getSimpleName();

        this.driver = driver;
        PageFactory.initElements(driver, this);
        isPageLoaded(pageObjectName, createBoardModalTitle);
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

    @Step("Select background: orange")
    public CreateBoardModal selectOrangeBackground() {
        log.info("Select background: orange");
        clickOnWebElement(orangeBackgroundButton);
        return this;
    }

    @Step("Click 'Start with a template' button")
    public CreateFromTemplateModal clickStartWithATemplateButton() {
        log.info("Click 'Start with a template' button");
        clickOnWebElement(startWithATemplateButton);
        return new CreateFromTemplateModal(driver);
    }
}
