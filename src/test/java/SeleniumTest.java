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
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static utils.DateTimeMatcher.dateEquals;

@RunWith(JUnit4.class)
public class SeleniumTest extends BaseTest {

    private ApiDataGetter api = ApiDataGetter.getAPUsingDefaultCredentials();

    User user = User.setLoginAndPassword("admin", "admin");
    String firstProjectName = getFirstProjectName();
    String randomProjectName = getRandomrojectName();

    @Test
    public void loginTest() {
        MainPage page = new LoginPage(driver).get().loginAs(user.getLogin(),
                user.getPassword());
        assertTrue(page.isLoggedIn());
    }

    @Test
    public void enterProjectPageTest() {
        new MainPage(driver).get();
        ProjectPage projectPage = new ProjectPage(driver, firstProjectName).get();
        assertEquals(randomProjectName, projectPage.getProjectName());
    }

    @Test
    public void dateTest() {
        ProjectPage projectPage = new ProjectPage(driver, randomProjectName).get();
        String randomBuild = getRandomBuild(projectPage.getBuildNumbers());
        LocalDateTime buildHistoryDate = projectPage.getBuildHistoryDate(randomBuild);

        BuildPage bp = new BuildPage(driver, randomProjectName, randomBuild).get();
        LocalDateTime buildDate = bp.getBuildPageDate();
        assertThat(buildHistoryDate, dateEquals(buildDate));
        assertThat(api.getApiBuildDate(randomProjectName, randomBuild), dateEquals(buildHistoryDate));
    }

    private static String getRandomBuild(List<String> buildElementsNames){
        Random random = new Random();
        return buildElementsNames.get(random.nextInt(buildElementsNames.size()));
    }

    private String getFirstProjectName() {
        return new MainPage(driver).get().getFirstProject().getText();
    }

    private String getRandomrojectName() {
        return new MainPage(driver).get().getRandomProject().getText();
    }
}
