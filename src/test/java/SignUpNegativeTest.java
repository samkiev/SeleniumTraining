import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.model.Statement;
import pages.ConfigureGlobalSecurityPage;
import pages.CreateAccountResultPage;
import pages.SignUpPage;
import utils.User;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(JUnit4.class)
public class SignUpNegativeTest extends BaseUITest {

    private CreateAccountResultPage resultPage;
    private User user = null;
    
   @ClassRule
   public static TestRule abilityToSignIn = (base, d) -> new Statement() {

       @Override
       public void evaluate() throws Throwable {
           boolean isSwitched = false;
           if (!isPossibleToSignUp()){
               isSwitched = true;
               switchAbilityUsersToSignUp();
           }
           try {
               base.evaluate();
           }
           finally {
               if (isSwitched){
                   switchAbilityUsersToSignUp();
               }
           }
       }
   };


    @Rule
    public TestRule userAvailabilityRule = (base, d) -> new Statement() {

        @Override
        public void evaluate() throws Throwable {
            boolean shouldCleanUp = d.getAnnotation(CleanUpRequired.class) != null;
            if (shouldCleanUp) {
                user = User.generateMockUser().register();
            }
            try {
                base.evaluate();
            } finally {
                if (shouldCleanUp) {
                    user.delete();
                }
            }
        }
    };

    @Test
    public void checkIfAllFieldIsEmpty() {
        resultPage = new SignUpPage(driver).get()
                .signUpAs(User.generateEmptyFieldsUser());
        assertTrue(!resultPage.isLoggedIn());
        assertNotNull(resultPage.getError());
    }

    @Test
    public void checkIfAllFieldIsSpace() {
        resultPage = new SignUpPage(driver).get()
                .signUpAs(User.generateSpaceFieldUser());
        assertTrue(!resultPage.isLoggedIn());
        assertNotNull(resultPage.getError());
    }

    @CleanUpRequired
    @Test
    public void checkSignUpFunctionality() {
        resultPage = new SignUpPage(driver).get()
                .signUpAs(user);
        assertTrue(!resultPage.isLoggedIn());
        assertNotNull(resultPage.getError());
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface CleanUpRequired {
    }
}

