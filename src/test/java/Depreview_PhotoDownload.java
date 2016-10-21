import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Ira on 10/13/16.
 */
public class Depreview_PhotoDownload {
    static WebDriver driver;
    static String maker = "Nikon";
    static String model = "D5";
    static String file_name;
    static WebElement link_element;
    static String link;
    static URL url;

    public static void download(URL url, String destination) throws IOException {
        InputStream inputStream = url.openStream();
        OutputStream outputStream = new FileOutputStream(destination);

        byte[] bt = new byte[2048];
        int length;

        while ((length = inputStream.read(bt)) != -1) {
            outputStream.write(bt,0,length);
        }

        inputStream.close();
        outputStream.close();
    }

    public static boolean checkIfFileExists(String file_name, String directory) throws IOException {
        Boolean a = true;
        File file1 = new File(directory);
        String[] all_files = file1.list();

        for (String file2:all_files) {
            file2 = "/" + file2;

            if(file_name.equals(file2)) {
                System.out.println("File names equals");
                a = false;
            }
        }
        System.out.println(a);
        return a;
    }

    @BeforeMethod
    public static void openBrowser() {
        driver = new FirefoxDriver();

    }

    private static WebElement waitUntil(ExpectedCondition<WebElement> condition) {
        return  (new WebDriverWait(driver, 10)).until(condition);
    }

    @AfterMethod
    public static void closeBrowser() {
        driver.quit();
    }

    @Test
    public static void dpPhotoDownload() throws InterruptedException, IOException {
        String main_directory = "/Users/Ira/Pictures/Dpreview/" + maker + "_" + model;
        String jpg_directory = main_directory + "/JPG";
        String raw_directory = main_directory + "/RAW";

        new File(main_directory).mkdir();
        new File(jpg_directory).mkdir();
        new File(raw_directory).mkdir();


        driver.get("https://www.dpreview.com/");
        driver.manage().window().maximize();

        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.linkText("Sample Images"));
        action.moveToElement(element).perform();
        Thread.sleep(200);
        WebElement element1 = driver.findElement(By.linkText("Browse all"));
        action.moveToElement(element1).perform();
        Thread.sleep(200);
        action.click(element1).perform();

        WebElement link_to_camera = driver.findElement(By.partialLinkText("Nikon D5 real world low light samples"));
        link_to_camera.click();

        waitUntil(presenceOfElementLocated(By.xpath("//div[@class=\"filmstrip\"]")));

        List<WebElement> strip_items = driver.findElements(By.xpath("//div[@class='filmstripImage']"));
        ArrayList<String> photo_links_urls = new ArrayList<String>();


        for (int t=1; t<=strip_items.size(); t++) {
            WebElement u = driver.findElement(By.xpath("//div[@class='filmstripImage']" + "[" + t +"]"));
            u.click();
            link_element = waitUntil(presenceOfElementLocated(By.xpath("//tr[./td[text()=\"Download:\"]]/td[@class=\"content\"]/a[1]")));
            link = link_element.getAttribute("href");
            photo_links_urls.add(link);
            System.out.println("link 1" + link);

            url = new URL(link);
            String file = url.getFile();
            file_name = file.substring(file.lastIndexOf("/"));

            if (checkIfFileExists(file_name,jpg_directory)){
                String destination = jpg_directory + file_name;
                download(url, destination);
            }else {System.out.println("File already exists");}

            try {
                link_element = driver.findElement(By.xpath("//tr[./td[text()=\"Download:\"]]/td[@class=\"content\"]/a[2]"));
                link = link_element.getAttribute("href");
                photo_links_urls.add(link);
                System.out.println("Link 2" + link);

                url = new URL(link);
                String file2 = url.getFile();
                file_name = file2.substring(file2.lastIndexOf("/"), file2.indexOf("?"));

                if (checkIfFileExists(file_name,raw_directory)) {
                    String destination2 = raw_directory + file_name;
                    download(url, destination2);
                } else {System.out.println("RAW file already exists");}

            }catch (NoSuchElementException e) {

            }
            System.out.println("Size is " + photo_links_urls.size());
        }
        System.out.println(photo_links_urls.size());
     System.out.println("Success");
    }
}
