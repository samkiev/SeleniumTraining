package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.User;

public abstract class AuthenticationBasePage<T extends AuthenticationBasePage<T>> extends BasePage<T> {

	public static final User ADMIN = new User("admin","admin");
	public static final String LOGIN = "admin";
	public static final String PASSWORD = "admin";

	
	public AuthenticationBasePage(WebDriver wd) {
		super(wd);
	}

	protected AuthenticationBasePage(WebDriver wd, boolean checkIfLoaded) {
		super(wd, checkIfLoaded);
	}

	@Override
	public void load() {
		if (!isLoggedIn()) {
			new LoginPage(driver).get().loginAs(ADMIN);
		}
		driver.get(getPageUrl());
	}	

	public void logOut(){
		logOutLink.click();
	}
}
