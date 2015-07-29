package pages;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by oleg.semenov on 7/29/2015.
 */
public class ManageJenkinsPage extends AuthenticationBasePage<ManageJenkinsPage> {

    @FindBy(id = "management-links")
    WebElement managePageuniqeElement;

    public ManageJenkinsPage(@NotNull WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getPageUrl() {
        return "http://seltr-kbp1-1.synapse.com:8080/manage";
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(isLoggedIn());
        Assert.assertTrue(isOnThePage());
    }

    private boolean isOnThePage() {
        return managePageuniqeElement.isDisplayed();
    }
}
