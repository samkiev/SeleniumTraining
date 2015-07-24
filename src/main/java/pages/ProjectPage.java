package pages;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import utils.LocaleDateExtractor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.Alert;

public class ProjectPage extends AuthenticationBasePage<ProjectPage> {

    @FindBy(css = "div.build-details > a")
    private WebElement buildHistoryDateElement;

    @FindBy(className = "build-row-cell")
    private List<WebElement> buildElements;

    @FindBy(xpath = ".//*[@id='breadcrumbs']/li[3]/a")
    private WebElement projectNameElement;

    @FindBy(id = "yui-gen1-button")
    private WebElement disableButton;

    @FindBy(className = "icon-edit-delete")
    private WebElement deleateProjectIcon;

    @FindBy(className = "icon-clock")
    private WebElement buildNowElement;

    public static final String PROJECT_PAGE_URL = "http://seltr-kbp1-1.synapse.com:8080/job/%s/";
    private String projectName;

    public ProjectPage(@NotNull WebDriver driver, String projectName) {
        super(driver);
        this.projectName = projectName;
    }

    private Integer getLastBuild(List<String> buildList){
        try{
            return  buildList.stream()
                    .mapToInt(eachElement -> Integer.parseInt(String.valueOf(eachElement)))
                    .max()
                    .getAsInt();
        }catch (java.util.NoSuchElementException e){
            return 0;
        }
    }

    private ExpectedCondition<Boolean> isLastBuildLoaded(){
        return new ExpectedCondition<Boolean>(){
            int previusLastBuild = getLastBuild(getBuildNumbers());
            @Override
            public Boolean apply(WebDriver driver){
                return (previusLastBuild < getLastBuild(getBuildNumbers()));
            }
        };
    }

    public ProjectPage addBuild(){
        try{
            buildNowElement.click();
            wait.until(isLastBuildLoaded());
            System.out.println("Build was Added");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    public String getAddedBuildNumber(){
        return getLastBuild(getBuildNumbers()).toString();
    }

    public MainPage deleteProject(){
        try{
            deleateProjectIcon.click();
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new MainPage(driver);
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

    public LocalDateTime getBuildHistoryDate(@NotNull String buildNumber) {
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
        return String.format(PROJECT_PAGE_URL, projectName);
    }
}

