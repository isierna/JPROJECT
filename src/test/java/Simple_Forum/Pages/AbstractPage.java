package Simple_Forum.Pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Ira on 10/10/16.
 */
public class AbstractPage {
    WebDriver driver;

    public void go() {}
    public void at() {}

    public void userSignedIn(WebDriver driver) {
        Cookie cookie = new Cookie("PHPSESSID", "2b93c203d0c54608017c7a3a4454c096");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();

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
