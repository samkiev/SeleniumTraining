import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.BuildPage;
import pages.LoginPage;
import pages.MainPage;
import pages.ProjectPage;


public class SeleniumTests {

	private static WebDriver driver = new FirefoxDriver();;
	private String login = "admin";
	private String password = "admin";
	
	
	/*@Before
	public void before() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}*/
	
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
	
}
