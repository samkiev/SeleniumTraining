package rules;

import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.DeletePage;
import pages.SignUpPage;
import utils.User;


public class SignUpNegativeTestsConditionsRule implements TestRule {
	private WebDriver driver;
	private User user;

	
	public SignUpNegativeTestsConditionsRule(WebDriver driver, User user) {
		
		this.driver = driver;
		this.user = user;
	}

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
               
                try {
                	base.evaluate();
                	
				} catch (Exception e) {}
            	cleanerTests();                          
            }
        };
    }    
    
    private void cleanerTests() {
    	if (new SignUpPage(driver).get().isLoggedIn()) {
			WebDriver cleaningDriver = new FirefoxDriver();
			new DeletePage(cleaningDriver, user).get().deleteUser();
			cleaningDriver.quit();
			driver.manage().deleteAllCookies();
		}
    	}    
}