package pages;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class MainPage extends AuthenticationBasePage<MainPage> {

    WebElement projectLinkElement;

    @FindBy(css = ".yui-ac-bd>ul>li")
    List<WebElement> listOfDropDownNames;

    @FindBy(css = "#projectstatus a.model-link:not([href*=\"Build\"]")
    List<WebElement> listOfProjectLink;

    @FindBy(id = "description-link")
    private WebElement addDescriptionLinkElement;

    @FindBy(id = "search-box")
    private WebElement searchBoxField;

    @FindBy(id = "yui-ac-content")
    private WebElement expectedSearchElement;

    private Actions action = new Actions(driver);

    public MainPage(@NotNull WebDriver driver) {
        super(driver);
    }

    public MainPage(@NotNull WebDriver driver, @NotNull boolean checkIfLoaded) {
        super(driver, checkIfLoaded);
    }

    public ProjectPage choseProjectLink(@NotNull WebElement element) {
        try{
            log.info("Opening project: {}", element.getText());
            element.click();
        return new ProjectPage(driver, element.getText());
        }
            catch (NoSuchElementException e){}
        WebElement randomElement = getRandomProject();
        randomElement.click();
        return new ProjectPage(driver, randomElement.getText());
    }

    public WebElement getFirstProject() {
        try{
        return listOfProjectLink.get(0);
    }
        catch (NoSuchElementException e){
            System.out.println("Project list is empty");
            throw new RuntimeException(e);
        }
    }

    public WebElement getRandomProject() {
        try{
            projectLinkElement = listOfProjectLink.get(new Random().nextInt(listOfProjectLink.size()));
            return projectLinkElement;
        }
        catch (NoSuchElementException e){
            System.out.println("Project list is empty");
            throw new RuntimeException(e);
        }
    }

    public UserPage searchUser(String token, String expectedUser) {
        try {

            searchBoxField.sendKeys(token);
            selectExpectedUser(expectedUser);

        } catch (org.openqa.selenium.TimeoutException e) {
        }
        return new UserPage(driver, false, expectedUser);
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
        WebDriverWait wait = new WebDriverWait(driver, 5);
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
        } catch (NoSuchElementException e) {
                Assert.fail("Main page is not loaded");
        }
    }

    @Override
    protected String getPageUrl() {
        return "http://seltr-kbp1-1.synapse.com:8080/";
    }
}
