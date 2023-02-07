package pl.tomaszbuga.ui.pom;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.ui.framework.PageObject;

import static pl.tomaszbuga.ui.utils.UserUtils.pass;
import static pl.tomaszbuga.ui.utils.UserUtils.username;

@Log4j2
public class DashboardPage extends PageObject {

    @FindBy(id= "someId")
    private WebElement somethingOnPage;

    public DashboardPage(WebDriver driver) {
        String pageObjectName = this.getClass().getSimpleName();

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @Step("Action to perform")
    public DashboardPage actionToPerform() {
        log.info("Action to perform");
        clickOnWebElement(somethingOnPage);
        return this;
    }
}
