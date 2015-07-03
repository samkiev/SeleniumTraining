package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.User;

public abstract class AuthenticationBasePage<T extends AuthenticationBasePage<T>> extends BasePage<T> {

	public static final User ADMIN = new User("admin","admin");
	
	public AuthenticationBasePage(WebDriver wd) {
		super(wd);
	}

	protected AuthenticationBasePage(WebDriver wd, boolean checkIfLoaded) {
		super(wd, checkIfLoaded);
	}

	@Override
	public void load() {
		if (!isLoggedIn()) {
			System.out.println("Login as " + ADMIN.getLogin().toUpperCase());
			new LoginPage(driver).get().loginAs(ADMIN);
		}
		driver.get(getPageUrl());
	}	

	public void logOut(){
		System.out.println("Log Out");
		logOutLink.click();
	}
}
