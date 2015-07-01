package pages;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class CreateAccountResultPage extends BasePage<CreateAccountResultPage> {

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
}
