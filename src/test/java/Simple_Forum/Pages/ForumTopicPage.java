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
public class ForumTopicPage extends BasePage {
    public ForumTopicPage(WebDriver currentDriver) {
        driver = currentDriver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//td[@class=\"post-content\"]")
    public WebElement message;

    @FindBy(xpath = "//td[@class=\"user-post\"]")
    public WebElement user;

    @Override
    public void at() {
        waitUntil(presenceOfElementLocated(By.xpath("//input[@value=\"Submit reply\"]")));
    }
}
