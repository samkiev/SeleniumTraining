package pages;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by oleg.semenov on 7/23/2015.
 */
public class CreateNewProjectPage extends AuthenticationBasePage<CreateNewProjectPage> {

    @FindBy(id = "name")
    private WebElement projectNameField;

    @FindBy(css = "input[value*='FreeStyleProject']")
    private WebElement freestyleRabioButton;

    @FindBy(id = "ok-button")
    private WebElement summitButton;

    @FindBy(className = "error")
    private WebElement errorElement;

    public CreateNewProjectPage(@NotNull WebDriver driver) {
        super(driver);
    }

    public ProjectConfigurePage createNewProject(@NotNull String projectName){
           try{
               projectNameField.sendKeys(projectName);
               freestyleRabioButton.click();
               summitButton.click();
           }catch (NoSuchElementException e){
               e.printStackTrace();
           }
        return new ProjectConfigurePage(driver, projectName);
    }

    @Override
    protected String getPageUrl() {
        return "http://seltr-kbp1-1.synapse.com:8080/newJob";
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(isLoggedIn());
        Assert.assertTrue(isOnCreateNewProjectPage());
    }

    private boolean isOnCreateNewProjectPage() {
        return projectNameField.isDisplayed();
    }
}
