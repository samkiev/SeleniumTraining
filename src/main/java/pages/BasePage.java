package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class BasePage<T extends BasePage<T>> extends LoadableComponent<T> {

	protected final WebDriver driver;
	protected final Logger log = LogManager.getLogger(this);
	
	@FindBy(css = "a.inverse")
	private WebElement userLink;

	@FindBy(css = ".login a[href*='logout']")
	protected WebElement logOutLink;
	
	public BasePage(WebDriver wd) {
		this(wd, false);
	}
		
	protected BasePage(WebDriver wd, boolean checkIfLoaded) {
		this.driver = wd;
		PageFactory.initElements(driver, this);
		if (checkIfLoaded) {
			isLoaded();
		}
	}
	
	static void sendKeys(WebElement el, String text) {
		el.clear();
		el.sendKeys(text);
	}
	
	public String getUserLinkElementText(){
		try {
			return userLink.getText();
		}
		catch (NoSuchElementException e){}
		return null;
		
	}
	
	public boolean isLoggedIn() {
		try {
			return logOutLink.isDisplayed();
		}
		catch (NoSuchElementException e) {}
		return false;
	}
	
	@Override
	protected void load() {
		log.debug("Loading url: {}", getPageUrl());
		driver.get(getPageUrl());
	}
	
	protected abstract String getPageUrl();
}
