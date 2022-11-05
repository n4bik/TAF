package pl.tomaszbuga.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pl.tomaszbuga.framework.PageObject;

import java.util.NoSuchElementException;
public class SeleniumTrainingPage extends PageObject {
    private String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    @FindBy(css = "#my-text-id")
    private WebElement textInput;

    @FindBy(css = "[name='my-textarea']")
    private WebElement textareaInput;

    @FindBy(css = "[name='my-select']")
    private WebElement dropdownSelect;

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
        LOGGER.info("Getting text from Text input");
        return getTextFromInput(textInput);
    }

    public String getTextFromTextareaInput() {
        LOGGER.info("Getting text from Textarea input");
        return getTextFromInput(textareaInput);
    }

    public SeleniumTrainingPage clearTextInput() {
        LOGGER.info("Clearing Text input");
        textInput.clear();
        return this;
    }

    public SeleniumTrainingPage selectValueFromDropdown(String valueToSelect) {
        Select select = new Select(dropdownSelect);

        LOGGER.info("Searching for '" + valueToSelect + "' in dropdown");
        try {
            select.selectByVisibleText(valueToSelect);
        } catch (NoSuchElementException ex) {
            LOGGER.warn("There is no value '" + valueToSelect + "' in dropdown");
        }

        return this;
    }

    public String getSelectedValueFromDropdown() {
        LOGGER.info("Getting value from dropdown");
        Select select = new Select(dropdownSelect);
        return select.getFirstSelectedOption().getText();
    }
}
