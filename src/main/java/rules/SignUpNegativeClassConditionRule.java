
package rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.DeletePage;
import pages.PeoplePage;
import pages.SignUpPage;
import utils.User;


public class SignUpNegativeClassConditionRule implements TestRule {
	private WebDriver driver;
	private User user;
	
	public SignUpNegativeClassConditionRule(WebDriver driver, User user) {
		this.driver = driver;
		this.user = user;
	}

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println("Start Sign Up negative Tests");
                createTemporaryUser();
                try {
                	base.evaluate();                	
				} catch (Exception e) {}
                deleteTemporaryUser();
                driver.quit();              
            }
        };
    }    
    
    private void createTemporaryUser() {
    	new SignUpPage(driver).get()			
    	.signUpAs(user);		
    	driver.manage().deleteAllCookies();
    }
    
    public void deleteTemporaryUser() {    	
		driver.quit();
		driver = new FirefoxDriver();
		if (new PeoplePage(driver).get().isUserInTheList(user)){
			new DeletePage(driver, user).get().deleteUser();
		}
	}   
}