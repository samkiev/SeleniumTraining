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
import java.util.Collections;
import java.util.List;
import java.util.Random;
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

    private WebElement buildElement = null;

    private String projectName;
    private List<String> el;

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

    public String[] getAvailableBuilds() {
        List<String> list = buildElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        return list.toArray(new String[list.size()]);
    }

    public String getDateForBuildNumber(@NotNull String buildNumber) {
        return buildElements.stream()
                .filter(el -> buildNumber.equalsIgnoreCase(el.findElement(By.className("build-name")).getText().replace("#", "")))
                .map(el -> el.findElement(By.className("build-details")).getText())
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    public List<String> getBuildNumbers(){
        return (buildElements.stream().map(num -> (num.findElement(By.className("build-name")).getText()).replace("#", "")).collect(Collectors.toList()));
        //System.out.println(el);

//
// for (String number : builds) {
//            number = number.substring(number.indexOf("#")+1, number.indexOf(" "));
//            System.out.println(number);
//        }
//        return builds;
    }
//    public BuildPage openBuildPage(String buildNumber) {
//
//    }

//
//    private WebElement getBuildHistoryDateElement(String buildNumber){
//        try {
//            return getDateForBuildNumber(buildNumber);
//        }
//        catch (NoSuchElementException e){
//            System.out.println(getProjectName()+ " has no builds");
//        }
//        return null;
//    }

    public LocalDateTime getBuildHistoryDate(String buildNumber) {
        try {
            return LocaleDateExtractor.getBuildHistoryCorrectDate(getDateForBuildNumber(buildNumber));
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

