package rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.DeletePage;
import utils.User;


public class SingUpPositiveTestsConditionsRule implements TestRule {
	private WebDriver driver;
	private User user;
		
	public SingUpPositiveTestsConditionsRule(WebDriver driver, User user) {
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
               
                System.out.println("After Test Precondition");
                userCleaner();
            }
        };
    }
    
    public void userCleaner(){		
		driver = new FirefoxDriver();
		new DeletePage(driver, user).get().deleteUser();
		driver.quit();
	}
}