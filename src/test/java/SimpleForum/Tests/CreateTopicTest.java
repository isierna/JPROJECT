package SimpleForum.Tests;

import SimpleForum.Pages.ForumCategoryPage;
import SimpleForum.Pages.ForumCreateTopicPage;
import SimpleForum.Pages.ForumTopicPage;
import SimpleForum.Utils.User;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Ira on 11/2/16.
 */
public class CreateTopicTest extends BaseTest {
    static ForumCreateTopicPage createTopicPage;
    static ForumTopicPage forumTopicPage;
    static ForumCategoryPage forumCategoryPage;
    static User user;


    @Test
    public void createTopic() {
        user = new User(driver, homePage);
        homePage.linkToCreateTopicPage.click();
        createTopicPage = new ForumCreateTopicPage(driver);
        createTopicPage.at();
        createTopicPage.subject.sendKeys("Nature Pollution");
        createTopicPage.createSelect(createTopicPage.category).selectByVisibleText("New category");
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

        Assert.assertEquals(userPostedTopic, user.existingUserName);

    }
}
