package pl.tomaszbuga.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.ui.pom.StaleElementReferenceExceptionExamplePage;

public class SEETest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--remote-allow-origins=*"
        );
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void staleElementExceptionTest() {
        var page = new StaleElementReferenceExceptionExamplePage(driver);
        page.forceException(); // test should force Stale Element Exception
        // page.fixedExceptionWithFindElement(); // test should solve Stale Element Exception
        // page.fixedExceptionWithWait(); // test should solve Stale Element Exception
        // page.fixedExceptionWithFindBy(); // test should solve Stale Element Exception
    }
}
