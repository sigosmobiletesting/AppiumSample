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

        Integer applicationId = 211;//mobileTestingHelper.uploadApplication(new File("C:\\Users\\ssu\\Desktop\\Apps\\Iphone\\KitchenSinkApp.ipa"));

        int numberOfRuns = 3;

        List<Object> objList = new ArrayList<Object>();

        for (int i = 0; i < numberOfRuns; i++) {
            objList.add(new DARestAppiumExistingApplicationTest(applicationId));
        }

        return objList.toArray();
    }

}
