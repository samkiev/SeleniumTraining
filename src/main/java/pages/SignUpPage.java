package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage<SignUpPage> {
	
	@FindBy(id = "username")
	private WebElement usernameField;
	
	@FindBy(name = "password1")
	private WebElement passwordField;	

	@FindBy(name = "password2")
	private WebElement confirmPasswordField;
	
	@FindBy(name = "fullname")
	private WebElement fullNameField;
	
	@FindBy(name = "email")
	private WebElement emailField;
	
	@FindBy(id = "yui-gen1-button")
	private WebElement signUpButton;	
	
	public SignUpPage(WebDriver driver){
		super(driver);
	}
	
	public CreateAccountResultPage signUpAs(String login, String password, String name, String email){
		usernameField.sendKeys(login);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(password);
		fullNameField.sendKeys(name);
		emailField.sendKeys(email);
		signUpButton.click();	
		
		return new CreateAccountResultPage(driver, true);
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue(!isLoggedIn());		 
		Assert.assertEquals("Sign up [Jenkins]", driver.getTitle());
	}

	@Override
	protected String getPageUrl() {
		return "http://seltr-kbp1-1.synapse.com:8080/signup";
	}
}
