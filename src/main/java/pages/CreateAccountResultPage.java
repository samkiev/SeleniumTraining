package pages;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateAccountResultPage extends BasePage<CreateAccountResultPage> {

	@FindBy(className = "error")
	private WebElement error;
	
	protected CreateAccountResultPage(WebDriver wd, boolean checkIfLoaded) {
		super(wd, checkIfLoaded);
	}

	@Override
	protected String getPageUrl() {
		return "http://seltr-kbp1-1.synapse.com:8080/securityRealm/createAccount";
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertThat(driver.getCurrentUrl(), Matchers.equalToIgnoringCase(getPageUrl()));
	}
	
	public String getError() {
		try {
			return error.getText();
		}
		catch (NoSuchElementException e) {}
		System.out.println("Sign up is Fail");
		return null;
	}
}
