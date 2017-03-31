package ios;

import helper.MobileTestingHelper;
import org.testng.annotations.Factory;

import java.util.ArrayList;
import java.util.List;

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

        List<Object> objList = new ArrayList<Object>();

        for (int i = 0; i < numberOfRuns; i++) {

            objList.add(new DARestAppiumExistingApplicationTest(KSAppId));
            objList.add(new DARestAppiumExistingApplicationTest(TestAppId));
            objList.add(new DARestAppiumExistingApplicationTest(RegionsAppId));
        }

        return objList.toArray();
    }

}
