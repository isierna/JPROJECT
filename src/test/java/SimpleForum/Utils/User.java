package SimpleForum.Utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

/**
 * Created by Ira on 11/3/16.
 */
public class User {

    public void userSignedIn(WebDriver driver) {
        Cookie cookie = new Cookie("PHPSESSID", "5e38ccf6a98db6100e8f3a7f8914603b"); //TODO: session id still expires with the time
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();

    }

    public String user_name = "IraDgCFCyo";
}
