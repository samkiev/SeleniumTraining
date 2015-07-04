
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
import rules.SingUpRule;
import utils.User;
import utils.WebDriverController;

@RunWith(JUnit4.class)
public class SignUpPositiveTest {

	private static WebDriver driver = WebDriverController.getDriver();
	private User user = new User();
	

	@ClassRule
	public static DefaultRule fr = new DefaultRule(driver);
	
	@Rule
	public SingUpRule signUpPageRule = new SingUpRule(driver, user);
		
	@Test
	public void checkSignUpFunctionality() {
		CreateAccountResultPage resultPage = new SignUpPage(driver).get()			
				.signUpAs(user);
		assertTrue(resultPage.isLoggedIn());
		assertNull(resultPage.getError());	
	}
}
