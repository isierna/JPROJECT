package SimpleForum.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;


/**
 * Created by Ira on 10/10/16.
 */
public class ForumRegistrationPage extends BasePage {
    public ForumRegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String random = generateRandomString();


    @FindBy(xpath = "//input[@name=\"user_name\"]")
    public WebElement userNameInput;

    @FindBy(xpath = "//input[@name=\"user_pass\"]")
    public WebElement password1;

    @FindBy(xpath = "//input[@name=\"user_pass_check\"]")
    public WebElement password2;

    @FindBy(xpath = "//input[@name=\"user_email\"]")
    public WebElement email;

    @FindBy(xpath = "//input[@value=\"Add user\"]")
    public WebElement addUserButton;

    @Override
    public void goTo(WebElement element) {
        element.click();
        at();
    }

    @Override
    public void at() {
        waitUntil(presenceOfElementLocated(By.xpath("//input[@value=\"Add user\"]")));
    }

    public void assertConfirmationPresent() {
        waitUntil(textToBePresentInElementLocated(By.xpath("//div[@id=\"content\"]"),"Successfully registered. You can now "));

    }
}
