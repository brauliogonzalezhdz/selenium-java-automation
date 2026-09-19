package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class Bookf {

    private WebDriver driver;
    private WebDriverWait wait;
    private By cityF;
    private By cityT;
    private By fromDate;
    private By toDate;
    private By passegers;
    private By clas;
    private By search;

    //Constructor
    public Bookf(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.cityF = By.cssSelector("[data-testid='flight-from']");
        this.cityT = By.cssSelector("[data-testid='flight-to']");
        this.fromDate = By.cssSelector("[data-testid='flight-departure-date']");
        this.toDate = By.cssSelector("[data-testid='flight-return-date']");
        this.passegers = By.cssSelector("[data-testid='flight-passengers']");
        this.clas = By.cssSelector("[data-testid='flight-to']");
        this.search = By.cssSelector("[data-testid='flight-search']");

    }

    public void invalid(String ciudad) {
        Assert.assertEquals(driver.getTitle(), "Flight Booking Automation Practice | QA Practice");
        driver.findElement(cityF).click();
        WebElement dropdown = driver.findElement(cityF);
        Assert.assertTrue(dropdown.isDisplayed());
        Select city = new Select(dropdown);
        boolean exists = city.getOptions()
                .stream()
                .anyMatch(option -> option.getText().equals(ciudad));
        Assert.assertFalse(
                exists,
                "La ciudad " + ciudad + " no debería existir en el dropdown"
        );
    }

    public void valid(String ciudad) {
        Assert.assertEquals(driver.getTitle(), "Flight Booking Automation Practice | QA Practice");
        driver.findElement(cityF).click();
        WebElement dropdown = driver.findElement(cityF);
        Assert.assertTrue(dropdown.isDisplayed());
        Select city = new Select(dropdown);
        Assert.assertTrue(
                city.getOptions().size() > 1);
        city.selectByVisibleText(ciudad);
        Assert.assertEquals(
                city.getFirstSelectedOption().getText(),
                ciudad
        );
    }

    public void complete(String cF, String cT, String dD, String rD, String Ps) {
        //City From
        Select cityFrom = new Select(driver.findElement(cityF));
        cityFrom.selectByVisibleText(cF);
        //City To
        Select cityTo = new Select(driver.findElement(cityT));
        cityTo.selectByVisibleText(cT);
        //From Date
        driver.findElement(fromDate).click();
        WebElement dFrom = driver.findElement(fromDate);
        dFrom.sendKeys(dD);
        //To Date
        driver.findElement(toDate).click();
        WebElement dTo = driver.findElement(toDate);
        dTo.sendKeys(rD);
        //Passenger
        driver.findElement(passegers).click();
        WebElement Pa = driver.findElement(passegers);
        Pa.sendKeys(Ps);
        WebElement searchButton = wait.until(
                ExpectedConditions.elementToBeClickable(search));
        /*System.out.println("Visible: " + searchButton.isDisplayed());
        System.out.println("Enabled: " + searchButton.isEnabled());
        System.out.println("Location: " + searchButton.getLocation());
        System.out.println("Size: " + searchButton.getSize());
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                searchButton
        );

        String elementOnTop = (String) js.executeScript(
                """
                const button = arguments[0];
                const rect = button.getBoundingClientRect();
        
                const x = rect.left + rect.width / 2;
                const y = rect.top + rect.height / 2;
        
                const element = document.elementFromPoint(x, y);
        
                return element ? element.outerHTML : 'No element found';
                """,
                searchButton
        );

        System.out.println("ELEMENTO ENCIMA DEL BOTON:");
        System.out.println(elementOnTop);*/
        Actions actions = new Actions(driver);

        actions.moveToElement(searchButton)
                .click()
                .perform();
        //searchButton.click();
    }

}

