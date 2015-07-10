import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pages.MainPage;
import pages.UserPage;

@RunWith(JUnit4.class)
public class SearchTest extends BaseTest {
	
	private String userName = "special_user_name";

	@Test
	public void checkSearchFunctionality() {
		UserPage userPage = new MainPage(driver).get()
				.searchUser("ia", userName );			
		assertEquals(userPage.getUserPageMainContext(), userName);
	}
	
}