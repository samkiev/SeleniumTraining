package pages;

import java.util.NoSuchElementException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.User;

public class PeoplePage extends AuthenticationBasePage<PeoplePage> {

	private String peoplePageUrl = "http://seltr-kbp1-1.synapse.com:8080/asynchPeople/";
	
	public PeoplePage(WebDriver driver){
		super(driver);
	}
	
	public boolean isUserInTheList(User user) {
		try {
			if (driver.findElement(By.linkText(user.getLogin())).isDisplayed()){
			System.out.println(user.getLogin() + " is found in the People page");
			return true;
		}
				
		} catch (NoSuchElementException e) {}
			Assert.fail("User is abcent in the List");
			return false;	
	}	
	
	private boolean isOnPeoplePage() {		
		return (driver.getCurrentUrl().equals(peoplePageUrl));
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue(isLoggedIn());
		Assert.assertTrue(isOnPeoplePage());
	}	

	@Override
	protected String getPageUrl() {		
		return peoplePageUrl;
	}

}
