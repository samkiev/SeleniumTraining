package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
	
	@FindBy(xpath = "//*[@id='projectstatus']//tr[2]/td[3]/a")
	WebElement firstProjectLink;
	
	public MainPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void choseFirstProjectLink(){
		
		firstProjectLink.click();	
	}
}
