package rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.DeletePage;
import utils.User;

public class SingUpRule implements TestRule {
	private WebDriver driver;
	private User user;
	
	public SingUpRule(WebDriver driver, User user) {
		this.driver = driver;
		this.user = user;
	}

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println("before");
                base.evaluate();
                System.out.println("after");
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