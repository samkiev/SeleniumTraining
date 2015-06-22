package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BuildPage extends BasePage{
	
	String date;
	
	@FindBy(className = "build-caption")
	WebElement buildDateElement;
	
	public BuildPage(WebDriver driver){		
		this.driver = driver;
	}
	public void goToBuildPage(WebElement element){
		
		ProjectPage projectPage = new ProjectPage(driver);		
		projectPage.getBuildHistoryDateElement().click();
	}
	public WebElement getBuildDateElement(){
		return buildDateElement;
	}
	
	public String getBuildDate(WebElement element) {
		
		date = BasePage.getDateOfElement(element).substring(18, 137);
		return date;
	}
}
