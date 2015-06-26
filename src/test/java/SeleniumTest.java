import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import pages.BuildPage;
import pages.LoginPage;
import pages.MainPage;
import pages.ProjectPage;
import utils.WebDriverController;

@RunWith(JUnit4.class)
public class SeleniumTest {

	private static WebDriver driver = WebDriverController.getDriver();
	private String login = "admin";
	private String password = "admin";
	
	
	@AfterClass
	public static void shutDown() {
		driver.quit();
	}
	
	@Test
	public void loginTest() {
		MainPage page = new LoginPage(driver)
			.get()
			.loginAs(login, password);
		assertEquals("log out", page.getLogOutLinkText());
	}

	@Test
	public void enterProjectTest(){		
		MainPage mainPage = new MainPage(driver).get();		
		String projectNameInList = mainPage.getProjectName();		
		ProjectPage projectPage = mainPage.choseFirstProjectLink();
		assertEquals(projectNameInList, projectPage.getProjectName());
	}
	
	@Test 
	public void dateTest(){
		
		ProjectPage projectPage = new ProjectPage(driver);
		projectPage.get();		
		String buildHistoryDate = projectPage.getBuildHistoryDate();
		BuildPage buildPage = projectPage.goToBuildPage();	
		assertEquals(buildHistoryDate, buildPage.getBuildPageDate());
		
	}
	
	@Test
	public void mynewtest() {
		new BuildPage(driver).get();
		
	}
	
}
