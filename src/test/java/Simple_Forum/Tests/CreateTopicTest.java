package Simple_Forum.Tests;

import Simple_Forum.Pages.ForumCreateTopicPage;
import Simple_Forum.Pages.ForumHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Ira on 11/2/16.
 */
public class CreateTopicTest extends AbstractTest{
    static ForumHomePage homePage;
    static ForumCreateTopicPage createTopicPage;
    WebDriver driver;

    @BeforeMethod
    public void openMainPage() {
        driver = new FirefoxDriver();
        homePage = new ForumHomePage(driver);
        homePage.go();
    }

    @AfterMethod
    public void  closeBrowser() {
        driver.quit();
    }

    @Test
    public void createTopic() {
        homePage.at();
        userSignedIn(driver);
        homePage.linkToCreateTopicPage.click();
        createTopicPage = new ForumCreateTopicPage(driver);
        createTopicPage.at();
        createTopicPage.subject.sendKeys("Nature Pollution");
        Select select = new Select(createTopicPage.category);
        select.selectByValue("3");
        createTopicPage.message.sendKeys("New Message that was created on " + currentDateAndTime());
        createTopicPage.createTopicButton.click();
    }
}
