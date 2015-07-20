package TestsForFrameWork;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import pages.MainPage;
import pages.ProjectPage;


/**
 * Created by tetiana.sviatska on 7/15/2015.
 */
public class TestsForMainPage extends BaseTest {

    @Test
    public void createPageWithNullArgument() {
        try {
            MainPage mn = new MainPage(null);
        } catch (NullPointerException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void openMainPageUsingWrongConstructor() {
        try {
            MainPage mn = new MainPage(driver, true).get();
        } catch (Error e) {
            Assert.fail("Page should be loaded successfully");
        }
    }
//
//    @ChangeViewConfig("test")
//    @Test
//    public void choseNonexistentProject() {
//        try {
//            MainPage mp = new MainPage(driver).get();
//            ProjectPage projectPage = mp.choseProjectLink();
//            Assert.assertTrue(driver.getCurrentUrl().startsWith("http://seltr-kbp1-1.synapse.com:8080/job/"));
//            Assert.assertTrue(driver.getCurrentUrl().length() > "http://seltr-kbp1-1.synapse.com:8080/job/".length());
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @ChangeViewConfig("test")
//    @Test
//    public void getNameFromNonexistentProject() {
//        try {
//            MainPage mp = new MainPage(driver).get();
//            String projectName = mp.getFirstProjectName();
//            Assert.assertFalse(projectName.isEmpty());
//        } catch (Throwable e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @ChangeViewConfig("test2")
//    @Test
//    public void choseProjectFromChangedTable() {
//        try {
//            MainPage mp = new MainPage(driver).get();
//            ProjectPage projectPage = mp.choseProjectLink();
//            Assert.assertTrue(driver.getCurrentUrl().startsWith("http://seltr-kbp1-1.synapse.com:8080/job/"));
//            Assert.assertTrue(driver.getCurrentUrl().length() > "http://seltr-kbp1-1.synapse.com:8080/job/".length());
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @ChangeViewConfig("test2")
//    @Test
//    public void getProjectNameFromChangedTable() {
//        try {
//            MainPage mp = new MainPage(driver).get();
//            String projectName = mp.getFirstProjectName();
//            Assert.assertFalse(projectName.isEmpty());
//        } catch (Throwable e) {
//            Assert.fail(e.getMessage());
//        }
//    }

    @Test
    public void searchForNonexistentUser() {
        try {
            MainPage mp = new MainPage(driver).get();
            mp.searchUser(null, "");
        } catch (TimeoutException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void selectNonexistentUserFromAutoComplete() {
        try {
            MainPage mp = new MainPage(driver).get();
            mp.searchUser("1", null);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

}
