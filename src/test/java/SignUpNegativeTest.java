
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.CreateAccountResultPage;
import pages.DeletePage;
import pages.PeoplePage;
import pages.SignUpPage;
import rules.SignUpNegativeClassConditionRule;
import rules.SignUpNegativeTestsConditionsRule;
import utils.User;
import utils.WebDriverController;

@RunWith(JUnit4.class)
public class SignUpNegativeTest {
	
	private static WebDriver driver = WebDriverController.getDriver();	
	private static CreateAccountResultPage resultPage;
	private static User user = User.generateMockUser();
	

	@ClassRule
	public static SignUpNegativeClassConditionRule classRule = new SignUpNegativeClassConditionRule(driver, user);
	
	@Rule
	public SignUpNegativeTestsConditionsRule testsRule = new SignUpNegativeTestsConditionsRule(driver, user);
	
	@Test
	public void checkIfAllFieldIsEmpty() {		
		 resultPage = new SignUpPage(driver).get()			
				.signUpAs(User.generateEmptyFieldsUser());		
		assertTrue(!resultPage.isLoggedIn());
		assertNotNull(resultPage.getError());	
	}
	
	@Test
	public void checkIfAllFieldIsSpace(){
		resultPage = new SignUpPage(driver).get()		
				.signUpAs(User.generateSpaceFieldUser());		
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
			WebDriver cleaningDriver = new FirefoxDriver();
			new DeletePage(cleaningDriver, user).get().deleteUser();
			cleaningDriver.quit();
			driver.manage().deleteAllCookies();
		}
	}
}

