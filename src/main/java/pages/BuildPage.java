package pages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BuildPage extends BasePage{
	
	String date;
	
	@FindBy(className = "build-caption")
	private WebElement buildDateElement;
	
	public BuildPage(WebDriver driver){		
		super(driver);
	}
	
	public WebElement getBuildDateElement(){
		return buildDateElement;
	}
	
	public String getBuildPageDate() {	
		String messageOfBuildPage = getBuildDateElement().getText();
		String dateOfBuildPage = messageOfBuildPage.substring(messageOfBuildPage.indexOf("(") + 1, messageOfBuildPage.indexOf(")"));
		LocalDateTime formatDateOFBuildPage = LocalDateTime.parse(dateOfBuildPage, DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a"));				
		return 
				formatDateOFBuildPage.format(DateTimeFormatter.ofPattern("mm-dd-yyyy hh:mm a"));
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
	}
}
