import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pages.BuildPage;
import pages.LoginPage;
import pages.MainPage;
import pages.ProjectPage;
import utils.ApiDataGetter;
import utils.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.*;
import static utils.DateTimeMatcher.dateEquals;

@RunWith(JUnit4.class)
public class SeleniumTest extends BaseTest {

    private ApiDataGetter api = ApiDataGetter.getAPUsingDefaultCredentials();
    User user = User.setLoginAndPassword("admin", "admin");

    @Test
    public void loginTest() {
        MainPage page = new LoginPage(driver).get().loginAs(user.getLogin(),
                user.getPassword());
        assertTrue(page.isLoggedIn());
    }

    @Test
    public void enterProjectPageTest() {
        String projectName = getRandomItem(new MainPage(driver).get().getListOfProject());
        ProjectPage projectPage = new ProjectPage(driver, projectName).get();
        assertEquals(projectName, projectPage.getProjectName());
    }

    @Test
    public void dateTest() {
        String randomProjectName = getRandomItem(new MainPage(driver).get().getListOfProject());
        ProjectPage projectPage = new ProjectPage(driver, randomProjectName).get();
        String randomBuild = getRandomItem(projectPage.getBuildNumbers());
        LocalDateTime buildHistoryDate = projectPage.getBuildHistoryDate(randomBuild);
        BuildPage bp = new BuildPage(driver, randomProjectName, randomBuild).get();
        LocalDateTime buildDate = bp.getBuildPageDate();
        assertThat(buildHistoryDate, dateEquals(buildDate));
        assertThat(api.getApiBuildDate(randomProjectName, randomBuild), dateEquals(buildHistoryDate));
    }

    private static String getRandomItem(List<String> buildElementsNames){
        Random random = new Random();
        return buildElementsNames.get(random.nextInt(buildElementsNames.size()));
    }
}
