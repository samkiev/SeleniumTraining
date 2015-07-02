package pages;

import java.time.LocalDateTime;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LocaleDateExtractor;

public class BuildPage extends AuthenticationBasePage<BuildPage> {
		
	@FindBy(className = "build-caption")
	private WebElement buildDateElement;
	
	@FindBy(xpath = "a//a")
	private WebElement buildPageUniqueElement;
		
	public BuildPage(WebDriver driver){		
		super(driver);
	}
	
	public LocalDateTime getBuildPageDate() {						
		return 	LocaleDateExtractor.getBuildPageCorrectDate(getBuildPageDateText());
	}

	private String getBuildPageDateText() {
		String messageOfBuildPage = buildDateElement.getText();	
		return  messageOfBuildPage.substring(messageOfBuildPage.indexOf("(") + 1, messageOfBuildPage.indexOf(")"));
	}
	
	public boolean isOnBuildPage(){
		try{			
			return buildPageUniqueElement.isDisplayed();
		}
		catch (NoSuchElementException e) {}
		return false;
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue(isLoggedIn());
		Assert.assertTrue(isOnBuildPage());
	}

	@Override
	protected String getPageUrl() {
		return "http://seltr-kbp1-1.synapse.com:8080/job/" + new MainPage(driver).getProjectName();
	}
}
