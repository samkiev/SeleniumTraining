
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;

import pages.CreateAccountResultPage;
import pages.SignUpPage;
import rules.SingUpRule;
import utils.User;
import utils.WebDriverController;

@RunWith(JUnit4.class)
public class SignUpPositiveTest {

	private static WebDriver driver = WebDriverController.getDriver();
	private User user = new User();
	

	//@ClassRule
	
	
	@Rule
	public SingUpRule rule = new SingUpRule(driver, user);
	
	@AfterClass
	public static void shutDown() {
		System.out.println("close driver");
		driver.quit();
	}	

	@Test
	public void checkSignUpFunctionality() {
		CreateAccountResultPage resultPage = new SignUpPage(driver).get()			
				.signUpAs(user);
		assertTrue(resultPage.isLoggedIn());
		assertNull(resultPage.getError());	
	}
}
