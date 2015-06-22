package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

	@FindBy (id ="j_password")
	WebElement loginField;
	
	@FindBy (id ="j_username")
	WebElement passwordField;
	
	@FindBy (id ="yui-gen1-button")
	WebElement submitButton;
	
	public LoginPage(WebDriver driver){		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void loginAs(String login, String password){
		
		setLogin(login);
		setPassword(password);
		submit();
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

}

