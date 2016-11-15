package SimpleForum.Tests;

import SimpleForum.Pages.ForumSignInPage;
import SimpleForum.Utils.User;
import org.testng.annotations.Test;

/**
 * Created by Ira on 10/20/16.
 */
public class SignInTest extends BaseTest {
    static ForumSignInPage signInPage;
    static User user;

    @Test
    public void signIn() {
        homePage.at();
        signInPage = new ForumSignInPage(driver);
        signInPage.goTo(homePage.linkToSignInPage);
        signInPage.userName.sendKeys("IraDgCFCyo");
        signInPage.password.sendKeys("DgCFCyo");
        signInPage.signInButton.click();
        signInPage.assertConfirmationPresent();
        System.out.println("Success");
    }
}
