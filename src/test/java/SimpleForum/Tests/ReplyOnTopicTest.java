package SimpleForum.Tests;

import SimpleForum.Pages.ForumCategoryPage;
import SimpleForum.Pages.ForumReplyConfirmationPage;
import SimpleForum.Pages.ForumTopicPage;
import SimpleForum.Utils.User;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Ira on 11/5/16.
 */
public class ReplyOnTopicTest extends BaseTest {
    static User user;
    static ForumCategoryPage forumCategoryPage;
    static ForumTopicPage forumTopicPage;
    static ForumReplyConfirmationPage forumReplyConfirmationPage;
    static String replyMessage = "this is a reply message";

    @Test
    public void replyOnTopic() {
        user = new User(driver, homePage);
        homePage.go();
        homePage.category.click();
        forumCategoryPage = new ForumCategoryPage(driver);
        forumCategoryPage.at();
        forumCategoryPage.findLastAddedTopicInTheCategory().click();

        forumTopicPage = new ForumTopicPage(driver);
        forumTopicPage.at();

        forumTopicPage.replyInput.sendKeys(replyMessage);
        forumTopicPage.submitReplyButton.click();

        forumReplyConfirmationPage = new ForumReplyConfirmationPage(driver);
        forumReplyConfirmationPage.at();
        forumReplyConfirmationPage.linkToTopicPage.click();
        forumTopicPage.at();

        Assert.assertEquals(forumTopicPage.message.getText(), replyMessage);

    }
}
