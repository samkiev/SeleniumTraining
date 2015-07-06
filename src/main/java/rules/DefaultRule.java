package rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;


public class DefaultRule implements TestRule {
	private WebDriver driver;
	
	public DefaultRule(WebDriver driver) {
		this.driver = driver;
	}
	
    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println("Start TestClass");
            
                try {
                	 base.evaluate();
				} catch (Exception e) {	}
               
                System.out.println("Tear down");
                teardown();
            }
        };
    }
    
    public void teardown(){				
		driver.quit();
	}
}