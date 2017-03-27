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

        MobileTestingHelper mobileTestingHelper = new MobileTestingHelper();

        Integer KSAppId = 211;//mobileTestingHelper.uploadApplication(new File("C:\\Users\\ssu\\Desktop\\Apps\\Iphone\\KitchenSinkApp.ipa"));

        Integer TestAppId = 212;//mobileTestingHelper.uploadApplication(new File("C:\\Users\\ssu\\Desktop\\Apps\\Iphone\\testApp.ipa"));

        Integer RegionsAppId = 213;//mobileTestingHelper.uploadApplication(new File("C:\\Users\\ssu\\Desktop\\Apps\\Iphone\\Regions.ipa"));

        int numberOfRuns = 3;

        Object[] obj = new Object[numberOfRuns * 3];

        for (int i = 0; i < numberOfRuns; i = i + 3) {

            obj[i] = new DARestAppiumExistingApplicationTest(KSAppId);
            obj[i + 1] = new DARestAppiumExistingApplicationTest(TestAppId);
            obj[i + 2] = new DARestAppiumExistingApplicationTest(RegionsAppId);
        }

//        Object[] obj = new Object[numberOfRuns * 3];
//
//        for (int i = 0; i < numberOfRuns; i++) {
//
//            obj[i] = new DARestAppiumExistingApplicationTest(KSAppId);
//        }

        return obj;
    }

}
