package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserPage extends AuthenticationBasePage<UserPage> {
	
	private final String userName;
	
	@FindBy(css = "a[href*='delete']")
	private WebElement deleateLinkElement;
			
	public UserPage(WebDriver driver, String userName){
		this(driver, false, userName);
	}
	
	protected UserPage(WebDriver driver, boolean checkIfLoaded, String userName){
		super(driver, checkIfLoaded);
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}

	@Override
	protected void isLoaded() throws Error {
		try {
			Assert.assertTrue(driver.findElement(By.cssSelector("#main-panel-content h1 img[src*='user.png']")).isDisplayed());
		}
		catch (NoSuchElementException e) {
			Assert.fail(String.format("User page is not loaded. Url: %s", driver.getCurrentUrl()));
		}
		
	}

	@Override
	protected String getPageUrl() {
		return String.format("http://seltr-kbp1-1.synapse.com:8080/user/%s/", getUserName());
	}
}
