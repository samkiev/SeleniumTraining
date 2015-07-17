package TestsForFrameWork;

import org.junit.Assert;
import org.junit.Test;
import pages.SignUpPage;

/**
 * Created by tetiana.sviatska on 7/16/2015.                                    FIX
 */
public class TestsForSignUpPage extends BaseTest {

    @Test(expected = IllegalArgumentException.class)
    public void loadPageWithNullArgument() {
        try {
            SignUpPage signUpPage = new SignUpPage(null).get();
        } catch (NullPointerException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void signUpWithNullArgument() {
        try {
            SignUpPage signUpPage = new SignUpPage(driver).get();
            signUpPage.signUpAs(null);
        } catch (NullPointerException e) {
            Assert.fail(e.getMessage());
        }
    }
}
