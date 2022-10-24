package pl.tomaszbuga.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.framework.BaseTest;
import pl.tomaszbuga.pom.SeleniumTrainingPage;

public class HomeworkTest extends BaseTest {

    SeleniumTrainingPage seleniumTrainingPage;

    @BeforeMethod
    public void beforeSetup() {
        seleniumTrainingPage = new SeleniumTrainingPage(getDriver());
        seleniumTrainingPage.openPage();
    }

    @Test()
    public void verifyPassword(){
        String setPassword = "Pass2022!!";
        String passwordFromInput = enterPasswordAndGetInputValue(setPassword);
        Assert.assertEquals(passwordFromInput, setPassword, "Warning : password incompatible");
        boolean encryptionOn = verifyEncryption();
        System.out.println("Encryption (true/false) : " + encryptionOn);
    }

    private String enterPasswordAndGetInputValue(String expectedText){
        return seleniumTrainingPage
                .enterPasswordToInput(expectedText)
                .getPasswordFromInput();
    }

    private boolean verifyEncryption(){
        return seleniumTrainingPage
                .getPasswordAttribute();
    }
}
