package pl.tomaszbuga.ui.utils;

import com.google.common.base.CaseFormat;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@UtilityClass
public class ByLocatorFinder {
    public static By getByFromWebElement(WebElement element) {
        String elemStr = element.toString();
        if (elemStr.contains("->")) {
            return extractByFromWebElement(elemStr.split("->")[1]);
        } else {
            return extractByFromProxyElement(
                    elemStr.substring(elemStr.indexOf("'") + 1,
                            elemStr.length() - 1));
        }
    }

    public static By extractByFromWebElement(String locatorToString) {
        String[] locatorSplit = locatorToString.split(": ");

        if (locatorSplit.length != 2)
            throw new IllegalStateException(String.format("Locator definition does not have 2 elements for %s locator", locatorToString));

        String locatorType = locatorSplit[0].trim();
        String locatorValue = locatorSplit[1].trim().substring(0, locatorSplit[1].trim().length() - 1);

        return getBy(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,
                        locatorType.toUpperCase().replaceAll(" ", "_")),
                locatorValue, locatorToString);
    }

    public static By extractByFromProxyElement(String locatorToString) {
        String[] locatorSplit = locatorToString.split(": ");

        if (locatorSplit.length != 2)
            throw new IllegalStateException(String.format("Locator definition does not have 2 elements for %s locator", locatorToString));

        String locatorType = locatorSplit[0].trim().substring(3);
        String locatorValue = locatorSplit[1].trim();

        return getBy(locatorType, locatorValue, locatorToString);
    }

    private static By getBy(String locatorType, String locatorValue, String locatorToString) {
        return switch (locatorType) {
            case "cssSelector" -> By.cssSelector(locatorValue);
            case "id" -> By.id(locatorValue);
            case "linkText" -> By.linkText(locatorValue);
            case "partialLinkText" -> By.partialLinkText(locatorValue);
            case "tagName" -> By.tagName(locatorValue);
            case "name" -> By.name(locatorValue);
            case "class" -> By.className(locatorValue);
            case "xpath" -> By.xpath(locatorValue);
            default ->
                    throw new IllegalStateException("Cannot define locator for WebElement definition: " + locatorToString);
        };
    }

}