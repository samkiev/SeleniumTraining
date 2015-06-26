package pages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BuildPage extends BasePage<BuildPage> {
		
	@FindBy(className = "build-caption")
	private WebElement buildDateElement;
	
	@FindBy(xpath = "a//a")
	private WebElement buildPageUniqueElement;
	
	public BuildPage(WebDriver driver){		
		super(driver);
	}
	
	public String getBuildPageDate() {	
		
		String messageOfBuildPage = buildDateElement.getText();
		String dateOfBuildPage = messageOfBuildPage.substring(messageOfBuildPage.indexOf("(") + 1, messageOfBuildPage.indexOf(")"));
		LocalDateTime formatDateOFBuildPage = LocalDateTime.parse(dateOfBuildPage, DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a"));				
		return 	formatDateOFBuildPage.format(DateTimeFormatter.ofPattern("mm-dd-yyyy hh:mm a"));
	}
	
	public boolean isOnBuildPage(){
		try{			
			return buildPageUniqueElement.isDisplayed();
		}
		catch (NoSuchElementException e) {}
		return false;
	}
	
	@Override
	protected void load() {
		if (!isLoggedIn()) {
			 new LoginPage(driver).get().loginAs(login, password);			 
		}
		driver.get("http://seltr-kbp1-1.synapse.com:8080/job/" + new MainPage(driver).getProjectName());
		
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue(isLoggedIn());
		Assert.assertTrue(isOnBuildPage());
	}
}
