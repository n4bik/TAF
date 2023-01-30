package pl.tomaszbuga.ui.framework;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pl.tomaszbuga.ui.utils.ByLocatorFinder.getByFromWebElement;

@Log4j2
public abstract class PageObject {
    private static final Duration TIMEOUT = Duration.ofSeconds(5);
    protected WebDriver driver;

    protected final void waitUntilElementIsVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        By elementByLocator = getByFromWebElement(element);
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementByLocator));
    }

    protected final void waitUntilElementIsClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        By elementByLocator = getByFromWebElement(element);
        wait.until(ExpectedConditions.elementToBeClickable(elementByLocator));
    }

    protected void enterTextToInput(String text, WebElement element) {
        waitUntilElementIsClickable(element);
        element.sendKeys(text);
    }

    protected String getTextFromInput(WebElement element) {
        waitUntilElementIsVisible(element);
        // .getAttribute("value"), bo wartośc wpisana w input
        // nie jest dostępna do pobrania przez .getText()
        return element.getAttribute("value");
    }
}
