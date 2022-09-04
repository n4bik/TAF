package pl.tomaszbuga.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.framework.PageObject;

public class HomePage extends PageObject {
    @FindBy(css = "#docsearch")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage openHomePage() {
        driver.get("http://www.selenium.dev");
        return this;
    }

    public HomePage clickSearchButton() {
        searchButton.click();
        return this;
    }
}
