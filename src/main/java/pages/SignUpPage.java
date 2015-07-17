package pages;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.User;


public class SignUpPage extends BasePage<SignUpPage> {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(name = "password1")
    private WebElement passwordField;

    @FindBy(name = "password2")
    private WebElement confirmPasswordField;

    @FindBy(name = "fullname")
    private WebElement fullNameField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(id = "yui-gen1-button")
    private WebElement signUpButton;

    public SignUpPage(@NotNull WebDriver driver) {
        super(driver);
    }

    public CreateAccountResultPage signUpAs(@NotNull User user) {
        log.info("Signing up as: {}", user);
        sendKeys(usernameField, user.getLogin());
        sendKeys(passwordField, user.getPassword());
        sendKeys(confirmPasswordField, user.getPassword());
        sendKeys(fullNameField, user.getName());
        sendKeys(emailField, user.getEmail());
        signUpButton.click();
        return new CreateAccountResultPage(driver, true);
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(!isLoggedIn());
        Assert.assertEquals("http://seltr-kbp1-1.synapse.com:8080/signup", driver.getCurrentUrl());
    }

    @Override
    protected String getPageUrl() {
        return "http://seltr-kbp1-1.synapse.com:8080/signup";
    }
}
