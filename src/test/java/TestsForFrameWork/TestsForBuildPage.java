package TestsForFrameWork;

import org.junit.Assert;
import org.junit.Test;
import pages.BuildPage;

/**
 * Created by tetiana.sviatska on 7/15/2015.
 */
public class TestsForBuildPage extends BaseTest {

    @Test(expected = IllegalArgumentException.class)
    public void loadPageWithNullArgument() {
        try {
            BuildPage lp = new BuildPage(null).get();
        } catch (NullPointerException e) {
            Assert.fail(e.getMessage());
        }
    }

    @ChangeViewConfig("test")
    @Test
    public void openPageWithAnotherDefaultView() {
        try {
            BuildPage bp = new BuildPage(driver).get();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

}
