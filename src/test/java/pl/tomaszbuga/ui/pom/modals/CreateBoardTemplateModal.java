package pl.tomaszbuga.ui.pom.modals;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.ui.pom.BoardPage;

@Log4j2
public class CreateBoardTemplateModal extends BaseCreateBoardModal<CreateBoardTemplateModal> {

    @FindBy(name = "keepCardsCheckbox")
    private WebElement keepCardsCheckbox;

    public CreateBoardTemplateModal(WebDriver driver) {
        String pageObjectName = this.getClass().getSimpleName();

        this.driver = driver;
        PageFactory.initElements(driver, this);
        isPageLoaded(pageObjectName, createBoardModalTitle, keepCardsCheckbox);
    }

    @Step("Enter required data and press Create button")
    public BoardPage enterRequiredData() {
        log.info("Enter required data and press Create button");
        return enterNewBoardTitle("TST-A Board")
                .expandVisibilityDropdown()
                .selectPrivateVisiblityOption()
                .setKeepCards(true)
                .clickCreateNewBoardButton();
    }

    @Step("Set keep cards checkbox to: {isKeepCards}")
    public CreateBoardTemplateModal setKeepCards(boolean isKeepCards) {
        log.info("Set keep cards checkbox to: {}", isKeepCards);
        if (isKeepCards && !keepCardsCheckbox.isSelected()) {
            keepCardsCheckbox.click();
        }

        return this;
    }
}
