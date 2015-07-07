package pages;

import org.openqa.selenium.WebDriver;
import utils.User;


public abstract class AuthenticationBasePage<T extends AuthenticationBasePage<T>> extends BasePage<T> {
	
	public AuthenticationBasePage(WebDriver wd) {
		super(wd);
	}

	protected AuthenticationBasePage(WebDriver wd, boolean checkIfLoaded) {
		super(wd, checkIfLoaded);
	}

	@Override
	public void load() {
		if (!isLoggedIn()) {
			User user = User.setLoginAndPassword("admin", "admin");
			new LoginPage(driver).get().loginAs(user.getLogin(), user.getPassword());
		}
		log.debug("Loading url: {}", getPageUrl());
		driver.get(getPageUrl());
	}
}