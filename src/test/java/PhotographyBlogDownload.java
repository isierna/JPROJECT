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
    static String maker = "Fuji";
    static String model = "X-E2S(1)";
    static String raw_directory;
    static String jpg_directory;
    static String main_directory;

    public static void download(URL url, String destination) throws IOException {
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestProperty("Referer","http://www.photographyblog.com/reviews/canon_eos_1d_x_mark_ii_review/sample_images/");

        InputStream inputStream = connection.getInputStream();
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
        main_directory = "/Users/Ira/Pictures/PhotographyBlog/" + maker + "_" + model;
        jpg_directory = main_directory + "/JPG";
        raw_directory = main_directory + "/RAW";

        new File("/Users/Ira/Pictures/PhotographyBlog").mkdir();
        new File(main_directory).mkdir();
        new File(jpg_directory).mkdir();
        new File(raw_directory).mkdir();

        driver = new FirefoxDriver();

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
        driver.get("http://www.photographyblog.com/reviews/fujifilm_x_e2s_review/sample_images/");
        driver.manage().window().maximize();
        List<WebElement> allPhotos = driver.findElements(By.xpath("//a[1][@href][text()=\"Download Original\"]"));

        for(int i=0; i<allPhotos.size(); i++) {
            String link = allPhotos.get(i).getAttribute("href");
            URL url = new URL(link);
            String fileName = link.substring(link.lastIndexOf("/"));

            String destination = jpg_directory + fileName;
            download(url, destination);

        }


        //WebElement element = driver.findElement(By.xpath("(//a[1][@href][text()='Download Original'])[1]"));
        //String link = element.getAttribute("href");
        /*URL url = new URL(link);
        String fileName = link.substring(link.lastIndexOf("/"));

        String destination = jpg_directory + fileName;

        System.out.println(destination);*/
       // download(url, destination);






        System.out.println("make it");






    }
    }

