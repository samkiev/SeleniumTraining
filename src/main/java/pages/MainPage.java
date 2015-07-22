package pages;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ApiDataGetter;

import java.util.List;
import java.util.stream.Collectors;

public class MainPage extends AuthenticationBasePage<MainPage> {

    @FindBy(css = ".yui-ac-bd>ul>li")
    List<WebElement> listOfDropDownNames;

    @FindBy(css = "#projectstatus a.model-link:not([href*=\"Build\"]")
    static
    List<WebElement> listOfProjectLink;

    @FindBy(id = "description-link")
    private WebElement addDescriptionLinkElement;

    @FindBy(id = "search-box")
    private WebElement searchBoxField;

    @FindBy(id = "yui-ac-content")
    private WebElement expectedSearchElement;

    private Actions action = new Actions(driver);
    public final static String MAIN_PAGE_URL = "http://seltr-kbp1-1.synapse.com:8080/";

    public MainPage(@NotNull WebDriver driver) {
        super(driver);
    }

    public MainPage(@NotNull WebDriver driver, @NotNull boolean checkIfLoaded) {
        super(driver, checkIfLoaded);
    }

    public static List<String> getListOfProject() {
        try{
            return (listOfProjectLink.stream()
                    .map(names -> (names.getText()))
                    .collect(Collectors.toList()));
        }
        catch (NoSuchElementException | NullPointerException e){
            System.out.println("Project list is empty");
            throw new RuntimeException(e);
        }
    }

    public UserPage searchUser(String token, String expectedUser) {
        try {
            searchBoxField.sendKeys(token);
            selectExpectedUser(expectedUser);
        }
        catch (org.openqa.selenium.TimeoutException e) {
            e.printStackTrace();
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
        try{
        action.moveToElement(randomUserName).click()
                .sendKeys(searchBoxField, Keys.ENTER)
                .build()
                .perform();
    }catch (NoSuchElementException e){
            e.printStackTrace();
        }

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
        return MAIN_PAGE_URL;
    }
}
