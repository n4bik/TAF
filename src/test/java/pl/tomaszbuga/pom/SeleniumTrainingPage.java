package pl.tomaszbuga.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.framework.PageObject;

public class SeleniumTrainingPage extends PageObject {
    private String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    @FindBy(css = "#my-text-id")
    private WebElement textInput;

    @FindBy(css = "[name='my-textarea']")
    private WebElement textareaInput;

    @FindBy(css = "[name='my-password']")
    private WebElement passwordInput;

    public SeleniumTrainingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SeleniumTrainingPage openPage() {
        driver.get(baseUrl);
        return this;
    }

    public SeleniumTrainingPage enterTextToTextInput(String text) {
        enterTextToInput(text, textInput);
        return this;
    }

    public SeleniumTrainingPage enterTextToTextareaInput(String text) {
        enterTextToInput(text, textareaInput);
        return this;
    }

    public String getTextFromTextInput() {
        return getTextFromInput(textInput);
    }

    public String getTextFromTextareaInput() {
        return getTextFromInput(textareaInput);
    }

    public SeleniumTrainingPage clearTextInput() {
        textInput.clear();
        return this;
    }
    // HomeworkTest 22/10/22 ----------------------------------
    public SeleniumTrainingPage enterPasswordToInput(String text) {
        enterTextToInput(text, passwordInput);
        return this;
    }

    public String getPasswordFromInput() {
        return getTextFromInput(passwordInput);
    }

    public boolean getPasswordAttribute(){
        return getPasswordAttributeType(passwordInput);
    }
    // ---------------------------------------------------------
}
