package pl.tomaszbuga.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.framework.PageObject;

public class LoginPage extends PageObject {
    private String baseUrl = "https://www.autohero.com/pl/search/";

    @FindBy(css = "[data-qa-selector='cookie-consent-configure']")
    private WebElement cookieConsentButton;

    @FindBy(id = "transmissionFilter")
    private WebElement transmissionFilter;

    @FindBy(css = "[data-qa-selector-value='automatic']")
    private WebElement automaticField;

    @FindBy(xpath = "//h2[@data-qa-selector='title']")
    private WebElement title;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get(baseUrl);
    }

    public LoginPage fillFormAndClickSubmit() {
        cookieConsentButton.click();
        cookieConsentButton.click();
        System.out.println(title.getText());
        transmissionFilter.click();
        automaticField.click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(title.getText());
        return this;
    }

}
