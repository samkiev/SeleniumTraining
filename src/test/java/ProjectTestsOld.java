import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pages.BuildPage;
import pages.LoginPage;
import pages.MainPage;
import pages.ProjectPage;
import utils.ApiDataGetter;
import utils.StringGenerator;
import utils.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static utils.DateTimeMatcher.dateEquals;

@RunWith(JUnit4.class)
public class ProjectTestsOld extends BaseUITest {

    private ApiDataGetter api = ApiDataGetter.getAPUsingDefaultCredentials();
    User user = User.setLoginAndPassword("admin", "admin");
    private String randomProjectName = StringGenerator.getRandomItem(getListProjectForUsingApi());

    public List<String> getListProjectForUsingApi() {
        JSONArray projects = api.getPageApi(MainPage.MAIN_PAGE_URL).getJSONArray("jobs");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < projects.length(); i++) {
            list.add(projects.getJSONObject(i).getString("name").replaceAll(" ", "%20"));
        }
        Assume.assumeTrue(list.size() != 0);
        return list;
    }

    public List<String> getBuilsdNumberForUsingApi(@NotNull String project) {
        JSONArray builds = api.getPageApi(String.format(ProjectPage.PROJECT_PAGE_URL, project)).getJSONArray("builds");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < builds.length(); i++) {
            list.add(String.valueOf(builds.getJSONObject(i).getInt("number")));
        }
        Assume.assumeTrue(list.size() != 0);
        return list;
    }

    @Test
    public void loginTest() {
        MainPage page = new LoginPage(driver).get().loginAs(user.getLogin(),
                user.getPassword());
        assertTrue(page.isLoggedIn());
    }

    @Test
    public void enterExistProjectPageTest() {
        String projectName = StringGenerator.getRandomItem(getListProjectForUsingApi());
        ProjectPage projectPage = new ProjectPage(driver, projectName).get();
        assertEquals(projectName, projectPage.getProjectName());
    }

    @Test
    public void dateTestForUsingApi() {
        String project = StringGenerator.getRandomItem(getListProjectForUsingApi());
        String build = StringGenerator.getRandomItem(getBuilsdNumberForUsingApi(project));
        Assert.assertNotNull(api.getApiBuildDate(project, build));
    }

    @Test
    public void complianceCheckingDate() {
        ProjectPage projectPage = new ProjectPage(driver, randomProjectName).get();
        String randomBuild = StringGenerator.getRandomItem(projectPage.getBuildNumbers());
        LocalDateTime buildHistoryDate = projectPage.getBuildHistoryDate(randomBuild);
        BuildPage bp = new BuildPage(driver, randomProjectName, randomBuild).get();
        LocalDateTime buildDate = bp.getBuildPageDate();
        assertThat(buildHistoryDate, dateEquals(buildDate));
        assertThat(api.getApiBuildDate(randomProjectName, randomBuild), dateEquals(buildHistoryDate));
    }


}
