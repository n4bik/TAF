package pl.tomaszbuga.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.autohero.Car;
import pl.tomaszbuga.framework.PageObject;

import java.util.ArrayList;
import java.util.List;

import static pl.tomaszbuga.utils.ScrollUtil.scrollByPixels;

public class AutoHeroCarsPage extends PageObject {
    String
            baseUrl = "https://www.autohero.com/pl/search/?fuelType=hybrid&fuelType=elektro",
            carTitleSelector = "[data-qa-selector='title']",
            carPriceSelector = "[data-qa-selector='price']",
            carYearSelector = "[data-qa-selector='spec-year']",
            carFuelTypeSelector = "[data-qa-selector='spec-fuel']",
            carMileageSelector = "[data-qa-selector='spec-mileage']",
            carGearTypeSelector = "[data-qa-selector='spec-gear']",
            carCardSelector = "//div[@class='ReactVirtualized__Grid grid___nN-uR']/div/div";
    List<Car> cars = new ArrayList<>();

    @FindBy(css = "[data-qa-selector='cookie-consent-configure']")
    private WebElement showCookieConsentConfigButton;

    @FindBy(css = ".ReactVirtualized__Grid__innerScrollContainer")
    private WebElement carsContainer;

    @FindBy(css = ".saveSearchWrapper___3Z1KI > [data-qa-selector='filters-car-counter']")
    private WebElement carsCounter;

    public AutoHeroCarsPage(WebDriver driver) {
        this.driver = driver;
        setWebDriverToSmallSize(driver);
        PageFactory.initElements(driver, this);
    }

    private void setWebDriverToSmallSize(WebDriver driver) {
        driver
                .manage()
                .window()
                .setSize(new Dimension(400, 650));
    }

    public AutoHeroCarsPage openAutoHeroCarsPage() {
        LOGGER.info("Opening webpage: " + baseUrl);
        driver.get(baseUrl);
        return this;
    }

    public AutoHeroCarsPage setOnlyEssentialCookies() {
        clickOnMoreActionsButton();
        clickOnMoreActionsButton();
        return this;
    }

    public AutoHeroCarsPage getCars() throws InterruptedException {
        int carsCount = getCarsCount();
        int counter = 0;

        while (counter < carsCount) {
            WebElement carCard = getCarCard(counter, carsCount);
            counter = addCarToListOrSubstractFromCounter(counter, carCard);
            scrollToNextCar(counter);
            Thread.sleep(150); // NIE WYKORZYSTYWAC W AUTOMATYZACJI TESTOW!!!
            counter++;
        }

        displayCarsList();
        return this;
    }

    private int getCarsCount() {
        waitUntilElementIsVisible(carsCounter);
        return Integer.parseInt(carsCounter.getText().split(" ")[0]);
    }

    private WebElement getCarCard(int counter, int carCount) {
        if (counter == carCount - 1) {
            // dla ostatniego pojazdu musimy wybrac drugi element
            // poniewaz nie da się przeskrolowac strony w taki sposob
            // zeby byl widoczny tylko ostatni pojazd
            return carsContainer.findElement(By.xpath(carCardSelector + "[2]"));
        } else {
            return carsContainer.findElement(By.xpath(carCardSelector + "[1]"));
        }
    }

    private int addCarToListOrSubstractFromCounter(int counter, WebElement carCard) {
        try {
            Car car = getCar(carCard);
            cars.add(car);
        } catch (Exception ex) {
            // w razie wystąpienia reklamy odejmujemy jedną wartośc z licznika
            counter--;
        }

        return counter;
    }

    private Car getCar(WebElement carCard) {
        String title = getCarTitle(carCard);
        String price = getCarPrice(carCard);
        String year = getCarYear(carCard);
        String fuelType = getCarFuelType(carCard);
        String mileage = getCarMileage(carCard);
        String gearType = getCarGearType(carCard);

        return new Car(title, price, year, fuelType, mileage, gearType);
    }

    private void scrollToNextCar(int counter) {
        if (counter == 0) {
            // w przypadku pierwszego samochodu bierzemy pod uwagę
            // fakt, że musimy przewinąc też header strony
            scrollByPixels(0, 700, driver);
        } else {
            // przewijanie powodujące, że elementy znajdują się
            // za kazdym razem w tym samym miejscu (w razie jeżeli liczba
            // samochód byłaby duża, mogłoby to stwarzac problemy)
            scrollByPixels(0, 482, driver);
        }
    }

    private String getCarTitle(WebElement carCard) {
        return carCard.findElement(By.cssSelector(carTitleSelector)).getText();
    }

    private String getCarPrice(WebElement carCard) {
        return carCard.findElement(By.cssSelector(carPriceSelector)).getText();
    }

    private String getCarYear(WebElement carCard) {
        return carCard.findElement(By.cssSelector(carYearSelector)).getText();
    }

    private String getCarFuelType(WebElement carCard) {
        return carCard.findElement(By.cssSelector(carFuelTypeSelector))
                .getText().split("\n")[1];
    }

    private String getCarMileage(WebElement carCard) {
        return carCard.findElement(By.cssSelector(carMileageSelector))
                .getText().split("\n")[1];
    }

    private String getCarGearType(WebElement carCard) {
        return carCard.findElement(By.cssSelector(carGearTypeSelector)).getText();
    }

    private void displayCarsList() {
        for (Car car : cars) {
            String formattedCarDisplay = """
                    =================
                    MARKA / MODEL: %s
                    CENA: %s
                                        
                    PRZEBIEG: %s
                    ROK PRODUKCJI: %s
                    RODZAJ PALIWA: %s
                    SKRZYNIA BIEGÓW: %s
                    =================
                                        
                    """.formatted(
                    car.title(),
                    car.price(),
                    car.mileage(),
                    car.year(),
                    car.fuelType(),
                    car.gearType());

            System.out.println(formattedCarDisplay);
        }

        System.out.println("ŁĄCZNIE SAMOCHODÓW: " + cars.size());
    }

    private void clickOnMoreActionsButton() {
        waitUntilElementIsClickable(showCookieConsentConfigButton);
        LOGGER.info("Clicking on More Actions button");
        showCookieConsentConfigButton.click();
    }
}
