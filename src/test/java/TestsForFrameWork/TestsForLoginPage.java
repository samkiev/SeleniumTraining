package TestsForFrameWork;

import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;

/**
 * Created by tetiana.sviatska on 7/15/2015.                                                               FIX
 */
public class TestsForLoginPage extends BaseTest {

    @Test(expected = IllegalArgumentException.class)
    public void LoadPageWithNullArgument() {
        try {
            new LoginPage(null).get();
        } catch (NullPointerException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void loginWithIncorrectArguments() {
        LoginPage lp = new LoginPage(driver).get();
        lp.loginAs(null, null);
    }
}
