package pl.tomaszbuga.ui.pom;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.ui.framework.PageObject;
@Log4j2
public class StaleElementReferenceExceptionExamplePage extends PageObject {
    @FindBy(name = "q")
    private WebElement searchFieldInput;

    public StaleElementReferenceExceptionExamplePage(WebDriver driver) {
        String pageObjectName = this.getClass().getSimpleName();

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public StaleElementReferenceExceptionExamplePage forceException() {
        driver.get("https://github.com/");
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("test");
        search.sendKeys(Keys.ENTER);
        search.sendKeys("test");
        return this;
    }

    public StaleElementReferenceExceptionExamplePage fixedExceptionWithFindElement() {
        driver.get("https://github.com/");
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("test");
        search.sendKeys(Keys.ENTER);
        search = driver.findElement(By.name("q"));
        search.sendKeys("test");
        return this;
    }

    public StaleElementReferenceExceptionExamplePage fixedExceptionWithWait() {
        driver.get("https://github.com/");
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("test");
        search.sendKeys(Keys.ENTER);
        waitUntilElementIsClickableAndReturn(search)
                .sendKeys("test");
        return this;
    }

    public StaleElementReferenceExceptionExamplePage fixedExceptionWithFindBy() {
        driver.get("https://github.com/");
        searchFieldInput.sendKeys("test");
        searchFieldInput.sendKeys(Keys.ENTER);
        searchFieldInput.sendKeys("test");
        return this;
    }
}
