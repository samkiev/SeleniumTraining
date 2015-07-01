package pages;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage<LoginPage> {

	@FindBy (id ="j_username")
	private WebElement loginField;
	
	@FindBy (name ="j_password")
	private WebElement passwordField;
	
	@FindBy (id ="yui-gen1-button")
	private WebElement submitButton;		
	
	public LoginPage(WebDriver driver){		
		super(driver);
	}
	
	public MainPage loginAs(String login, String password){	
		setLogin(login);
		setPassword(password);
		submit();
		return new MainPage(driver);
	}
	private void submit() {		
		submitButton.click();
	}
	private void setPassword(String password) {			
		passwordField.sendKeys(password);
	}
	private void setLogin(String login) {		
		loginField.sendKeys(login);		
	}

	@Override
	protected String getPageUrl() {
		return "http://seltr-kbp1-1.synapse.com:8080/login";
	}
	
	@Override 
	protected void isLoaded() throws Error {
		try {
			Assert.assertTrue(passwordField.isDisplayed());
			Assert.assertThat(driver.getCurrentUrl(), Matchers.startsWith(getPageUrl()));
		}
		catch (NoSuchElementException e) {
			Assert.fail("Login page is not loaded");
		}
	}
}

