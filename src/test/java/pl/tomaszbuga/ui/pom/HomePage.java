package pl.tomaszbuga.ui.pom;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.ui.framework.PageObject;

@Log4j2
public class HomePage extends PageObject {
    @FindBy(css = "#docsearch")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage openHomePage() {
        log.info("Opening home page");
        driver.get("https://www.selenium.dev");
        return this;
    }

    public HomePage clickSearchButton() {
        log.info("Clicking on the search button");
        searchButton.click();
        return this;
    }
}
