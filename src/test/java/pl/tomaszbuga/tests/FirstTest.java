package pl.tomaszbuga.tests;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.framework.BaseTest;
import pl.tomaszbuga.pom.SeleniumTrainingPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

import static org.openqa.selenium.support.locators.RelativeLocator.RelativeBy;

public class FirstTest extends BaseTest {
    SeleniumTrainingPage seleniumTrainingPage;

    @BeforeMethod
    public void beforeSetup() {
    }

    @Test
    void testColorPicker() {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebElement colorPicker = getDriver().findElement(By.name("my-colors"));

        String initColor = colorPicker.getAttribute("value");
        LOGGER.info("The initial color is {}", initColor);
        Color red = new Color(255, 0, 0, 1);
        String script = String.format("arguments[0].setAttribute('value', '%s');", red.asHex());
        js.executeScript(script, colorPicker);
        String finalColor = colorPicker.getAttribute("value");
        LOGGER.info("The final color is {}", finalColor);

        Assert.assertNotEquals(finalColor, initColor);
        Assert.assertEquals(Color.fromString(finalColor), red);
    }

    @Test
    void testInfiniteScroll() {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/infinite-scroll.html");
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        By pLocator = By.tagName("p");
        List<WebElement> paragraphs = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pLocator, 0));
        int initParagraphsNumber = paragraphs.size();
        WebElement lastParagraph = getDriver().findElement(By.xpath(String.format("//p[%d]", initParagraphsNumber)));

        String script = "arguments[0].scrollIntoView();";
        js.executeScript(script, lastParagraph);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pLocator, initParagraphsNumber));
    }

    @Test
    void testScrollIntoView() {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/long-page.html");
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement lastElememt = getDriver().findElement(By.cssSelector("p:last-child"));
        String script = "arguments[0].scrollIntoView();";
        js.executeScript(script, lastElememt);
        System.out.println();
    }

    @Test
    void testScrollBy() {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/long-page.html");
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String script = "window.scrollBy(0, 1000);";
        js.executeScript(script);
    }

    @Test
    void testFluentWait() {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");

        Wait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        WebElement landscape = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("landscape")));

        Assert.assertTrue(landscape.getAttribute("src").toLowerCase().contains("landscape"));
    }

    @Test
    void testSlowCalculator() {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");

        // wykonujemy działanie: 1 + 3
        getDriver().findElement(By.xpath("//span[text()='1']")).click();
        getDriver().findElement(By.xpath("//span[text()='+']")).click();
        getDriver().findElement(By.xpath("//span[text()='3']")).click();
        getDriver().findElement(By.xpath("//span[text()='=']")).click();

        // czekamy na wyświetlenie rezultatu
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(By.className("screen"), "4"));
    }

    @Test
    void testExplicitWait() {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement landscape = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("landscape")));
        Assert.assertTrue(landscape.getAttribute("src").toLowerCase().contains("landscape"));
    }

    @Test
    void testImplicitWait() {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement landscape = getDriver().findElement(By.id("landscape"));
        Assert.assertTrue(landscape.getAttribute("src").toLowerCase().contains("landscape"));
    }
    @Test
    void testCopyAndPaste() {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        Actions actions = new Actions(getDriver());
        WebElement inputText = getDriver().findElement(By.name("my-text"));
        WebElement textarea = getDriver().findElement(By.name("my-textarea"));
        Keys modifier = SystemUtils.IS_OS_MAC ? Keys.COMMAND : Keys.CONTROL;
        actions.sendKeys(inputText, "hello world").keyDown(modifier)
                .sendKeys(inputText, "a").sendKeys(inputText, "c")
                .sendKeys(textarea, "v").build().perform();
        Assert.assertEquals(inputText.getAttribute("value"), textarea.getAttribute("value"));
    }

    @Test
    void testClickAndHold() {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/draw-in-canvas.html");
        Actions actions = new Actions(getDriver());
        WebElement canvas = getDriver().findElement(By.tagName("canvas"));

        actions.moveToElement(canvas).clickAndHold();

        int numPoints = 10;
        int radius = 30;
        for (int i = 0; i <= numPoints; i++) {
            double angle = Math.toRadians(360 * i / numPoints);
            double x = Math.sin(angle) * radius;
            double y = Math.cos(angle) * radius;

            actions.moveByOffset((int) x, (int) y);
        }

        actions.release(canvas).build().perform();
    }

    @Test
    void testDragAndDrop() {
        int offset = 100;
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");
        Actions actions = new Actions(getDriver());

        WebElement draggable = getDriver().findElement(By.id("draggable"));
        Point initLocation = draggable.getLocation();

        actions.dragAndDropBy(draggable, offset, 0)
                .dragAndDropBy(draggable, 0, offset)
                .dragAndDropBy(draggable, -offset, 0)
                .dragAndDropBy(draggable, 0, -offset).build().perform();
        Assert.assertEquals(initLocation, draggable.getLocation());

        WebElement target = getDriver().findElement(By.id("target"));
        actions.dragAndDrop(draggable, target).build().perform();
        Assert.assertEquals(target.getLocation(), draggable.getLocation());
    }

    @Test
    void testMouseOver() {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/mouse-over.html");
        Actions actions = new Actions(getDriver());

        List<String> imageList = Arrays.asList("compass", "calendar", "award", "landscape");
        for (String imageName : imageList) {
            String xpath = String.format("//img[@src='img/%s.png']", imageName);
            WebElement image = getDriver().findElement(By.xpath(xpath));
            actions.moveToElement(image).build().perform();
            WebElement caption = getDriver().findElement(
                    RelativeLocator.with(By.tagName("div")).near(image));
            Assert.assertEquals(caption.getText().toLowerCase(), imageName.toLowerCase());
        }
    }

    @Test
    void testContextAndDoubleClick() {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
        Actions actions = new Actions(getDriver());

        // kliknięcie prawym przyciskiem myszy (tzw. context click)
        WebElement dropdown2 = getDriver().findElement(By.id("my-dropdown-2"));
        actions.contextClick(dropdown2).build().perform();
        WebElement contextMenu2 = getDriver().findElement(By.id("context-menu-2"));
        Assert.assertTrue(contextMenu2.isDisplayed());

        // dwukrotne kliknięcie lewym przyciskiem myszy (tzw. double mouse click)
        WebElement dropdown3 = getDriver().findElement(By.id("my-dropdown-3"));
        actions.doubleClick(dropdown3).build().perform();
        WebElement contextMenu3 = getDriver().findElement(By.id("context-menu-3"));
        Assert.assertTrue(contextMenu3.isDisplayed());
    }

    @Test
    void testRadioAndCheckbox() {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        WebElement checkbox = getDriver().findElement(By.id("my-checkbox-2"));
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected());

        WebElement radio = getDriver().findElement(By.id("my-radio-2"));
        radio.click();
        Assert.assertTrue(radio.isSelected());
    }

    @Test
    void testNavigation() {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/");
        getDriver().findElement(By.xpath("//a[text()='Navigation']")).click();
        getDriver().findElement(By.xpath("//a[text()='Next']")).click();
        getDriver().findElement(By.xpath("//a[text()='3']")).click();

        getDriver().findElement(By.xpath("//a[text()='2']")).click();
        getDriver().findElement(By.xpath("//a[text()='Previous']")).click();

        String bodyText = getDriver().findElement(By.tagName("body")).getText();
        Assert.assertTrue(bodyText.contains("Lorem ipsum"));
    }

    @Test
    public void testSlider() {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement slider = getDriver().findElement(By.name("my-range"));

        String initValue = slider.getAttribute("value");

        LOGGER.info("The initial value of the slider is {}", initValue);

        for (int i = 0; i < 5; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }

        String endValue = slider.getAttribute("value");

        LOGGER.info("The final value of the slider is {}", endValue);
        Assert.assertNotEquals(initValue, endValue);
    }

    @Test
    public void uploadFile() throws IOException {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/download.html");

        WebElement pngLink = getDriver().findElement(By.xpath("(//a)[2]"));
        File pngFile = new File(".", "webdrivermanager.png");
        download(pngLink.getAttribute("href"), pngFile);

        WebElement pdfLink = getDriver().findElement(By.xpath("(//a)[3]"));
        File pdfFile = new File(".", "webdrivermanager.pdf");
        download(pdfLink.getAttribute("href"), pdfFile);
    }

    void download(String link, File destination) throws IOException {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpUriRequestBase request = new HttpGet(link);

            try (CloseableHttpResponse response = client.execute(request)) {
                FileUtils.copyInputStreamToFile(
                        response.getEntity().getContent(), destination);
            }
        }
    }

    @Test()
    public void enterTextToTextInputTest() {
        // Assign
        String expectedText = "Jakiś inny tekst, który wpisaliśmy";
        // Act
        String textFromTextInput = enterTextAndGetInputValue(expectedText);
        // Assert
        Assert.assertEquals(textFromTextInput, expectedText);
    }

    @Test()
    public void clearTextInputTest() {
        String expectedText = "Jakiś inny tekst, który wpisaliśmy";

        String textFromTextInput = enterTextAndGetInputValue(expectedText);
        Assert.assertEquals(textFromTextInput, expectedText);

        String inputValueAfterClear = getTextAfterClear();
        Assert.assertEquals(inputValueAfterClear, "");
    }

    @Test()
    public void enterTextToTextareaInputTest() {
        // Assign
        String expectedText = "Jakiś inny tekst, który wpisaliśmy";
        // Act
        String textFromTextInput = fillTextareaAndGetInputValue(expectedText);
        // Assert
        Assert.assertEquals(textFromTextInput, expectedText);
    }

    @Test()
    public void selectValueFromDropdownTest() {
        String expectedValue = "Two";
        String selectedValueFromDropdown;

        seleniumTrainingPage.selectValueFromDropdown("Two");
        selectedValueFromDropdown = seleniumTrainingPage.getSelectedValueFromDropdown();

        Assert.assertEquals(selectedValueFromDropdown, expectedValue);
    }

    @Test()
    public void relativeSelectors() {
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        getDriver().manage().window().maximize();

        WebElement baseElement = getDriver().findElement(By.name("my-datalist"));
        RelativeBy relativeBy = RelativeLocator.with(By.tagName("input"));
        WebElement leftSideElement = getDriver().findElement(relativeBy.toLeftOf(baseElement));
        WebElement rightSideElement = getDriver().findElement(relativeBy.toRightOf(baseElement));
        WebElement belowElement = getDriver().findElement(relativeBy.below(baseElement));

        System.out.println(leftSideElement.getAttribute("name"));
        System.out.println(rightSideElement.getAttribute("name"));
        System.out.println(belowElement.getAttribute("name"));
    }

    private String getTextAfterClear() {
        return seleniumTrainingPage
                .clearTextInput()
                .getTextFromTextInput();
    }

    private String enterTextAndGetInputValue(String expectedText) {
        return seleniumTrainingPage
                .enterTextToTextInput(expectedText)
                .getTextFromTextInput();
    }

    private String fillTextareaAndGetInputValue(String expectedText) {
        return seleniumTrainingPage
                .enterTextToTextareaInput(expectedText)
                .getTextFromTextareaInput();
    }
}
