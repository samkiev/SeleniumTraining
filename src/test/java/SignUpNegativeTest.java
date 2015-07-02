
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.CreateAccountResultPage;
import pages.DeletePage;
import pages.PeoplePage;
import pages.SignUpPage;
import utils.User;
import utils.WebDriverController;

@RunWith(JUnit4.class)
public class SignUpNegativeTest {
	
	private static WebDriver driver = WebDriverController.getDriver();	
	private static CreateAccountResultPage resultPage;
	private static User user = new User();
	
	
	@BeforeClass
	public static void before(){
		resultPage = new SignUpPage(driver).get()			
				.signUpAs(user);		
		driver.manage().deleteAllCookies();
		}
	
	@AfterClass
	public static void shutDown() {
		driver.quit();
		driver = WebDriverController.getDriver(true);		
		if (new PeoplePage(driver).get().isUserInTheList(user)){
			new DeletePage(driver, user).get().deleteUser();
		}
		driver.quit();
		
	}		
	@Test
	public void checkIfAllFieldIsEmpty() {		
		 resultPage = new SignUpPage(driver).get()			
				.signUpAs(new User(null, null, null, null));		
		assertTrue(!resultPage.isLoggedIn());
		assertNotNull(resultPage.getError());	
	}
	
	@Test
	public void checkIfAllFieldIsSpace(){
		resultPage = new SignUpPage(driver).get()		
				.signUpAs(new User("   ", "   ", "   ", "   "));		
		assertTrue(!resultPage.isLoggedIn());
		assertNotNull(resultPage.getError());	
	}
	@Test
	public void checkSignUpFunctionality() {
		resultPage = new SignUpPage(driver).get()			
				.signUpAs(user);
		assertTrue(!resultPage.isLoggedIn());
		assertNotNull(resultPage.getError());	
	}
	
	@After
	public void testCleaner(){
		if (resultPage.isLoggedIn()) {
			String getUserLogin = resultPage.getUserLinkElementText();			
			WebDriver cleaningDriver = new FirefoxDriver();
			new DeletePage(cleaningDriver, new User(getUserLogin)).get().deleteUser();
			cleaningDriver.quit();
			driver.manage().deleteAllCookies();
		}
		System.out.println("User is not Logged in");		
	}
}

