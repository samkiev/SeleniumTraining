import static org.junit.Assert.*;
import static utils.DateTimeMatcher.*;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pages.LoginPage;
import pages.MainPage;
import pages.ProjectPage;
import utils.User;

@RunWith(JUnit4.class)
public class SeleniumTest extends BaseTest {

	@Test
	public void loginTest() {
		User user = User.setLoginAndPassword("admin", "admin");
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
	public void dateTest() {
		ProjectPage projectPage = new ProjectPage(driver).get();
		LocalDateTime buildHistoryDate = projectPage.getBuildHistoryDate();
		LocalDateTime buildDate = projectPage.goToBuildPage()
				.getBuildPageDate();
		assertThat(buildHistoryDate, dateEquals(buildDate));
	}
}
