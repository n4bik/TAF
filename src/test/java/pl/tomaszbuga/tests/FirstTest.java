package pl.tomaszbuga.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.framework.BaseTest;
import pl.tomaszbuga.pom.SeleniumTrainingPage;

import static org.openqa.selenium.support.locators.RelativeLocator.RelativeBy;

public class FirstTest extends BaseTest {
    SeleniumTrainingPage seleniumTrainingPage;

    @BeforeMethod
    public void beforeSetup() {
        seleniumTrainingPage = new SeleniumTrainingPage(getDriver());
        seleniumTrainingPage.openPage();
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
