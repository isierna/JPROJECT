import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Ira on 10/22/16.
 */
public class PhotographyBlogDownload {
    static WebDriver driver;
    static String maker = "NIkon";
    static String model = "D5";
    static String raw_directory;
    static String jpg_directory;
    static String cookie1;
    static String cookies_string;

    public static void download(URL url, String destination) throws IOException {
        InputStream inputStream = url.openStream();
        OutputStream outputStream = new FileOutputStream(destination);

        byte[] bt = new byte[2048];
        int length;

        while ((length = inputStream.read(bt)) != -1) {
            outputStream.write(bt, 0, length);
        }
    }

    public static void getHTTPHeaders(URL url) throws IOException {
        HttpURLConnection url1 = (HttpURLConnection) url.openConnection();

        Map<String, List<String>> a1 = url1.getHeaderFields();

        System.out.println("Printing Response Header...\n");

        for (Map.Entry<String, List<String>> entry : a1.entrySet()) {
            System.out.println("Key : " + entry.getKey()
                    + " ,Value : " + entry.getValue());
        }
    }

    @BeforeMethod
    public static void openBrowser() {
        String main_directory = "/Users/Ira/Pictures/PhotographyBlog/" + maker + "_" + model;
        jpg_directory = main_directory + "/JPG";
        raw_directory = main_directory + "/RAW";

        new File("/Users/Ira/Pictures/PhotographyBlog").mkdir();
        new File(main_directory).mkdir();
        new File(jpg_directory).mkdir();
        new File(raw_directory).mkdir();



        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", "/Users/Ira/Pictures/1");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "image/jpeg");
        driver = new FirefoxDriver(profile);



    }

    public static WebElement waitUntil(ExpectedCondition<WebElement> condition) {
        return (new WebDriverWait(driver, 10)).until(condition);
    }

    @AfterMethod
    public static void closeBrowser() {
        driver.quit();
    }

    @Test
    public static void photographyBlog() throws IOException, AWTException, InterruptedException {
        driver.get("http://www.photographyblog.com/reviews/nikon_d5_review/sample_images/");
        WebElement element = driver.findElement(By.xpath("(//a[1][@href][text()='Download Original'])[1]"));
        element.click();

        WebElement element1 = waitUntil(presenceOfElementLocated(By.xpath("//img")));


        Actions actions = new Actions(driver);

        actions.contextClick(element1).build().perform();
        actions.sendKeys("v").build().perform();
        actions.sendKeys(Keys.ENTER).build().perform();
        actions.sendKeys(Keys.ENTER).release();







    }
    }

