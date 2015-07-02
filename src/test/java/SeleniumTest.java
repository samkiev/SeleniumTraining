import static org.junit.Assert.*;
import static utils.DateTimeMatcher.*;
import java.time.LocalDateTime;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.ProjectPage;
import utils.User;
import utils.WebDriverController;

@RunWith(JUnit4.class)
public class SeleniumTest {

	private static WebDriver driver = WebDriverController.getDriver();
	private static User admin = new User("admin", "admin");

	@AfterClass
	public static void shutDown() {
		driver.quit();
	}

	@Test
	public void loginTest() {
		MainPage page = new LoginPage(driver).get()
				.loginAs(admin);
		assertTrue(page.isLoggedIn());
	}

	@Test
	public void enterProjectPageTest(){		
		MainPage mainPage = new MainPage(driver).get();		
		String projectNameInList = mainPage.getProjectName();		
		ProjectPage projectPage = mainPage.choseFirstProjectLink();
		assertEquals(projectNameInList, projectPage.getProjectName());
	}

	@Test 
	public void dateTest(){
		ProjectPage projectPage = new ProjectPage(driver).get();
		LocalDateTime buildHistoryDate = projectPage.getBuildHistoryDate();
		LocalDateTime buildDate = projectPage.goToBuildPage().getBuildPageDate();
		assertThat(buildHistoryDate, dateEquals(buildDate));
	}
}
