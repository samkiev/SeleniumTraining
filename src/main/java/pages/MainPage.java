package pages;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class MainPage extends AuthenticationBasePage<MainPage> {
	
	@FindBy(xpath = "//*[@id='projectstatus']//tr[2]/td[3]/a")
	private WebElement firstProjectLink;
	
	@FindBy (id = "description-link")
	private WebElement addDescriptionLinkElement;
	
	@FindBy (id = "search-box")
	private WebElement searchBoxField;
	
	@FindBy (id = "search-box-completion")
	WebElement expectedSearchElement;
	
	@FindBy (css = ".yui-ac-bd>ul>li")
	List<WebElement> listOfDropDownNames;
	
	private String randomName = null;
	
	private Actions action = new Actions(driver);				

	
	public MainPage(WebDriver driver) {
		super(driver);
	}
	
	public MainPage(WebDriver driver, boolean checkIfLoaded) {
		super(driver, checkIfLoaded);
	}
	
	public ProjectPage choseFirstProjectLink() {
		log.info("Opening project: {}", getProjectName());
		firstProjectLink.click();
		return new ProjectPage(driver);
	}
	
	public String getProjectName(){
		return firstProjectLink.getText();
	}
	
	public UserPage searchUser(String token, String expectedUser){		
		searchBoxField.sendKeys(token);
		selectExpectedUser(expectedUser);
		return new UserPage(driver, false, expectedUser);
	}
	
	public UserPage searchAnyUser(String token){
		searchBoxField.sendKeys(token);
		selectAnyUser();
		return new UserPage(driver, false, randomName);
	}
	
	private void selectAnyUser() {
		waitForDropDownElement();
		WebElement randomUserName = listOfDropDownNames.get((int) (Math.random()*listOfDropDownNames.size()-1));
		randomName = randomUserName.getText();
		selectDesiredItem(randomUserName);
	}

	private void selectExpectedUser(String userName) {
		waitForDropDownElement();
		WebElement expectedUserName = null;		
			for (WebElement liName : listOfDropDownNames) {
				if (liName.getText().equals(userName)) {
					expectedUserName = liName;
				}
			}			
		selectDesiredItem(expectedUserName);
	}
	
	private void waitForDropDownElement() {
		WebDriverWait wait = new WebDriverWait(driver, 2);		
		wait.until(ExpectedConditions.visibilityOf(expectedSearchElement));
	}
	
	private void selectDesiredItem(WebElement randomUserName) {
		action.moveToElement(randomUserName).click()
		.sendKeys(searchBoxField, Keys.ENTER)
		.build()
		.perform();
	}
	
	@Override
	protected void isLoaded() throws Error {
		try {
			Assert.assertTrue(addDescriptionLinkElement.isDisplayed());
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
