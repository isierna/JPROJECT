package SimpleForum.Tests;

import SimpleForum.Pages.ForumCategoryPage;
import SimpleForum.Pages.ForumCreateTopicPage;
import SimpleForum.Pages.ForumHomePage;
import SimpleForum.Pages.ForumTopicPage;
import SimpleForum.Utils.User;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ira on 11/2/16.
 */
public class CreateTopicTest extends BaseTest {
    static ForumHomePage homePage;
    static ForumCreateTopicPage createTopicPage;
    static ForumTopicPage forumTopicPage;
    static ForumCategoryPage forumCategoryPage;
    static User user;


    @BeforeMethod
    public void openMainPage() {
        homePage = new ForumHomePage(driver);
        homePage.go();
    }


    @Test
    public void createTopic() {
        user = new User();
        user.userSignedIn(driver);
        homePage.linkToCreateTopicPage.click();
        createTopicPage = new ForumCreateTopicPage(driver);
        createTopicPage.at();
        createTopicPage.subject.sendKeys("Nature Pollution");
        createTopicPage.createSelect(createTopicPage.category).selectByVisibleText("New category"); //TODO: incorrect category selected
        String message = "New Message that was created on " + createTopicPage.currentDateAndTime;
        createTopicPage.messageInput.sendKeys(message);
        createTopicPage.createTopicButton.click();

        Assert.assertEquals(createTopicPage.confirmationMessage.getText(), "Create a topic" + "\n" + "You have successfully created your new topic.");

        homePage.go();
        homePage.category.click();
        forumCategoryPage = new ForumCategoryPage(driver);
        forumCategoryPage.at();
        forumCategoryPage.findLastAddedTopicInTheCategory().click();

        forumTopicPage = new ForumTopicPage(driver);
        forumTopicPage.at();

        Assert.assertEquals(forumTopicPage.message.getText(), message);

        String userPostedTopic = forumTopicPage.user.getText().substring(0, 10);

        Assert.assertEquals(userPostedTopic, user.user_name);

    }
}
