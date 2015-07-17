import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.model.Statement;
import pages.CreateAccountResultPage;
import pages.SignUpPage;
import utils.User;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class SignUpPositiveTest extends BaseTest {

    private final User user = User.generateMockUser();

    @Rule
    public TestRule userAvailabilityRule = (base, description) -> new Statement() {

        @Override
        public void evaluate() throws Throwable {
            try {
                base.evaluate();
            } finally {
                user.delete(true);
            }
        }
    };

    @Test
    public void checkSignUpFunctionality() {
        CreateAccountResultPage resultPage = new SignUpPage(driver).get()
                .signUpAs(user);
        assertTrue(resultPage.isLoggedIn());
        assertNull(resultPage.getError());
    }
}
