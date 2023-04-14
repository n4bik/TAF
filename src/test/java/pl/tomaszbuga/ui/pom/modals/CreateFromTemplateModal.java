package pl.tomaszbuga.ui.pom.modals;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Log4j2
public class CreateFromTemplateModal extends BaseCreateBoardModal<CreateFromTemplateModal> {

    @FindBy(css = ".atlaskit-portal ul")
    private WebElement templatesList;

    @FindBy(css = ".atlaskit-portal ul li")
    private List<WebElement> templates;

    public CreateFromTemplateModal(WebDriver driver) {
        String pageObjectName = this.getClass().getSimpleName();

        this.driver = driver;
        PageFactory.initElements(driver, this);
        isPageLoaded(pageObjectName, createBoardModalTitle, templatesList);
    }

    @Step("Select template by index {templateIndex}")
    public CreateBoardTemplateModal selectTemplateByIndex(int templateIndex) {
        log.info("Select template by index {}", templateIndex);
        clickOnWebElement(templates.get(templateIndex));
        return new CreateBoardTemplateModal(driver);
    }
}
