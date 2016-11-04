package Simple_Forum.Utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

/**
 * Created by Ira on 11/3/16.
 */
public class User {
    public void userSignedIn(WebDriver driver) {
        Cookie cookie = new Cookie("PHPSESSID", "2b93c203d0c54608017c7a3a4454c096");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();

    }
}
