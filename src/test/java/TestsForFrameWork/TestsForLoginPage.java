package TestsForFrameWork;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LoginPage;

/**
 * Created by tetiana.sviatska on 7/15/2015.
 */
public class TestsForLoginPage extends BaseTest{
    WebDriver driver = new FirefoxDriver();

    @Test
    public void LoadPageWithNullArgument(){
        try{
            LoginPage lp = new LoginPage(null).get();
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test(expected = Error.class)
    public void loginWithIncorrectArguments(){
        LoginPage lp = new LoginPage(driver).get();
        lp.loginAs(null, null);
    }
}
