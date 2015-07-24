import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import pages.CreateNewProjectPage;
import pages.ProjectPage;
import utils.ApiDataGetter;
import utils.RandomGenerator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static utils.DateTimeMatcher.dateEquals;

/**
 * Created by oleg.semenov on 7/23/2015.
 */
public class SeleniumTestsWithPrecondition extends BaseUITest {

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
                    wd.quit();
                }
            }
        }
    };

    private ProjectPage getTestProjectPage() {
        String existingProjectName = getExistingProjectName();
        return existingProjectName != null ? new ProjectPage(driver, existingProjectName).get() : null;
    }

    private String getExistingProjectName() {
        JSONArray info = ApiDataGetter.getAPUsingDefaultCredentials().getMainPageInfo().getJSONArray("jobs");
        List<String> projects = new ArrayList<>();
        for (int i = 0; i < info.length(); i++) {
            projects.add(info.getJSONObject(i).getString("name").replaceAll(" ", "%20"));
        }
        if (projects.isEmpty()) {
            isNewProjectCreated = true;
            projectName = RandomGenerator.generateName();
            createMockProject(projectName);
            return projectName;
        }
        projectName = RandomGenerator.getRandomItem(projects);
        return projectName;
    }

    private void createMockProject(String projectName) {
        try {
            mockProject = new CreateNewProjectPage(wd).get()
                .createNewProject(projectName)
                .saveConfiguration();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



    @Test
    public void enterProjectPageTest() {
        ProjectPage testProjectPage = getTestProjectPage();
        Assume.assumeNotNull(testProjectPage);
        assertEquals(projectName, testProjectPage.getProjectName());
    }

//    @Test
//    public void complianceCheckingDate(){
//        LocalDateTime buildPageDate = getBuildPageDate();
//        LocalDateTime apiBuildDate = getApiBuildDate();
//        Assert.assertThat(getExistingProjectName(), dateEquals(buildHistoryDate));
//    }

    @Test
    public void checkPossibilityToAddNewBuild(){
        ProjectPage configurePage =  new ProjectPage(driver, projectName).get();
        String buildNumber = configurePage.getAddedBuildNumber();
        System.out.println(buildNumber);
    }

    @Test
    public void createNewProjectPossibility(){
        ProjectPage configurePage =  new CreateNewProjectPage(driver).get()
                .createNewProject(projectName)
                .saveConfiguration();
        Assert.assertEquals(configurePage.getProjectName(), projectName);
    }

    @Test
    public void deleteExistProject(){
        new ProjectPage(driver, projectName).get().deleteProject();
    }
}
