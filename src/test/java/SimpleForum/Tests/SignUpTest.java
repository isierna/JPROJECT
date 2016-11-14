package SimpleForum.Tests;

import SimpleForum.Pages.ForumHomePage;
import SimpleForum.Pages.ForumRegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



/**
 * Created by Ira on 10/10/16.
 */
public class SignUpTest extends BaseTest {
    static ForumRegistrationPage registrationPage;

    @Test
    public void signUP() {
        registrationPage = new ForumRegistrationPage(driver);
        registrationPage.goTo(homePage.linkToRegistrtationPage);
        registrationPage.userNameInput.sendKeys("Ira" + registrationPage.random);
        registrationPage.password1.sendKeys(registrationPage.random);
        registrationPage.password2.sendKeys(registrationPage.random);
        registrationPage.email.sendKeys(registrationPage.random + "@gmail.com");
        registrationPage.addUserButton.click();
        registrationPage.assertConfirmationPresent();
    }
}
