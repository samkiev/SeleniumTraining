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
import java.util.stream.Collectors;

public class ProjectPage extends AuthenticationBasePage<ProjectPage> {

    @FindBy(css = "div.build-details > a")
    private WebElement buildHistoryDateElement;

    @FindBy(className = "build-row-cell")
    private List<WebElement> buildElements;

    @FindBy(xpath = ".//*[@id='breadcrumbs']/li[3]/a")
    private WebElement projectNameElement;

    @FindBy(id = "yui-gen1-button")
    private WebElement disableButton;

    public static final String PROJECT_PAGE_URL = "http://seltr-kbp1-1.synapse.com:8080/job/%s/";

    private String projectName;

    public ProjectPage(@NotNull WebDriver driver, String projectName) {
        super(driver);
        this.projectName = projectName;
    }

    public String getDateForBuildNumber(@NotNull String buildNumber) {
        return buildElements.stream()
                .filter(el -> buildNumber.equalsIgnoreCase(el.findElement(By.className("build-name")).getText().replace("#", "")))
                .map(el -> el.findElement(By.className("build-details")).getText())
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    public List<String> getBuildNumbers(){
        return (buildElements.stream()
                .map(num -> (num.findElement(By.className("build-name")).getText()).replace("#", ""))
                .collect(Collectors.toList()));
    }

    public LocalDateTime getBuildHistoryDate(String buildNumber) {
        try {
            return LocaleDateExtractor.getBuildHistoryCorrectDate(getDateForBuildNumber(buildNumber));
        }
        catch (NoSuchElementException e) {
            System.out.println(getProjectName()+ " has no bulds");
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

