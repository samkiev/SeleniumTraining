package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectPage extends BasePage {

	@FindBy(css = "div.build-details > a")
	WebElement buildHistoryDateElement;
	String date;
		
	public ProjectPage(WebDriver driver){
		this.driver = driver;
	}
	
	public WebElement getBuildHistoryDateElement() {		
		return  buildHistoryDateElement;
	}
	
	public String getBuildHistoryDate(WebElement element){
		date = BasePage.getDateOfElement(element);
		return  date;
	}
}

