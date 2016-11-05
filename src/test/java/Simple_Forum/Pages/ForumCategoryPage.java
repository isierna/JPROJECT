package Simple_Forum.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

/**
 * Created by Ira on 11/3/16.
 */
public class ForumCategoryPage extends BasePage {
    public ForumCategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id=\"content\"]/h2")
    public WebElement category;

    @Override
    public void at() {
        waitUntil(textToBePresentInElement(category, "Topics in \"New category\" category"));
    }

    public WebElement findLastAddedTopicInTheCategory() {
        List<WebElement> allTopics = driver.findElements(By.xpath("//a[ancestor::tr]"));
        int i = allTopics.size() - 1;
        WebElement lastAddedTopic = allTopics.get(i);
        return lastAddedTopic;
    }
}
