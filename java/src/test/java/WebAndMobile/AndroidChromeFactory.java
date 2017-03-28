package WebAndMobile;

import org.testng.annotations.Factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssu on 3/28/2017.
 */
public class AndroidChromeFactory {
    @Factory
    public Object[] createInstances() {

        String appiumURL = "http://10.120.22.160:80/da/ensemble/device/88994f27-4bdd-4084-adb5-69ddccc871e3/9340/web/appium/wd/hub/";

        List<Object> objList = new ArrayList<Object>();
        objList.add(new AllDriverTest("Android", appiumURL));
        return objList.toArray();
    }
}
