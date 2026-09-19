package test;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Bookf;

import java.io.File;
import java.time.Duration;

public class Tests {
    WebDriver driver;
    WebDriverWait wait;
    final String baseUrl = "https://www.qapractice.com/flight-booking-scenarios";
    final String chooseUrl = "https://www.qapractice.com/flight-booking-scenarios";
    Bookf b;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(baseUrl);
        b = new Bookf(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
@Test
public void reverse()
{
    String word = "Hello";
    String reword = "";

    for (int i=word.length()-1; i>=0; i--)
    {
        reword = reword + word.charAt(i);
        System.out.println("First Iteration: " + reword);
    }
    System.out.println("Original word = " + word);
    System.out.println("Reword= " +reword);
}
    @Test
    public void bookvalidcity() {
        b.valid("London");
        File screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        File destino = new File("Screenshot/login_correcto.png");

        destino.getParentFile().mkdirs();

        screenshot.renameTo(destino);
    }

    @Test
    public void bookinvalidcity() {
        b.invalid("Argentina");
        File screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        File destino = new File("Screenshot/login_incorrecto.png");

        destino.getParentFile().mkdirs();

        screenshot.renameTo(destino);
    }

    @Test
    public void allFlow() {
        b.complete("London", "New York", "01-01-2024", "02-01-2024", "2");
        wait.until(ExpectedConditions.urlContains(chooseUrl));
        By flightResult =
                By.cssSelector("[data-testid='flight-select-GW100']");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(flightResult)
        );

        Assert.assertTrue(
                driver.findElement(flightResult).isDisplayed()
        );
        File screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        File destino = new File("Screenshot/vuelos.png");

        destino.getParentFile().mkdirs();

        screenshot.renameTo(destino);
    }


}


