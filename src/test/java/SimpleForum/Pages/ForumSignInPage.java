package SimpleForum.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Ira on 10/21/16.
 */
public class ForumSignInPage extends BasePage {
    public ForumSignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//input[@name=\"user_name\"]")
    public WebElement userName;

    @FindBy(xpath = "//input[@name=\"user_pass\"]")
    public WebElement password;

    @FindBy(xpath = "//input[@id=\"sign_in\"]")
    public  WebElement signInButton;

    @Override
    public void goTo(WebElement element) {
        element.click();
        at();
    }

    @Override
    public  void at() {
        waitUntil(elementToBeClickable(signInButton));
    }

    public void assertConfirmationPresent() {
        waitUntil(presenceOfElementLocated(By.linkText("Proceed to the forum overview")));
    }
}
