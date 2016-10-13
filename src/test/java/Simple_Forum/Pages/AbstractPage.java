package Simple_Forum.Pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Ira on 10/10/16.
 */
public class AbstractPage {
    WebDriver driver;

    public void go() {}
    public void at() {}

    public <T> void waitUntil(ExpectedCondition<T> expectedCondition) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(expectedCondition);
    }

    public String generateRandomString() {
        RandomStringUtils randomObject = new RandomStringUtils();
        return randomObject.randomAlphanumeric(7);
    }


}
