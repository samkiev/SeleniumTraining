package TestsForFrameWork;

import org.junit.Assert;
import org.junit.Test;
import utils.ApiDataGetter;

import java.io.IOException;

/**
 * Created by tetiana.sviatska on 7/16/2015.
 */
public class TestsForUtils extends BaseTest {

//    @Test(expected = IllegalArgumentException.class)
//    public void getAPIWithNullParameter() {
//        try {
//            ApiDataGetter.getPageApi(null);
//        } catch (IOException e) {
//            Assert.fail(e.getMessage());
//        }
//    }
//
//    @Test
//    public void requestToNonexistentUrl() {
//        try {
//            ApiDataGetter.request("fdfuurfgauergf");
//        } catch (Throwable e) {
//            Assert.fail(e.getMessage());
//        }
//    }

    @Test
    public void getNonExistentDateBuildFromApi() {
        try {
            ApiDataGetter.getApiBuildDate("fdfuurfgauergf", "dhds");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
//
//    @Test(expected = Error.class)
//    public void CreateApi() {
//        ApiDataGetter apiDataGetter = new ApiDataGetter();
//    }


}
