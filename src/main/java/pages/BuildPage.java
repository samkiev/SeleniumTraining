package pages;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LocaleDateExtractor;
import java.time.LocalDateTime;

public class BuildPage extends AuthenticationBasePage<BuildPage> {

    private final String projectName;
    private final String build;

    @FindBy(className = "build-caption")
    private WebElement buildDateElement;

    @FindBy(xpath = ".//*[@id='breadcrumbs']/li[5]/a")
    private WebElement buildPageUniqueElement;

    public BuildPage(@NotNull WebDriver driver, String projectName, String build) {
        super(driver);
        this.projectName = projectName;
        this.build = build;
    }

    public LocalDateTime getBuildPageDate() {
        return LocaleDateExtractor.getBuildPageCorrectDate(getBuildPageDateText());
    }

    public String getBuildVersionOnThePage() {
        StringBuffer output = new StringBuffer();
        try{
            String buildVersionElementText = buildPageUniqueElement.getText();
            output = new StringBuffer(buildVersionElementText);
        }catch (NoSuchElementException e)
        {
            e.printStackTrace();
        }
        return output.deleteCharAt(0).toString();
    }

    private String getBuildPageDateText() {
        try{
            String messageOfBuildPage = buildDateElement.getText();
            return messageOfBuildPage.substring(messageOfBuildPage.indexOf("(") + 1, messageOfBuildPage.indexOf(")"));
        }
        catch (NoSuchElementException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isOnBuildPage() {
        try {
            return buildPageUniqueElement.isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(isLoggedIn());
        Assert.assertTrue(isOnBuildPage());
    }

    @Override
    protected String getPageUrl() {
        return ("http://seltr-kbp1-1.synapse.com:8080/job/" + projectName + "/" + build);
    }
}
