package pages;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage<MainPage> {
	
	@FindBy(xpath = "//*[@id='projectstatus']//tr[2]/td[3]/a")
	private WebElement firstProjectLink;
	
	@FindBy (xpath =".//a[2]/b")
	private WebElement logOutLinkElement;	
	
	public MainPage(WebDriver driver){
		super(driver);
	}
	
	public ProjectPage choseFirstProjectLink(){		
		firstProjectLink.click();
		return new ProjectPage(driver);
	}
	public String getProjectName(){
		return firstProjectLink.getText();
	}
	public String getLogOutLinkText(){
		return logOutLinkElement.getText();
	}
	public WebElement getLogOutLinkElement() {
		return logOutLinkElement;
	}
	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue(isLoggedIn());		 
		Assert.assertEquals("Dashboard [Jenkins]", driver.getTitle());
	}

	@Override
	protected void load() {
		if (!isLoggedIn()) {
			new LoginPage(driver).get().loginAs(login, password);
		}
		driver.get("http://seltr-kbp1-1.synapse.com:8080/");
	}
}
