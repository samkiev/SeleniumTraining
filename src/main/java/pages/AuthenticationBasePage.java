package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AuthenticationBasePage<T extends AuthenticationBasePage<T>> extends BasePage<T> {

	public static final String LOGIN = "admin";
	public static final String PASSWORD = "admin";

	@FindBy(css = "a.inverse")
	private WebElement userLink;

	public AuthenticationBasePage(WebDriver wd) {
		super(wd);
	}

	protected AuthenticationBasePage(WebDriver wd, boolean checkIfLoaded) {
		super(wd, checkIfLoaded);
	}

	@Override
	public void load() {
		if (!isLoggedIn()) {
			new LoginPage(driver).get().loginAs(LOGIN, PASSWORD);
		}
		System.out.printf("Loading page: '%s'\n", getPageUrl());
		driver.get(getPageUrl());
	}


	public String getUserLinkElementText(){
		return userLink.getText();
	}

	public void logOut(){
		logOutLink.click();
	}
}
