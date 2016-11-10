package SimpleForum.Tests;

import SimpleForum.Pages.ForumHomePage;
import SimpleForum.Pages.ForumSignInPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Ira on 10/20/16.
 */
public class SignInTest extends BaseTest {
    static ForumHomePage homePage;
    static ForumSignInPage signInPage;

    @BeforeMethod
    public void openMainPage() {
        homePage = new ForumHomePage(driver);
        homePage.go();
    }

    @Test
    public void signIn() {
        homePage.at();
        signInPage = new ForumSignInPage(driver);
        signInPage.goTo(homePage.linkToSignInPage);
        signInPage.userName.sendKeys("Ira" + signInPage.existingUserNameAndPass);
        signInPage.password.sendKeys(signInPage.existingUserNameAndPass);
        signInPage.signInButton.click();
        signInPage.assertConfirmationPresent(); //TODO: change to the actual assert
        System.out.println("Success");

    }
}
