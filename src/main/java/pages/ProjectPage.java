package pages;

import java.time.LocalDateTime;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LocaleDateExtractor;

public class ProjectPage extends AuthenticationBasePage<ProjectPage> {
		
	@FindBy(css = "div.build-details > a")
	private WebElement buildHistoryDateElement;
	
	@FindBy(xpath = ".//*[@id='breadcrumbs']/li[3]/a")
	private WebElement projectNameElement;
	
	@FindBy(id = "yui-gen1-button")
	private WebElement disableButton;
				
	public ProjectPage(WebDriver driver){
		super(driver);
	}
	
	public LocalDateTime getBuildHistoryDate(){			
		return LocaleDateExtractor.getBuildHistoryCorrectDate(buildHistoryDateElement.getText());					
	}
	
	public BuildPage goToBuildPage(){		
		buildHistoryDateElement.click();
		return new BuildPage(driver);
	}
	public String getProjectName(){
		return projectNameElement.getText();
	}
	
	public boolean isOnProjectPage(){
		try {
			return disableButton.isDisplayed();
		}
		catch (NoSuchElementException e) {}
		return false;
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue(isLoggedIn());		 
		Assert.assertTrue(isOnProjectPage());	
	}

	@Override
	protected String getPageUrl() {
		return "http://seltr-kbp1-1.synapse.com:8080/job/" + new MainPage(driver).getProjectName();
	}
}

