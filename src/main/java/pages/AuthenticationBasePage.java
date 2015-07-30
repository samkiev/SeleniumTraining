package pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.User;


public abstract class AuthenticationBasePage<T extends AuthenticationBasePage<T>> extends BasePage<T> {

    public AuthenticationBasePage(@NotNull WebDriver wd) {
        super(wd);
    }

    protected AuthenticationBasePage(@NotNull WebDriver wd, @NotNull boolean checkIfLoaded) {
        super(wd, checkIfLoaded);
    }

    public void logOut(){
        try{
            if (isLoggedIn())
            wait.until(ExpectedConditions.visibilityOf(logOutLink));
            log.info("Log out");
            logOutLink.click();
        }
        catch (NoSuchElementException | TimeoutException e){
        }
    }

    @Override
    public void load() {
        if (!isLoggedIn()) {
            User user = User.setLoginAndPassword("admin", "admin");
            new LoginPage(driver).get().loginAs(user.getLogin(), user.getPassword());
        }
        log.debug("Loading url: {}", getPageUrl());
        driver.get(getPageUrl());
    }
}