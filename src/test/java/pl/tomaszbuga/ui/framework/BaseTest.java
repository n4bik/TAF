package pl.tomaszbuga.ui.framework;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static pl.tomaszbuga.ui.utils.AllureEnvironmentWriter.createEnvironmentPropertiesFile;

@Log4j2
public abstract class BaseTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    private void setupWebDriver() {
        setupDriver();
        createEnvironmentPropertiesFile(getDriver());
    }

    @AfterMethod(alwaysRun = true)
    private void closeBrowser() {
        driver.quit();
    }

    private void setupDriver() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments(
                "--headless",
                "--disable-gpu",
                "--window-size=1920,1200",
                "--ignore-certificate-errors",
                "--disable-extensions",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--remote-allow-origins=*"
        );

        driver = new ChromeDriver(options);
    }

    protected WebDriver getDriver() {
        if (driver != null)
            return driver;

        setupWebDriver();
        return getDriver();
    }
}
