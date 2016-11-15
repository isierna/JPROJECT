package SimpleForum.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Ira on 11/15/16.
 */
public class ForumReplyConfirmationPage extends BasePage {
    public ForumReplyConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id=\"content\"]/a")
    public WebElement linkToTopicPage;

    @Override
    public void at() {
        waitUntil(presenceOfElementLocated(By.xpath("//div[@id=\"content\"]/a")));
    }
}
