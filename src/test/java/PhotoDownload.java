import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Ira on 9/29/16.
 */
public class PhotoDownload {
    static WebDriver driver;

    @BeforeMethod
    public static void openBrowser() { driver = new FirefoxDriver(); }

    private static WebElement waitUntil(ExpectedCondition<WebElement> condition) {
        return  (new WebDriverWait(driver, 10)).until(condition);
    }

    @Test
    public static void photoGet() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.dir", "/Users/Ira/Pictures/1");
        profile.setPreference("browser.download.folderList", 2);
        driver = new FirefoxDriver(profile);
        driver.get("http://www.imaging-resource.com/cameras/reviews/");
        driver.manage().window().maximize();


        driver.findElement(By.linkText("Canon Reviews")).click();
        WebElement camera = waitUntil(presenceOfElementLocated(By.linkText("Canon 1DX")));
        camera.click();
        WebElement gallery = waitUntil(presenceOfElementLocated(By.linkText("Gallery")));
        gallery.click();
        WebElement photo = waitUntil(presenceOfElementLocated(By.linkText("Y-Sigma150-600_BX6Q1667.CR2")));
        photo.click();
        WebElement actual_link = waitUntil(presenceOfElementLocated(By.linkText("Y-Sigma150-600_BX6Q1667.CR2")));
        actual_link.click();
    }
}
