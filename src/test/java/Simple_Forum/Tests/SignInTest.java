package Simple_Forum.Tests;

import Simple_Forum.Pages.ForumHomePage;
import Simple_Forum.Pages.ForumSignInPage;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Ira on 10/20/16.
 */
public class SignInTest extends AbstractTest{
    static ForumHomePage homePage;
    static ForumSignInPage signInPage;
    WebDriver driver;

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
    public void signIn() {
        homePage.at();
        signInPage = new ForumSignInPage(driver);
        signInPage.goTo(homePage.linkToSignInPage);
        signInPage.userName.sendKeys("Ira" + signInPage.existingUserNameAndPass);
        signInPage.password.sendKeys(signInPage.existingUserNameAndPass);
        signInPage.signInButton.click();
        signInPage.assertConfirmationPresent();
        System.out.println("Success");

    }
}
