package Simple_Forum.Tests;

import Simple_Forum.Pages.ForumHomePage;
import Simple_Forum.Pages.ForumRegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



/**
 * Created by Ira on 10/10/16.
 */
public class SignUpTest extends AbstractTest{
    static ForumHomePage homePage;
    static ForumRegistrationPage registrationPage;
    WebDriver driver;
    public String random = generateRandomString();

    @BeforeMethod
    public void openMainPage() {
        driver = new FirefoxDriver();
        homePage = new ForumHomePage(driver);
        homePage.go();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void signUP() {
        homePage.at();
        homePage.linkToRegistrtationPage.click();

        registrationPage = new ForumRegistrationPage(driver);
        registrationPage.userNameInput.sendKeys("Ira" + random);
        registrationPage.password1.sendKeys(random);
        registrationPage.password2.sendKeys(random);
        registrationPage.email.sendKeys(random + "@gmail.com");
        registrationPage.addUserButton.click();
        registrationPage.assertConfirmationPresent();
        System.out.println("Success");
    }
}
