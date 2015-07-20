package pages;

import org.hamcrest.Matchers;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeletePage extends AuthenticationBasePage<DeletePage> {

    private String userName;

    @FindBy(id = "yui-gen1-button")
    private WebElement confirmDeleteButton;

    public DeletePage(@NotNull WebDriver driver, @NotNull String userName) {
        super(driver);
        this.userName = userName.replaceAll(" ", "%20");
    }

    public MainPage deleteUser() {
        log.info("Goind to delete user: {}", userName);
        confirmDeleteButton.click();
        return new MainPage(driver, true);
    }

    @Override
    protected void isLoaded() throws Error {
            Assert.assertTrue(isLoggedIn());
        try {
             Assert.assertThat(driver.getCurrentUrl().replaceAll("$/", ""), Matchers.equalToIgnoringCase(getPageUrl().replaceAll("$/", "")));
        } catch (NoSuchElementException e) {
            Assert.fail(e.getMessage());
        }

    }

    @Override
    protected String getPageUrl() {
        return "http://seltr-kbp1-1.synapse.com:8080/user/" + userName + "/delete";
    }
}
