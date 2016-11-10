package SimpleForum.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Ira on 10/10/16.
 */
public class ForumHomePage extends BasePage {
    public ForumHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@id=\"sign_up\"]")
    public WebElement linkToRegistrtationPage;

    @FindBy(xpath = "//a[@id=\"sign_in\"]")
    public WebElement linkToSignInPage;

    @FindBy(xpath = "//a[@id=\"create_topic\"]")
    public WebElement linkToCreateTopicPage;

    @FindBy(linkText = "New category")
    public WebElement category;

    @Override
    public void go() {
        driver.get("http://koncikowski.pl/forum/index.php");
        at();
    }

    @Override
    public void at() {
        waitUntil(presenceOfElementLocated(By.xpath("//tr[th=\"Category\" and th=\"Last topic\"]")));    }

}
