package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverController {

	public static final String CHROME = "chrome";
	public static final String FIREFOX = "firefox";
	public static final String IE = "ie";
	
	
	
	private static WebDriver wd = null;
	
	public static WebDriver getDriver() {
		if (wd == null) {
			String browserName = System.getProperty("browser", "firefox").toLowerCase();
			switch(browserName) {
			case CHROME:
				System.setProperty("webdriver.chrome.driver", String.format("%s/drivers/chromedriver.exe", System.getProperty("user.dir")));
				wd = new ChromeDriver(ProfileWebDriver.getChromeProfile());
				break;
			case FIREFOX:
				wd = new FirefoxDriver(ProfileWebDriver.getFirefoxProfile());
				break;
			case IE:
				System.setProperty("webdriver.ie.driver", String.format("%s/drivers/IEDriverServer.exe", System.getProperty("user.dir")));
				wd = new InternetExplorerDriver();
				break;
			default:
				throw new IllegalStateException(String.format("Browser: '%s' is not supported!", browserName));
			}
		}
		wd.manage().window().maximize();
		return wd;
	}
}
