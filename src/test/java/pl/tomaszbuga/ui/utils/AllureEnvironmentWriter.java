package pl.tomaszbuga.ui.utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.util.Properties;

public class AllureEnvironmentWriter {

    private static final String ALLURE_RESULTS_PATH = "allure-results/";
    private static final String ALLURE_ENV_PROP_PATH = "allure-results/environment.properties";

    public static void createEnvironmentPropertiesFile(WebDriver driver) {
        Properties properties = new Properties();
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();

        createAllureResultsDirectoryIfNotExists();

        try (OutputStream outputStream = new FileOutputStream(ALLURE_ENV_PROP_PATH)) {
            properties.setProperty("Browser", capabilities.getBrowserName());
            properties.setProperty("Browser Version", capabilities.getBrowserVersion());
            properties.setProperty("OS", System.getProperty("os.name"));
            properties.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createAllureResultsDirectoryIfNotExists() {
        new File(ALLURE_RESULTS_PATH).mkdir();
    }

}
