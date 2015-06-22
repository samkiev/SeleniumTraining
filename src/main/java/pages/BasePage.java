package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

	protected WebDriver driver;

	protected static String getDateOfElement(WebElement element) {
		
		return element.getText();	
	}

}
