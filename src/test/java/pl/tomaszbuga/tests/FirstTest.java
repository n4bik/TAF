package pl.tomaszbuga.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.framework.BaseTest;
import pl.tomaszbuga.pom.SeleniumTrainingPage;

public class FirstTest extends BaseTest {

    SeleniumTrainingPage seleniumTrainingPage;

    @BeforeMethod
    public void beforeSetup() {
        seleniumTrainingPage = new SeleniumTrainingPage(getDriver());
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

    private String getTextAfterClear() {
        return seleniumTrainingPage
                .clearTextInput()
                .getTextFromTextInput();
    }

    private String enterTextAndGetInputValue(String expectedText) {
        return seleniumTrainingPage
                .openPage()
                .enterTextToTextInput(expectedText)
                .getTextFromTextInput();
    }
}
