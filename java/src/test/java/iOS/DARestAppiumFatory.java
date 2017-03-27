package iOS;

import Andriod.DAAndroidAppFromFileUrlTest;
import Helper.MobileTestingHelper;
import org.testng.annotations.Factory;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by ssu on 3/24/2017.
 */
public class DARestAppiumFatory {



    @Factory
    public Object[] createInstances() {

        //MobileTestingHelper mobileTestingHelper = new MobileTestingHelper();
        Integer appId = 198;//mobileTestingHelper.uploadApplication(new File("C:\\Users\\ssu\\Desktop\\Apps\\Iphone\\KitchenSinkApp.ipa"));

        int numberOfRuns = 1;

        Object[] obj = new Object[numberOfRuns];

        for (int i = 0; i < numberOfRuns; i++) {

            obj[i] = new DARestAppiumExistingApplicationTest(appId);
        }

        return obj;
    }

}
