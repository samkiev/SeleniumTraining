import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.junit.Assert;
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
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.*;
import static utils.DateTimeMatcher.dateEquals;

@RunWith(JUnit4.class)
public class SeleniumTest extends BaseTest {

    private ApiDataGetter api = ApiDataGetter.getAPUsingDefaultCredentials();
    User user = User.setLoginAndPassword("admin", "admin");

    public List<String> getListProjectForUsingApi(){
        JSONArray projects =  api.getPageApi(MainPage.MAIN_PAGE_URL).getJSONArray("jobs");
        String[] projectList = new String[projects.length()];
        for (int i = 0; i < projects.length(); i++){
            projectList[i] = projects.getJSONObject(i).getString("name").replaceAll(" ", "%20");
        }
        return Arrays.asList(projectList);
    }

    public List<String> getBuildNumberForUsingApi(String project){
        JSONArray builds = api.getPageApi(MainPage.MAIN_PAGE_URL + "/job/" + project + "/").getJSONArray("builds");
        String[] buildStringList = new String[builds.length()];
        for (int i = 0; i < builds.length(); i++){
            buildStringList[i] = String.valueOf(builds.getJSONObject(i).getInt("number"));
        }
        return Arrays.asList(buildStringList);
    }

    @Test
    public void enterProjectWithApi(){
        System.out.println(getListProjectForUsingApi());
    }

    @Test
    public void loginTest() {
        MainPage page = new LoginPage(driver).get().loginAs(user.getLogin(),
                user.getPassword());
        assertTrue(page.isLoggedIn());
    }

    @Test
    public void enterProjectPageTest() {
        String projectName = getRandomItem(getListProjectForUsingApi());
        ProjectPage projectPage = new ProjectPage(driver, projectName).get();
        assertEquals(projectName, projectPage.getProjectName());
    }

    @Test
    public void dateTestForUsingApi(){
        String project = getRandomItem(getListProjectForUsingApi());
        String build = getRandomItem(getBuildNumberForUsingApi(project));
        Assert.assertNotNull(api.getApiBuildDate(project, build));
    }

    @Test
    public void dateTest() {
        String randomProjectName = getRandomItem(getListProjectForUsingApi());
        ProjectPage projectPage = new ProjectPage(driver, randomProjectName).get();
        String randomBuild = getRandomItem(projectPage.getBuildNumbers());
        LocalDateTime buildHistoryDate = projectPage.getBuildHistoryDate(randomBuild);
        BuildPage bp = new BuildPage(driver, randomProjectName, randomBuild).get();
        LocalDateTime buildDate = bp.getBuildPageDate();
        assertThat(buildHistoryDate, dateEquals(buildDate));
        assertThat(api.getApiBuildDate(randomProjectName, randomBuild), dateEquals(buildHistoryDate));
    }

    private static String getRandomItem(@NotNull List<String> items){
        Random random = new Random();
        if (items.size() != 0) {
            return items.get(random.nextInt(items.size()));
        }throw new Error("The Project has no builds");
    }
}
