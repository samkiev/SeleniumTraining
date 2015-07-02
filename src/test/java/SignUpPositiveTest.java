
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import pages.CreateAccountResultPage;
import pages.DeletePage;
import pages.SignUpPage;
import utils.User;
import utils.WebDriverController;

@RunWith(JUnit4.class)
public class SignUpPositiveTest {

	private static WebDriver driver = WebDriverController.getDriver();
	private User user = new User();


	@AfterClass
	public static void shutDown() {
		driver.quit();
	}	

	@Test
	public void checkSignUpFunctionality() {
		CreateAccountResultPage resultPage = new SignUpPage(driver).get()			
				.signUpAs(user );
		assertTrue(resultPage.isLoggedIn());
		assertNull(resultPage.getError());	
	}
	
	@After
	public void userCleaner(){
		driver.quit();
		driver = WebDriverController.getDriver(true);
		new DeletePage(driver, user).get().deleteUser();
	}
}
