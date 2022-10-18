package pl.tomaszbuga.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ScrollUtil {
    public static void scrollByPixels(int xAxis, int yAxis, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(" + xAxis + "," + yAxis + ")");
    }
}
