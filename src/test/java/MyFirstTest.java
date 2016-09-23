/**
 * Created by Ira on 9/20/16.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;



public class MyFirstTest {
    static WebDriver driver;

    @Test
    public static void openGoogle() {
        driver = new FirefoxDriver();

        driver.get("http://google.com");

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("AfterShot");
        element.submit();

        WebElement dynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("RAW")));
        dynamicElement.click();

        WebElement applicationPage = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.id("asp-trial-nd")));
        System.out.println(applicationPage);

        applicationPage.click();

    }

    @AfterMethod
    public static void closeBrowser(){
        driver.quit();
    }
}
