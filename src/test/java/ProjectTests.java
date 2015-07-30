import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import pages.BuildPage;
import pages.CreateNewProjectPage;
import pages.ProjectPage;
import utils.ApiDataGetter;
import utils.StringGenerator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static utils.DateTimeMatcher.dateEquals;

/**
 * Created by oleg.semenov on 7/23/2015.
 */
public class ProjectTests extends BaseUITest {

    private static String projectName;
    private static WebDriver wd;
    private static ProjectPage mockProject;
    private boolean isNewProjectCreated = false;

    @Rule
    public TestRule userAvailabilityRule = (base, d) -> new Statement() {

        @Override
        public void evaluate() throws Throwable {
            try {
                base.evaluate();
            }
            finally {
                if (isNewProjectCreated) {
                    mockProject.deleteProject();
                    projectName = null;
                }
            }
        }
    };

    private ProjectPage getTestProjectPage() {
        String existingProjectName = getExistingProjectName();
        return existingProjectName != null ? new ProjectPage(driver, StringGenerator.encode(existingProjectName)).get() : null;
    }

    private String getExistingProjectName() {
        List<String> projects = new ArrayList<>();
        try {
            JSONArray info = ApiDataGetter.getAPUsingDefaultCredentials().getMainPageInfo().getJSONArray("jobs");
            for (int i = 0; i < info.length(); i++) {
                projects.add(info.getJSONObject(i).getString("name"));
            }
            if (projects.isEmpty()) {
                projectName = StringGenerator.generateRandomName();
                createMockProject(projectName);
                return projectName;
            }
        }
        catch (JSONException jsonEx){
            System.out.println(jsonEx.getMessage());
        }
        projectName = StringGenerator.getRandomItem(projects);
        return projectName;
    }

    private void createMockProject(@NotNull String projectName) {
            mockProject = new CreateNewProjectPage(wd).get()
                .createNewProject(projectName)
                .saveConfiguration();
            isNewProjectCreated = true;
    }

//    private BuildPage getTestBuildPage(String existedProjectName) {
//        String buildNumber = getExistBuild(existedProjectName);
//        return new BuildPage(driver, existedProjectName, buildNumber);
//    }

    private String getExistBuild(String existedProjectName) {
        List<String> builds = new ArrayList<>();
        try {
            JSONArray buildsList = ApiDataGetter.getAPUsingDefaultCredentials().getProjectPageInfo(existedProjectName).getJSONArray("builds");
            for (int i = 0; i < buildsList.length(); i++) {
                builds.add(String.valueOf(buildsList.getJSONObject(i).getInt("number")));
            }
            if (builds.isEmpty()) {
                new ProjectPage(driver, existedProjectName).get().addBuild();
                return getExistBuild(existedProjectName);
            }
        }
        catch (JSONException jsonEx){
            System.out.println(jsonEx.getMessage());
        }
        return StringGenerator.getRandomItem(builds);
    }

    @Test
    public void enterProjectPageTest() {
        ProjectPage testProjectPage = getTestProjectPage();
        Assume.assumeNotNull(testProjectPage);
        assertTrue(testProjectPage.isOnProjectPage());
    }

    @Test
    public void complianceCheckingDate(){
        String existedProjectName = getExistingProjectName();
        Assume.assumeNotNull(existedProjectName);
        String buildNumber = getExistBuild(existedProjectName);
        Assume.assumeNotNull(buildNumber);
        LocalDateTime buildHistoryDate = new ProjectPage(driver, existedProjectName).get().getBuildHistoryDate(buildNumber);
        LocalDateTime buildPage = new BuildPage(driver, existedProjectName, buildNumber).get().getBuildPageDate();
        LocalDateTime apiBuildPageDate = ApiDataGetter.getAPUsingDefaultCredentials().getApiBuildDate(existedProjectName, buildNumber);
        Assert.assertThat(buildHistoryDate, dateEquals(buildPage));
        Assert.assertThat(apiBuildPageDate, dateEquals(buildHistoryDate));
    }

    @Test
    public void checkPossibilityToAddNewBuild(){
        ProjectPage configurePage = getTestProjectPage().addBuild();
        String buildNumber = configurePage.getAddedBuildNumber();
        Assert.assertNotNull(buildNumber);
    }

//    @Test
//    public void createNewProjectPossibility(){
//        ProjectPage configurePage =  new CreateNewProjectPage(driver).get()
//                .createNewProject(projectName)
//                .saveConfiguration();
//        Assert.assertEquals(configurePage.getProjectName(), projectName);
//    }
//
//    @Test
//    public void deleteExistProject(){
//        new ProjectPage(driver, projectName).get().deleteProject();
//    }
}
