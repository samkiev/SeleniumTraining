package TestsForFrameWork;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import pages.LoginPage;
import utils.WebDriverController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by tetiana.sviatska on 7/15/2015.
 */
public class BaseTest {
    protected static WebDriver driver;

    @ClassRule
    public static TestRule setUpDriverAndCleanUpUsersRule = (base, d) -> new Statement() {
        @Override
        public void evaluate() throws Throwable {
            driver = new FirefoxDriver();
            try {
                base.evaluate();
            } finally {
                driver.quit();
            }
        }
    };

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    protected @interface ChangeViewConfig {
        String value() default "";
    }


    @Rule
    public TestRule changingViewConfigRule = (Statement base, Description d) -> new Statement() {
        @Override
        public void evaluate() throws Throwable {
            ChangeViewConfig config = d.getAnnotation(ChangeViewConfig.class);
            if (config != null) {
                new LoginPage(driver).get().loginAs("admin", "admin");
                defaultViewConfig(config.value());
            }
            try {
                base.evaluate();
            }
            finally {
                if (config != null) {
                    defaultViewConfig("All");
                }
            }
        }
    };

    public void defaultViewConfig(String view){
        try {
            driver.get("http://seltr-kbp1-1.synapse.com:8080/configure");
            WebElement config = driver.findElement(By.cssSelector("select[name=\"primaryView\"]"));
            WebElement save = driver.findElement(By.id("yui-gen33-button"));
            Select select = new Select(config);
            select.selectByValue(view);
            save.click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
}
