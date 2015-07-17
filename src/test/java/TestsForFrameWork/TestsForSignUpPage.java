package TestsForFrameWork;
import org.junit.Assert;
import org.junit.Test;
import pages.CreateAccountResultPage;
import pages.SignUpPage;
import utils.User;

/**
 * Created by tetiana.sviatska on 7/16/2015.
 */
public class TestsForSignUpPage extends BaseTest{

    @Test
     public void loadPageWithNullArgument(){
        try {
            SignUpPage signUpPage = new SignUpPage(null).get();
        }
        catch (NullPointerException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void signUpWithNullArgument(){
        try{
            SignUpPage signUpPage = new SignUpPage(driver).get();
            signUpPage.signUpAs(null);
        }
        catch (NullPointerException e){
            Assert.fail(e.getMessage());
        }
    }
}
