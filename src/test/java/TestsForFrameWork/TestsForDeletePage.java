package TestsForFrameWork;

import org.junit.Assert;
import org.junit.Test;
import pages.DeletePage;

/**
 * Created by tetiana.sviatska on 7/16/2015.
 */
public class TestsForDeletePage extends BaseTest {

    @Test(expected = IllegalArgumentException.class)
    public void loadPageWithNullArgument() {
        try {
            DeletePage deletePage = new DeletePage(null, "").get();
        } catch (NullPointerException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void LoadPageWithSpaceConsistedName() {
        try {
            DeletePage deletePage = new DeletePage(driver, "bla bla").get();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
