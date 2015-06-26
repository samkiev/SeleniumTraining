package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class BasePage<T extends BasePage<T>> extends LoadableComponent<T> {

	protected String login = "admin";
	protected String password = "admin";
	protected WebDriver driver;
	
	@FindBy(css = ".login a[href*='logout']")
	private WebElement logOutLink;

	public BasePage(WebDriver wd) {
		this.driver = wd;
		PageFactory.initElements(driver, this);
	}
		
	protected boolean isLoggedIn() {
		try {
			return logOutLink.isDisplayed();
		}
		catch (NoSuchElementException e) {}
		return false;
	}
}
