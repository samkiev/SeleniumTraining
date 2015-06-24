package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class BasePage<T extends BasePage<T>> extends LoadableComponent<T> {

	@FindBy(css = ".login a[href*='logout']")
	private WebElement logOutLink;
	protected String login = "admin";
	protected String password = "admin";
	protected WebDriver driver;

	public BasePage(WebDriver wd) {
		this.driver = wd;
		PageFactory.initElements(driver, this);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	protected String getDateOfElement(WebElement element) {
		return element.getText();	
	}

	protected boolean isLoggedIn() {
		try {
			return logOutLink.isDisplayed();
		}
		catch (NoSuchElementException e) {}
		return false;
	}
}
