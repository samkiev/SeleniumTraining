package utils;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;

public class AlertHandlingWebDriver implements WrapsDriver, WebDriver, JavascriptExecutor, TakesScreenshot {

	private WebDriver wd;
	
	public AlertHandlingWebDriver(WebDriver wd) {
		this.wd = wd;
	}
	
	private <T> T handleAlert(Function<WebDriver, T> action) {
		try {
			return action.apply(wd);
		}
		catch (UnhandledAlertException e) {
			wd.switchTo().alert().accept();
			return action.apply(wd);
		}
	}
	
	@Override
	public <X> X getScreenshotAs(OutputType<X> target)
			throws WebDriverException {
		return handleAlert(d -> ((TakesScreenshot) wd).getScreenshotAs(target));
	}

	@Override
	public Object executeScript(String script, Object... args) {
		return handleAlert(d -> ((JavascriptExecutor) wd).executeScript(script, args));
	}

	@Override
	public Object executeAsyncScript(String script, Object... args) {
		return handleAlert(d -> ((JavascriptExecutor) wd).executeAsyncScript(script, args));
	}

	@Override
	public void get(String url) {
		handleAlert(d -> {
			d.get(url);
			return null;
		});
	}

	@Override
	public String getCurrentUrl() {
		return handleAlert(WebDriver::getCurrentUrl);
	}

	@Override
	public String getTitle() {
		return handleAlert(WebDriver::getTitle);
	}

	@Override
	public List<WebElement> findElements(By by) {
		return handleAlert(d -> d.findElements(by));
	}

	@Override
	public WebElement findElement(By by) {
		return handleAlert(d -> d.findElement(by));
	}

	@Override
	public String getPageSource() {
		return handleAlert(WebDriver::getPageSource);
	}

	@Override
	public void close() {
		handleAlert(d -> {
			d.close();
			return null;
		});
	}

	@Override
	public void quit() {
		handleAlert(d -> {
			d.quit();
			return null;
		});
	}

	@Override
	public Set<String> getWindowHandles() {
		return handleAlert(WebDriver::getWindowHandles);
	}

	@Override
	public String getWindowHandle() {
		return handleAlert(WebDriver::getWindowHandle);
	}

	@Override
	public TargetLocator switchTo() {
		return handleAlert(WebDriver::switchTo);
	}

	@Override
	public Navigation navigate() {
		return handleAlert(WebDriver::navigate);
	}

	@Override
	public Options manage() {
		return handleAlert(WebDriver::manage);
	}

	@Override
	public WebDriver getWrappedDriver() {
		return wd;
	}
}
