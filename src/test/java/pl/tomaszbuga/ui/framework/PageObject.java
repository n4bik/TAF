package pl.tomaszbuga.ui.framework;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static pl.tomaszbuga.ui.utils.ByLocatorFinder.getByFromWebElement;

@Log4j2
public abstract class PageObject {
    private static final Duration TIMEOUT = Duration.ofSeconds(5);
    protected WebDriver driver;
    protected void isPageLoaded(String pageObjectName, WebElement... validationElements) {
        log.info("Check if {} is loaded successfully", pageObjectName);
        List<String> notLoadedElements = getNotLoadedElements(validationElements);

        if (notLoadedElements.size() > 0) {
            throw new AssertionError(pageObjectName
                    + " page not loaded properly. The following elements are invisible:\n"
                    + StringUtils.join(notLoadedElements, "\n")
            );
        }
    }

    protected List<String> getNotLoadedElements(WebElement... elements) {
        List<String> result = new ArrayList<>();
        for (WebElement element : elements) {
            try {
                waitUntilElementIsVisible(element);
            } catch (TimeoutException e) {
                result.add(element.toString());
            }
        }
        return result;
    }

    protected final WebElement waitUntilElementIsVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        By elementByLocator = getByFromWebElement(element);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementByLocator));
    }

    protected final WebElement waitUntilElementIsClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        By elementByLocator = getByFromWebElement(element);
        return wait.until(ExpectedConditions.elementToBeClickable(elementByLocator));
    }

    protected void clickOnWebElement(WebElement clickableElement) {
        waitUntilElementIsClickable(clickableElement);
        clickableElement.click();
    }

    protected void enterTextToInput(WebElement input, String text) {
        waitUntilElementIsClickable(input);
        input.sendKeys(text);
    }

    protected void enterTextToTextareaInput(WebElement textareaInput, String text) {
        waitUntilElementIsClickable(textareaInput);
        enterTextToInput(textareaInput, text);
    }

    protected String getTextFromInput(WebElement element) {
        waitUntilElementIsClickable(element);
        return element.getAttribute("value");
    }

    protected String getText(WebElement element) {
        waitUntilElementIsClickable(element);
        return element.getText();
    }

    protected void clearTextInput(WebElement input) {
        waitUntilElementIsClickable(input);
        Keys modifier = SystemUtils.IS_OS_MAC ? Keys.COMMAND : Keys.CONTROL;

        input.sendKeys(modifier + "A");
        input.sendKeys(Keys.DELETE);

        input.clear();
    }

    protected void selectValueFromDropdown(WebElement dropdown, String valueToSelect) {
        Select select = new Select(dropdown);

        log.info("Searching for '" + valueToSelect + "' in dropdown");
        try {
            select.selectByVisibleText(valueToSelect);
        } catch (NoSuchElementException ex) {
            log.warn("There is no value '" + valueToSelect + "' in dropdown");
        }
    }

    protected String getSelectedValueFromDropdown(WebElement dropdown) {
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }
}
