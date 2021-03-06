import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.model.Statement;
import pages.MainPage;
import pages.UserPage;
import utils.User;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class SearchTest extends BaseUITest {

    private static User user;

    @ClassRule
    public static TestRule userAvailabilityRule = (base, d) -> new Statement() {

        @Override
        public void evaluate() throws Throwable {
            boolean isSwitched = false;
            if (!isPossibleToSignUp()){
                isSwitched = true;
                switchAbilityUsersToSignUp();
            }
            user = User.generateMockUser().register();
            try {
                base.evaluate();
            }
            finally {
                user.delete();
                if (isSwitched){
                    switchAbilityUsersToSignUp();
            }
            }
        }
    };

    @Test
    public void checkSearchFunctionality() {
        UserPage userPage = new MainPage(driver).get()
                .searchUser(User.getRandomSubstringOf(user.getName()), user.getName());
        assertEquals(userPage.getUserPageMainContext(), user.getName());
    }

}