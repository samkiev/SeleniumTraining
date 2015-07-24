package pages;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by oleg.semenov on 7/23/2015.
 */
public class ProjectConfigurePage extends AuthenticationBasePage<ProjectConfigurePage> {

    @FindBy(id = "inpage-nav")
    private WebElement configureItemElement;

    @FindBy(id = "yui-gen38-button")
    private WebElement saveButtom;

    private String projectName;

    public ProjectConfigurePage(@NotNull WebDriver driver, String projectName) {
        super(driver);
        this.projectName = projectName;
    }

    public ProjectPage saveConfiguration(){
        try{
            saveButtom.click();
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
        return new ProjectPage(driver, projectName);
    }

    @Override
    protected String getPageUrl() {
        return "http://seltr-kbp1-1.synapse.com:8080/job/" + projectName + "/configure";
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(isLoggedIn());
        Assert.assertTrue(isOnTheConfigureProjectPage());
        Assert.assertTrue(driver.getCurrentUrl().contains(projectName));
    }

    private boolean isOnTheConfigureProjectPage() {
        return configureItemElement.isDisplayed();
    }
}
