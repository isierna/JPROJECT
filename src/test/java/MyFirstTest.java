/**
 * Created by Ira on 9/20/16.
 */

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MyFirstTest {
    static WebDriver driver;

    @BeforeMethod
    public static void openBrowser() {
        driver = new FirefoxDriver();
    }

    private static WebElement waitUntil(ExpectedCondition<WebElement> condition) {
        return (new WebDriverWait(driver, 10)).until(condition);
    }

    @Test
    public static void openGoogle() {
        driver.get("http://google.com");
        driver.manage().window().maximize();

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("AfterShot");
        element.submit();

        WebElement dynamicElement = waitUntil(presenceOfElementLocated(By.partialLinkText("RAW")));
        dynamicElement.click();

        WebElement applicationPage = waitUntil(presenceOfElementLocated(By.id("asp-trial-nd")));
        System.out.println(applicationPage);

        applicationPage.click();

    }

    @Test
    public static void login() {
        driver.get("http://yahoo.com");//open the web page
        driver.manage().window().maximize();

        driver.findElement(By.id("uh-signin")).click();  //click at sign in button

        driver.findElement(By.id("login-signup")).click(); //click at signup button



        WebElement first_name = waitUntil(presenceOfElementLocated(By.id("usernamereg-firstName")));

        WebElement last_name = waitUntil(presenceOfElementLocated(By.id("usernamereg-lastName")));

        WebElement email = waitUntil(presenceOfElementLocated(By.id("usernamereg-yid")));

        WebElement password = waitUntil(presenceOfElementLocated(By.id("usernamereg-password")));

        WebElement phone_number = waitUntil(presenceOfElementLocated(By.id("usernamereg-phone")));

        String randomString;
        randomString = RandomStringUtils.randomAlphabetic(8); //generating a random string

        first_name.sendKeys(randomString);
        last_name.sendKeys(randomString);
        email.sendKeys(randomString);

        String password1 = RandomStringUtils.randomAlphanumeric(10);

        Select country = new Select(waitUntil(presenceOfElementLocated(By.name("shortCountryCode"))));
        country.selectByValue("MC");

        Select month = new Select(waitUntil(presenceOfElementLocated(By.id("usernamereg-month"))));
        month.selectByValue("1");

        Select day = new Select(waitUntil(presenceOfElementLocated(By.id("usernamereg-day"))));
        day.selectByValue("1");

        Select year = new Select(waitUntil(presenceOfElementLocated(By.id("usernamereg-year"))));
        year.selectByValue("1990");

        Actions action = new Actions(driver);
        action.moveToElement(phone_number).click(phone_number);
        action.perform();

        WebElement submit_button = waitUntil(presenceOfElementLocated(By.id("reg-submit-button")));

        password.sendKeys(password1);

        phone_number.clear();
        phone_number.sendKeys("92057473");

        submit_button.click();

    }

    @AfterMethod
    public static void closeBrowser(){
        driver.quit();
    }
}
