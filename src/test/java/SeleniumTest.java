import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pages.BuildPage;
import pages.LoginPage;
import pages.MainPage;
import pages.ProjectPage;
import utils.ApiDataGetter;
import utils.User;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static utils.DateTimeMatcher.dateEquals;

@RunWith(JUnit4.class)
public class SeleniumTest extends BaseTest {

    User user = User.setLoginAndPassword("admin", "admin");

    @Test
    public void loginTest() {
        MainPage page = new LoginPage(driver).get().loginAs(user.getLogin(),
                user.getPassword());
        assertTrue(page.isLoggedIn());
    }

    @Test
    public void enterProjectPageTest() {
        MainPage mainPage = new MainPage(driver).get();
        String projectNameInList = mainPage.getProjectName();
        ProjectPage projectPage = mainPage.choseFirstProjectLink();
        assertEquals(projectNameInList, projectPage.getProjectName());
    }

    @Test
    public void dateTest() throws JSONException, IOException {
        ProjectPage projectPage = new ProjectPage(driver).get();
        LocalDateTime buildHistoryDate = projectPage.getBuildHistoryDate();
        LocalDateTime buildDate = projectPage.goToBuildPage()
                .getBuildPageDate();
        String buildVersion = new BuildPage(driver).getBuildVersion();
        assertThat(buildHistoryDate, dateEquals(buildDate));
        assertThat(ApiDataGetter.getApiBuildDate(projectPage.getProjectName(), buildVersion), dateEquals(buildHistoryDate));
    }
}
