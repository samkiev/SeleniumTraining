package pages;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LocaleDateExtractor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class ProjectPage extends AuthenticationBasePage<ProjectPage> {

    @FindBy(css = "div.build-details > a")
    private WebElement buildHistoryDateElement;

    @FindBy(className = "build-row-cell")
    private List<WebElement> buildElements;

    @FindBy(xpath = ".//*[@id='breadcrumbs']/li[3]/a")
    private WebElement projectNameElement;

    @FindBy(id = "yui-gen1-button")
    private WebElement disableButton;

    private WebElement buildElement = null;

    private String projectName;

    public ProjectPage(@NotNull WebDriver driver, String projectName) {
        super(driver);
        this.projectName = projectName;
    }

    private WebElement getBuildDateElement(){
        buildElement = getRandomBuildElement();
        return buildElement.findElement(By.className("build-details"));
    }

    private WebElement getBuildNumberElement(){
        return buildElement.findElement(By.className("build-name"));
    }
    public String getBuildNumber(){
        StringBuffer output = new StringBuffer(buildElement.findElement(By.className("build-name")).getText());
        return output.deleteCharAt(0).toString();
    }

    private WebElement getFirstBuildElement(){
        return buildElements.get(0);
    }

    private WebElement getRandomBuildElement(){
        return buildElements.get(new Random().nextInt(buildElements.size()));
    }

    private WebElement getBuildHistoryDateElement(){
        try {
            return getBuildDateElement();
        }
        catch (NoSuchElementException e){
            System.out.println(getProjectName()+ " has no builds");
        }
        return null;
    }

    public LocalDateTime getBuildHistoryDate() {
        try {
            return LocaleDateExtractor.getBuildHistoryCorrectDate(getBuildHistoryDateElement().getText());
        }
        catch (NoSuchElementException e) {
            System.out.println(getProjectName()+ " has no bulds");
        }
        return null;
    }

    public BuildPage goToBuildPage() {
        log.info("Opening Build Page");

        try{
            getBuildNumberElement().click();
        return
                new BuildPage(driver, getProjectName(), getBuildNumber());
        }
        catch (NoSuchElementException e){ System.out.println(getProjectName()+ " has no bulds");
        }
        return null;
    }

    public String getProjectName() {
        return projectNameElement.getText().replaceAll(" ", "%20");
    }

    public boolean isOnProjectPage() {
        try {
            return disableButton.isDisplayed();
        } catch (NoSuchElementException e) {
        }
        return false;
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(isLoggedIn());
        Assert.assertTrue(isOnProjectPage());
    }

    @Override
    protected String getPageUrl() {
        return "http://seltr-kbp1-1.synapse.com:8080/job/" + projectName;
    }
}

