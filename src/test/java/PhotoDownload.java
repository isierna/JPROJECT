import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.*;
import java.net.URL;
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
        } catch (NoSuchElementException e) {
            System.out.println("Error " + e);
        }
    }


    @Test
    public static void photoGet() throws IOException, AWTException {
        String main_directory = "/Users/Ira/Pictures/1/" + maker + "_" + model;
        String jpg_directory = main_directory + "/JPG";
        String raw_directory = main_directory + "/RAW";

        new File(main_directory).mkdir();
        new File(jpg_directory).mkdir();
        new File(raw_directory).mkdir();

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

        ArrayList<String> phtot_links_urls = new ArrayList<String>();

        for (WebElement y : photo_links) {
            String a = y.getAttribute("href");
            phtot_links_urls.add(a);
        }


        for (int i = 0; i < photo_links.size(); i++) {
            driver.get(phtot_links_urls.get(i));
            try {
                driver.findElement(By.linkText("Full Size Image")).click();
                WebElement link_element = driver.findElement(By.xpath("//img[contains(@src,'FULLRES')]"));

                String link1 = link_element.getAttribute("src");
                System.out.println(link1);

                URL url = new URL(link1);
                String file_name = url.getFile();
                String destination = jpg_directory + file_name.substring(file_name.lastIndexOf("/"));

                System.out.println(destination);

                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(destination);

                byte[] bt = new byte[2048];
                int length;

                while ((length = is.read(bt)) != -1) {
                    os.write(bt,0,length);
                }

                is.close();
                os.close();

            } catch (NoSuchElementException e) {
                System.out.println(e);
            }

            try {
                WebElement raw_element = driver.findElement(By.xpath("//a[@class='caption']"));
                String raw_url = raw_element.getAttribute("href");
                URL url = new URL(raw_url);
                String file_name = url.getFile();
                String destination = raw_directory + file_name.substring(file_name.lastIndexOf("/"));

                System.out.println(destination);

                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(destination);

                byte[] bt = new byte[2048];
                int length;

                while ((length = is.read(bt)) != -1) {
                    os.write(bt,0,length);
                }

                is.close();
                os.close();

                System.out.println("success");

            } catch (NoSuchElementException e) {
                System.out.println(e);
            }
        }
    }

    @AfterMethod
    public static void closeBrowser() { driver.quit();}
}
