package pl.tomaszbuga.ui;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.ui.pom.StaleElementReferenceExceptionExamplePage;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void testSlider() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement slider = driver.findElement(By.name("my-range"));

        String initialValue = slider.getAttribute("value");

        for (int i = 0; i < 5; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }

        String endValue = slider.getAttribute("value");
        Assert.assertNotEquals(initialValue, endValue);
    }

    @Test
    public void testMouseClicks() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
        Actions actions = new Actions(driver);

        WebElement dropdownRightClick = driver.findElement(By.id("my-dropdown-2"));
        actions
                .contextClick(dropdownRightClick)
                .build()
                .perform();

        WebElement contextMenuAfterContextClick = driver.findElement(By.id("context-menu-2"));
        Assert.assertTrue(contextMenuAfterContextClick.isDisplayed());

        WebElement dropdownDoubleClick = driver.findElement(By.id("my-dropdown-3"));
        actions
                .doubleClick(dropdownDoubleClick)
                .build()
                .perform();

        WebElement contextMenuAfterDoubleClick = driver.findElement(By.id("context-menu-3"));
        Assert.assertTrue(contextMenuAfterDoubleClick.isDisplayed());
    }

    @Test
    public void mouseHoverTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/mouse-over.html");
        Actions actions = new Actions(driver);

        List<String> imageList = Arrays.asList("compass", "calendar", "award", "landscape");

        for (String imageName : imageList) {
            String xpath = "//img[@src='img/%s.png']".formatted(imageName);
            WebElement image = driver.findElement(By.xpath(xpath));
            actions
                    .moveToElement(image)
                    .build().perform();
            WebElement caption = driver.findElement(RelativeLocator.with(By.tagName("div")).near(image));
            Assert.assertEquals(caption.getText().toLowerCase(), imageName.toLowerCase());
        }
    }

    @Test
    public void dragAndDropTest() {
        int offset = 100;
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");
        Actions actions = new Actions(driver);

        WebElement draggable = driver.findElement(By.id("draggable"));
        Point initalLocation = draggable.getLocation();

        actions.dragAndDropBy(draggable, offset, 0)
                .dragAndDropBy(draggable, 0, offset)
                .dragAndDropBy(draggable, -offset, 0)
                .dragAndDropBy(draggable, 0, -offset)
                .build().perform();

        Assert.assertEquals(initalLocation, draggable.getLocation());

        WebElement target = driver.findElement(By.id("target"));
        actions.dragAndDrop(draggable, target).build().perform();

        Assert.assertEquals(target.getLocation(), draggable.getLocation());
    }

    @Test
    public void clickAndHoldTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/draw-in-canvas.html");
        Actions actions = new Actions(driver);
        WebElement canvas = driver.findElement(By.tagName("canvas"));

        actions
                .moveToElement(canvas)
                .clickAndHold();

        int numPoints = 10;
        int radius = 30;

        for (int i = 0; i < numPoints; i++) {
            double angle = Math.toRadians(360 * i / numPoints);
            double x = Math.sin(angle) * radius;
            double y = Math.cos(angle) * radius;

            actions.moveByOffset((int) x, (int) y);
        }

        actions
                .release(canvas)
                .build().perform();
    }

}
