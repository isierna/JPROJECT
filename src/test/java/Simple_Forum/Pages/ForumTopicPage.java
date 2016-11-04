package Simple_Forum.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Ira on 11/3/16.
 */
public class ForumTopicPage extends AbstractPage {
    public ForumTopicPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//td[@class=\"post-content\"]")
    public WebElement message;

    @Override
    public void at() {
        waitUntil(presenceOfElementLocated(By.xpath("//input[@value=\"Submit reply\"]")));
    }
}
