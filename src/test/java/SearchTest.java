import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.model.Statement;
import pages.MainPage;
import pages.UserPage;
import utils.User;

@RunWith(JUnit4.class)
public class SearchTest extends BaseTest {
	
	private User user = null;	

	@Rule
	public TestRule userAvailabilityRule = (base, d) -> new Statement() {

		@Override
		public void evaluate() throws Throwable {
			user = User.generateMockUser().register();
			try {
				base.evaluate();
			} finally {
				user.delete();
			}
		}
	};

	@Test
	public void checkSearchFunctionality() {
		UserPage userPage = new MainPage(driver).get()
				.searchUserWith(user.getName());			
		assertEquals(userPage.getUserPageMainContext(), user.getName());
	}
}
