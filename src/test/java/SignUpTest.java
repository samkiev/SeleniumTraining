
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;

import pages.CreateAccountResultPage;
import pages.DeletePage;
import pages.MainPage;
import pages.SignUpPage;
import pages.UserPage;
import utils.WebDriverController;

@RunWith(JUnit4.class)
public class SignUpTest {

	private static WebDriver driver = WebDriverController.getDriver();
	private String adminLogin = "admin";
	private String adminPassword = "admin";
	private String userLogin = "userlogintest1";
	private String userPassword = "usernametest1";
	private String userName = "usernametest1";
	private String userEmail = "usernametest1@user.com";


	@AfterClass
	public static void shutDown() {
		driver.quit();
	}	

	@Test
	public void checkSignUpFunctionality() {
		CreateAccountResultPage resultPage = new SignUpPage(driver).get()			
				.signUpAs(userLogin, userPassword, userName, userEmail);
		assertTrue(resultPage.isLoggedIn());
		assertEquals(new MainPage(driver).getUserLinkElementText(), userName);	
	}
	
	@After
	public void userCleaner(){
		driver.manage().deleteAllCookies();
		driver.get("http://google.com");
		driver.navigate().refresh();
		System.out.println("cleaning process started");
		new DeletePage(driver, userLogin).get().deleteUser();
	}
}
