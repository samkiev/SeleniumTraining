package TestsForFrameWork;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import pages.BuildPage;
import pages.MainPage;
import pages.ProjectPage;

/**
 * Created by tetiana.sviatska on 7/16/2015.
 */
public class TestsForProjectPage extends BaseTest {

//    @Test
//    public void loadPageWithNullArgument() {
//        try {
//            ProjectPage projectPage = new ProjectPage(null).get();
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @ChangeViewConfig("test")
//    @Test
//    public void openPageWithAnotherDefaultView() {
//        try {
//           new ProjectPage(driver).get();
//        } catch (NoSuchElementException e) {
//            Assert.fail(e.getMessage());
//        }
//    }
////
//    @ChangeViewConfig("test3")
//    @Test
//    public void getDateFromNonexistentBuild() {
//        try {
//            ProjectPage projectPage = new ProjectPage(driver).get();
//            projectPage.getBuildHistoryDate();
//        } catch (Throwable e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @ChangeViewConfig("test3")
//    @Test
//    public void goToNonexistentBuild() {
//        try {
//            ProjectPage projectPage = new ProjectPage(driver).get();
//            BuildPage buildPage = projectPage.goToBuildPage();
//            Assert.assertTrue(driver.getCurrentUrl().substring("http://seltr-kbp1-1.synapse.com:8080/job/".length()).matches("(.+)/(\\d+)/"));
//        } catch (Throwable e) {
//            Assert.fail(e.getMessage());
//        }
//    }

}
