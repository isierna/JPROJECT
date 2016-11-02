package Simple_Forum.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Ira on 11/2/16.
 */
public class ForumCreateTopicPage extends AbstractPage{
    public ForumCreateTopicPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name=\"topic_subject\"]")
    public WebElement subject;

    @FindBy(xpath = "//select[@name=\"topic_cat\"]")
    public WebElement category;

    @FindBy(xpath = "//textarea[@name=\"post_content\"]")
    public WebElement message;

    @FindBy(xpath = "//input[@value=\"Create topic\"]")
    public WebElement createTopicButton;

    @Override
    public void at() {}

    @Override
    public void go() {
        waitUntil(presenceOfElementLocated(By.xpath("//input[@value=\"Create topic\"]")));
    }



}
