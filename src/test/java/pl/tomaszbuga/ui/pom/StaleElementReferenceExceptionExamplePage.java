package pl.tomaszbuga.ui.pom;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.ui.framework.PageObject;
@Log4j2
public class StaleElementReferenceExceptionExamplePage extends PageObject {
    @FindBy(id = "task-table-filter")
    private WebElement filterInputFindByAnnotation;

    public StaleElementReferenceExceptionExamplePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get("https://www.lambdatest.com/selenium-playground/table-search-filter-demo");
    }

    public StaleElementReferenceExceptionExamplePage forceException() {
        WebElement filterInput = driver.findElement(By.id("task-table-filter"));
        filterInput.sendKeys("in progress");
        driver.navigate().refresh();
        filterInput.sendKeys("in progress");
        return this;
    }

    public StaleElementReferenceExceptionExamplePage fixedExceptionWithFindElement() {
        WebElement filterInput = driver.findElement(By.id("task-table-filter"));
        filterInput.sendKeys("in progress");
        driver.navigate().refresh();
        filterInput = driver.findElement(By.id("task-table-filter"));
        filterInput.sendKeys("in progress");
        return this;
    }

    public StaleElementReferenceExceptionExamplePage fixedExceptionWithWait() {
        WebElement filterInput = driver.findElement(By.id("task-table-filter"));
        filterInput.sendKeys("in progress");
        driver.navigate().refresh();
        filterInput = waitUntilElementIsClickable(filterInput);
        filterInput.sendKeys("in progress");
        return this;
    }

    public StaleElementReferenceExceptionExamplePage fixedExceptionWithFindBy() {
        filterInputFindByAnnotation.sendKeys("in progress");
        driver.navigate().refresh();
        filterInputFindByAnnotation.sendKeys("in progress");
        return this;
    }
}
