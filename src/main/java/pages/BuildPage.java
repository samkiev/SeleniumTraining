package pages;

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
	
	public String getBuildDate(WebElement element) {		
		return getDateOfElement(element);
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
