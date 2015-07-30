package pages;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by oleg.semenov on 7/29/2015.
 */
public class ConfigureGlobalSecurityPage extends AuthenticationBasePage<ConfigureGlobalSecurityPage>{

    @FindBy(className = "icon-secure")
    private WebElement uniqueElement;

    @FindBy(name = "_.allowsSignup")
    private WebElement allowRegistrationCheckBox;

    @FindBy(id = "yui-gen5-button")
    private WebElement saveButton;

    public ConfigureGlobalSecurityPage(@NotNull WebDriver driver) {
        super(driver);
    }

    public ManageJenkinsPage exchangePossibilityUsersToSignIn() {
        try{
            log.info("Sign in possibility changed");
            allowRegistrationCheckBox.click();
            saveButton.click();
            wait.until(ExpectedConditions.urlToBe(ManageJenkinsPage.MANAGE_JENKINS_PAGE_URL));
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
        return new ManageJenkinsPage(driver);
    }

    @Override
    protected String getPageUrl() {
        return "http://seltr-kbp1-1.synapse.com:8080/configureSecurity/";
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(isLoggedIn());
        Assert.assertTrue(isOnThePage());
    }

    private boolean isOnThePage() {
        try {
            return uniqueElement.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }
}
