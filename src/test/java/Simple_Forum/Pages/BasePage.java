package Simple_Forum.Pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ira on 10/10/16.
 */
public class BasePage {
    WebDriver driver;

    public void go() {}
    public void at() {}

    public String currentDateAndTime() {
        DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        String date1 = dateformat.format(date);
        return date1;

    }

    public void goTo(WebElement element) {

    }

    public <T> void waitUntil(ExpectedCondition<T> expectedCondition) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(expectedCondition);
    }

    public String generateRandomString() {
        RandomStringUtils randomObject = new RandomStringUtils();
        return randomObject.randomAlphanumeric(7);
    }


}
