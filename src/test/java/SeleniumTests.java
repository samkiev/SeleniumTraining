import static org.junit.Assert.*;

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
	private String date;
	private String projectNameInList;
	
	
	/*@Before
	public void before() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}*/
	
	@Test
	public void loginTest() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.get();
		loginPage.loginAs(login, password);
		assertEquals("log out", loginPage.getLogOutLinkText());
	}

	@Test
	public void enterProjectTest(){
		
		MainPage mainPage = new MainPage(driver);
		mainPage.get();
		ProjectPage projectPage = new ProjectPage(driver);
		projectNameInList = mainPage.getProjectName();
		mainPage.choseFirstProjectLink();
		assertEquals(projectNameInList, projectPage.getProjectName());
	}
	
	@Test 
	public void dateTest(){
		ProjectPage projectPage = new ProjectPage(driver);
		projectPage.get();
		date = projectPage.getBuildHistoryDate(projectPage.getBuildHistoryDateElement()).substring(0, 17);
		projectPage.goToBuildPage();
		BuildPage buildPage = new BuildPage(driver);		
		Assert.assertThat(buildPage.getBuildDate(buildPage.getBuildDateElement()),CoreMatchers.containsString(date));
	}
	
}
