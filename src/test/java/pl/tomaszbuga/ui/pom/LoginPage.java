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
public class LoginPage extends PageObject {
    @FindBy(id = "googleButton")
    private WebElement loginViaGoogleButton;

    @FindBy(id = "user")
    private WebElement usernameField;

    @FindBy(id = "login")
    private WebElement continueButton;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(id= "login-submit")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        String pageObjectName = this.getClass().getSimpleName();

        this.driver = driver;
        PageFactory.initElements(driver, this);
        isPageLoaded(pageObjectName, loginViaGoogleButton);
    }
    @Step("Click login via Google button")
    public LoginPage clickLoginViaGoogleButton() {
        log.info("Click login via Google button");
        clickOnWebElement(loginViaGoogleButton);
        return this;
    }

    @Step("Enter username")
    public LoginPage enterUsername() {
        log.info("Enter username: " + username);
        enterTextToInput(usernameField, username);
        return this;
    }

    @Step("Click continue button")
    public LoginPage clickContinueButton() {
        log.info("Click continue button");
        clickOnWebElement(continueButton);
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword() {
        log.info("Enter password: " + pass);
        enterTextToInput(passwordField, pass);
        return this;
    }

    @Step("Click login button")
    public DashboardPage clickLoginButton() {
        log.info("Click login button");
        clickOnWebElement(loginButton);
        return new DashboardPage(driver);
    }
}
