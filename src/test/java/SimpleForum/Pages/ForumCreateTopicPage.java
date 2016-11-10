package SimpleForum.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Ira on 11/2/16.
 */
public class ForumCreateTopicPage extends BasePage {
    public ForumCreateTopicPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String currentDateAndTime = currentDateAndTime();

    @FindBy(xpath = "//div[@id=\"content\"]")
    public WebElement confirmationMessage;

    @FindBy(xpath = "//input[@name=\"topic_subject\"]")
    public WebElement subject;

    @FindBy(xpath = "//select[@name=\"topic_cat\"]")
    public WebElement category;

    @FindBy(xpath = "//textarea[@name=\"post_content\"]")
    public WebElement messageInput;

    @FindBy(xpath = "//input[@value=\"Create topic\"]")
    public WebElement createTopicButton;

    public Select createSelect(WebElement category) {
        Select select = new Select(category);
        return select;
    }

    @Override
    public void at() {}

    @Override
    public void go() {
        waitUntil(presenceOfElementLocated(By.xpath("//input[@value=\"Create topic\"]")));
    }



}
