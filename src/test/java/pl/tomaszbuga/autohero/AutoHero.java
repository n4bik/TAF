package pl.tomaszbuga.autohero;

import pl.tomaszbuga.framework.BaseTest;
import pl.tomaszbuga.pom.AutoHeroCarsPage;

public class AutoHero extends BaseTest {
    public static void main(String[] args) {
        AutoHeroCarsPage autoHeroCarsPage = new AutoHeroCarsPage(getDriver());

        autoHeroCarsPage
                .openAutoHeroCarsPage()
                .setOnlyEssentialCookies()
                .getCars();

        getDriver().quit();
    }
}
