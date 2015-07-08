import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AssumptionViolatedException;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.gargoylesoftware.htmlunit.javascript.configuration.BrowserName;
import com.google.common.io.Files;

import utils.WebDriverController;


public class BaseTest {

	protected static WebDriver driver = null;
	
	protected final Logger log = LogManager.getLogger(this);
	
	@ClassRule
	public static TestRule rule = (base, description) -> new Statement() {
		@Override
		public void evaluate() throws Throwable {
			driver = WebDriverController.getDriver();
			try {
				base.evaluate();
			} finally {
				driver.quit();
			}
		}
	};
	
	@Rule
	public TestWatcher watcher = new TestWatcher() {

		@Override
		protected void succeeded(Description d) {
			log.info("Test: {} - PASSED\n", d.getMethodName());
		}

		@Override
		protected void failed(Throwable e, Description d) {
			log.info("Test: {} - FAILED. Reason: {}\n", d.getMethodName(), e.getMessage());
				String browseName = System.getProperty("browser").toLowerCase();
                File dir = new File("reports/screenshots/" + browseName);
                dir.mkdirs();              
                File outputFile = new File(dir, d.getMethodName() + ".png");
                File originalFile = (((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));              
                try {
					Files.copy(originalFile, outputFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}

		@Override
		protected void skipped(AssumptionViolatedException e, Description d) {
			log.info("Test: {} - SKIPPED. Because of: {}\n", d.getMethodName(), e.getMessage());
		}

		@Override
		protected void starting(Description d) {
			log.info("Starting test: {}", d.getMethodName());
		}
		
	};
}
