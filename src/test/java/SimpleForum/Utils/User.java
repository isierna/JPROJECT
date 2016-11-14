package SimpleForum.Utils;

import SimpleForum.Pages.ForumHomePage;
import SimpleForum.Pages.ForumSignInPage;
import org.openqa.selenium.WebDriver;


/**
 * Created by Ira on 11/3/16.
 */
public class User {
    static ForumSignInPage signInPage;
 //   static ForumHomePage homePage;

    public String existingUserPass = "DgCFCyo";
    public String existingUserName = "Ira" + existingUserPass;



    public void signIn(WebDriver driver, ForumHomePage homePage) {
        signInPage = new ForumSignInPage(driver);
        signInPage.goTo(homePage.linkToSignInPage);
        signInPage.userName.sendKeys(existingUserName);
        signInPage.password.sendKeys(existingUserPass);
        signInPage.signInButton.click();
        signInPage.assertConfirmationPresent();
        System.out.println("Success");


    }
}
