package pl.tomaszbuga.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pl.tomaszbuga.utils.ByLocatorFinder.getByFromWebElement;

public abstract class PageObject {
    private static final Duration TIMEOUT = Duration.ofSeconds(5);
    protected static final Logger LOGGER = LogManager.getLogger(PageObject.class);
    protected WebDriver driver;

    protected void waitUntilElementIsVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        By elementByLocator = getByFromWebElement(element);
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementByLocator));
    }

    protected void waitUntilElementIsClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        By elementByLocator = getByFromWebElement(element);
        wait.until(ExpectedConditions.elementToBeClickable(elementByLocator));
    }
}
