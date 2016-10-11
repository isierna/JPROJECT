package Simple_Forum.Tests;

import Simple_Forum.Pages.ForumHomePage;
import Simple_Forum.Pages.ForumRegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Ira on 10/10/16.
 */
public class SignUp extends AbstractTest{
    static ForumHomePage homePage;
    static ForumRegistrationPage registrationPage;

    @BeforeMethod
    public void openMainPage() {
        WebDriver driver = new FirefoxDriver();
        homePage = new ForumHomePage(driver);
        homePage.go();
        System.out.println("success");
    }

    @Test
    public void signUP() {
        homePage.at();
        homePage.linkToRegistrtationPage.click();

        registrationPage = new ForumRegistrationPage();
        registrationPage.at();


    }

}
