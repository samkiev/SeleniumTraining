package pages;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AuthenticationBasePage<MainPage> {
	
	@FindBy(xpath = "//*[@id='projectstatus']//tr[2]/td[3]/a")
	private WebElement firstProjectLink;
	
	@FindBy (xpath =".//a[2]/b")
	private WebElement logOutLinkElement;	
	
	@FindBy (id = "description-link")
	private WebElement descriptionLinkElement;
	
	@FindBy (css = "a[href*='People']")
	WebElement usersListLink;
	
	public MainPage(WebDriver driver){
		super(driver);
	}
	
	public ProjectPage choseFirstProjectLink(){		
		firstProjectLink.click();
		return new ProjectPage(driver);
	}
	public UsersListPage gotoUsersListPage(){
		usersListLink.click();
		return new UsersListPage(driver);		
	}
	public String getProjectName(){
		return firstProjectLink.getText();
	}
	
	public boolean isLogOutLinkDisplayed() {
		return logOutLinkElement.isDisplayed();
	}	
	
	@Override
	protected void isLoaded() throws Error {
		try {
			Assert.assertTrue(descriptionLinkElement.isDisplayed());
		}
		catch (NoSuchElementException e) {
			Assert.fail("Main page is not loaded");
		}
	}

	@Override
	protected String getPageUrl() {
		return "http://seltr-kbp1-1.synapse.com:8080/";
	}
}
