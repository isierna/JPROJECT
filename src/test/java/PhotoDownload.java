import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Ira on 9/29/16.
 */
public class PhotoDownload {
    static WebDriver driver;
    static String maker = "Canon";
    static String model = "Canon 5DS";


    @BeforeMethod
    public static void openBrowser() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", "/Users/Ira/Pictures/1");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "image/x-cr2");

        driver = new FirefoxDriver(profile);
    }

    private static WebElement waitUntil(ExpectedCondition<WebElement> condition) {
        return  (new WebDriverWait(driver, 10)).until(condition);
    }

    private static void checkForAd() {
        try {
            driver.findElement(By.linkText("//*[@id=\"AdFloater\"]/a[1]")).click();
        }catch (NoSuchElementException e) {
            System.out.println("Error " + e);
        }
    }


    @Test
    public static void photoGet() throws IOException, AWTException {
        driver.get("http://www.imaging-resource.com/cameras/reviews/"); //open driver and main URL to work with
        driver.manage().window().maximize(); //maximize browser's window


        driver.findElement(By.linkText( maker +" Reviews")).click();
        checkForAd();
        WebElement camera = waitUntil(presenceOfElementLocated(By.linkText(model)));
        camera.click();
        checkForAd();
        WebElement gallery = waitUntil(presenceOfElementLocated(By.linkText("Gallery")));
        gallery.click();

        checkForAd();

        List<WebElement> photo_links = driver.findElements(By.xpath("//table[@id=\"thumbs-table\"]/tbody/tr/td/a[not(contains(@href,'EXIF'))][not(./img)]"));
        System.out.println("zzzz" + photo_links.size());

        for (int i = 0; i < photo_links.size(); i++) {
            System.out.println("wwww " + photo_links.get(i).getAttribute("href"));
            WebElement link = photo_links.get(i);
            link.click();

            try {
                driver.findElement(By.linkText("Full Size Image")).click();
                WebElement a = driver.findElement(By.xpath("//img[contains(@src,'FULLRES')]"));
                Actions action = new Actions(driver);
                action.moveToElement(a).perform();
                action.contextClick(a).perform();
                action.sendKeys("v").perform();

                Robot robot = new Robot();

                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
                    robot.delay(2000);

            } catch (NoSuchElementException e) {
                System.out.println(e);
            }
        }

        //FileUtils.cleanDirectory(photo_folder);

    }

    @AfterMethod
    public static void closeBrowser() { driver.quit();}
}
