package pl.tomaszbuga.ui.pom;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.ui.framework.PageObject;

@Log4j2
public class HomePage extends PageObject {
    @FindBy(linkText = "Log in")
    private WebElement loginButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Open Trello home page")
    public HomePage openHomePage() {
        log.info("Open Trello home page");
        driver.get("https://trello.com/");
        return this;
    }

    @Step("Click login button")
    public LoginPage clickLoginButton() {
        log.info("Click login button");
        clickOnWebElement(loginButton);
        return new LoginPage(driver);
    }
}
