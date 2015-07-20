package pages;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.NoSuchContextException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserPage extends AuthenticationBasePage<UserPage> {

    private final String userLogin;

    @FindBy(css = "#main-panel-content>h1")
    private WebElement mainPanelContentElement;


    protected UserPage(@NotNull WebDriver driver, @NotNull boolean checkIfLoaded, @NotNull String userLogin) {
        super(driver, checkIfLoaded);
        this.userLogin = userLogin.replace(" ", "%20");
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserPageMainContext() {
        return mainPanelContentElement.getText();
    }


    @Override
    protected void isLoaded() throws Error {
        try {
            Assert.assertEquals(driver.getCurrentUrl(), "http://seltr-kbp1-1.synapse.com:8080/user/" + getUserLogin());
        } catch (NoSuchContextException e) {
            Assert.fail(String.format("User page is not loaded. Url: %s", driver.getCurrentUrl()));
        }
    }

    @Override
    protected String getPageUrl() {
        return String.format("http://seltr-kbp1-1.synapse.com:8080/user/%s/", getUserLogin());
    }
}
