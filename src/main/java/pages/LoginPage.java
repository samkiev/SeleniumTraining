package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thoughtworks.selenium.webdriven.commands.GetText;

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
		
		passwordField.clear();
		passwordField.sendKeys(password);
	}
	private void setLogin(String login) {
		
		loginField.clear();
		loginField.sendKeys(login);		
	}

	@Override
	 protected void load() {
		driver.get("http://seltr-kbp1-1.synapse.com:8080/");
	}
	@Override 
	protected void isLoaded() throws Error {
		Assert.assertEquals("Jenkins", driver.getTitle());
	  }
}

