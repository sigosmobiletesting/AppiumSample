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
        List<Object> objList = new ArrayList<Object>();
        objList.add(new AllDriverTest("Android"));
        return objList.toArray();
    }
}
