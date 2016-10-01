import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    public static void photoGet() throws IOException {
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
        ArrayList <WebElement> photo_list = new ArrayList<WebElement>();
        WebElement photo = waitUntil(presenceOfElementLocated(By.xpath("//*[@id=\"thumbs-table\"]/tbody/tr[1]/td[1]")));
        List<WebElement> elements = photo.findElements(By.xpath(".//*[@href]")); // dot in front of // ensures that the search will be made inside the particular web-element, not the entire page

        for(int i = 0; i < elements.size(); i++) {
            System.out.println("wwww " + elements.get(i).getAttribute("href"));
        }

        System.out.println("123 " + photo.getAttribute("href"));

        photo_list.add(photo);
        System.out.println(photo.getAttribute("href"));
        photo_list.get(0).click();

        WebElement actual_link = waitUntil(presenceOfElementLocated(By.xpath("/html/body/table/tbody/tr/td[2]/p[1]/b/a")));
        actual_link.click();

        File photo_folder = new File("/Users/Ira/Pictures/1");
        File[] flist = photo_folder.listFiles();
        System.out.println("List of downloaded files \n");


        for (File file : flist) {
            System.out.println(file.getName());
        }

        //FileUtils.cleanDirectory(photo_folder);

    }

    @AfterMethod
    public static void closeBrowser() { driver.quit();}
}
