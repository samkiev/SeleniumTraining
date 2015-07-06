import static org.junit.Assert.*;
import static utils.DateTimeMatcher.*;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;

import pages.LoginPage;
import pages.MainPage;
import pages.ProjectPage;
import rules.DefaultRule;
import utils.User;
import utils.WebDriverController;

@RunWith(JUnit4.class)
public class SeleniumTest {

	private static WebDriver driver = WebDriverController.getDriver();
	Logger log = LogManager.getLogger(SeleniumTest.class.getName());
	

	@ClassRule 
	public static DefaultRule rule = new DefaultRule(driver);
	
	@Test
	public void loginTest() {
		log.info("Login Test started");
		MainPage page = new LoginPage(driver).get()
				.loginAs(User.setLoginAndPassword("admin", "admin"));
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
