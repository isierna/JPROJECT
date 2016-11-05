package Simple_Forum.Tests;

import Simple_Forum.Pages.ForumCategoryPage;
import Simple_Forum.Pages.ForumCreateTopicPage;
import Simple_Forum.Pages.ForumHomePage;
import Simple_Forum.Pages.ForumTopicPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Simple_Forum.Utils.User;


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
    WebDriver driver;

    @BeforeMethod
    public void openMainPage() {
        driver = new FirefoxDriver();
        homePage = new ForumHomePage(driver);
        homePage.go();
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println(testResult.getStatus());
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/Ira/Pictures/1/testScreenShot.jpg"));
            driver.quit();
        }
        driver.quit();
    }


    @Test
    public void createTopic() {
        user = new User();
        user.userSignedIn(driver);
        homePage.linkToCreateTopicPage.click();
        createTopicPage = new ForumCreateTopicPage(driver);
        createTopicPage.at();
        createTopicPage.subject.sendKeys("Nature Pollution");
        Select select = new Select(createTopicPage.category);
        select.selectByVisibleText("New category");
        String message = "New Message that was created on " + currentDateAndTime();
        createTopicPage.messageInput.sendKeys(message);
        createTopicPage.createTopicButton.click();

        Assert.assertEquals(createTopicPage.confirmationMessage.getText(),"Create a topic"+ "\n" +"You have successfully created your new topic.");

        homePage.go();
        homePage.category.click();
        forumCategoryPage = new ForumCategoryPage(driver);
        forumCategoryPage.at();
        forumCategoryPage.findLastAddedTopicInTheCategory().click();

        forumTopicPage = new ForumTopicPage(driver);
        forumTopicPage.at();

        Assert.assertEquals(forumTopicPage.message.getText(), message);

        String user_posted_topic = forumTopicPage.user.getText().substring(0,10);

        Assert.assertEquals(user_posted_topic,user.user_name);

    }
}
