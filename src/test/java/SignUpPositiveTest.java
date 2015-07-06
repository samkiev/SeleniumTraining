
import static org.junit.Assert.*;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;

import pages.CreateAccountResultPage;
import pages.SignUpPage;
import rules.DefaultRule;
import rules.SingUpPositiveTestsConditionsRule;
import utils.User;

import utils.WebDriverController;

@RunWith(JUnit4.class)
public class SignUpPositiveTest {

	private static WebDriver driver = WebDriverController.getDriver();
	private User user = User.generateMockUser();
	

	@ClassRule
	public static DefaultRule fr = new DefaultRule(driver);
	
	@Rule
	public SingUpPositiveTestsConditionsRule signUpPageRule = new SingUpPositiveTestsConditionsRule(driver, user);
		
	@Test
	public void checkSignUpFunctionality() {
		CreateAccountResultPage resultPage = new SignUpPage(driver).get()			
				.signUpAs(user);
		System.out.println(12/0);
		assertTrue(resultPage.isLoggedIn());
		assertNull(resultPage.getError());	
	}
}
