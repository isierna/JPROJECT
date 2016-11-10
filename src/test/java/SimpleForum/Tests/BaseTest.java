package SimpleForum.Tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ira on 10/10/16.
 */
public class BaseTest {
    WebDriver driver;

    @BeforeMethod
    public void createBrowser() {
        String browser = System.getProperty("browser");

        if (browser == null) {
            driver = new FirefoxDriver();
        } else {

            switch (browser) {//TODO: add check in case attribute is not set at test lauch to avoid NullPointer
                case "Chrome":
                    System.setProperty("webdriver.chrome.driver", "/Users/Ira/Dev/Drivers/Chrome/chromedriver");
                    driver = new ChromeDriver();
                    break;
                case "Firefox":
                default:
                    //System.setProperty("webdriver.gecko.driver", "/Users/Ira/Dev/Drivers/Firefox/geckodriver");
                    driver = new FirefoxDriver();
            }
        }
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { //TODO: make screenshot to reflect browser
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println(testResult.getStatus());
            System.out.println(testResult.getName());
            String fileName = testResult.getName() + ".jpg";
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/Ira/Pictures/1/" + fileName));
            driver.quit();
        }

        driver.quit();
    }

}
