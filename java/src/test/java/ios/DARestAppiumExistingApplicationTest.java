package ios;

import helper.MobileTestingHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by Moorthi.Subramani on 10/25/2016.
 * Basic appium test to connect with manually inject the appium URL
 * Direct appium Url or appium manual URL started from mobile testing
 */
public class DARestAppiumExistingApplicationTest {

    String appiumURL = null;
    private AppiumDriver driver;
    private MobileTestingHelper mobileTestingHelper;
    Integer applicationId = -1;

    public DARestAppiumExistingApplicationTest(Integer appId){
        applicationId = appId;
    }


    @BeforeClass
    //Executed once for all the script in the class
    public void setUp() throws Exception {

       System.out.println("Starting ios Driver with DA REST Interface");


        mobileTestingHelper = new MobileTestingHelper();

        //Parameters: mcd,
        //            application file (upload to mobile testing repository and install app to device
        //Response: true or false (success or fail)
        System.out.println("for application -" + applicationId);
        boolean status = mobileTestingHelper.start(9322, applicationId);

        //If response is success all required information is available in mobileTestingHelper object
        //mobileTestingHelper.appiumUrl --> appium url to pass to appium driver
        //ANDROID:
        //mobileTestingHelper.getApplicationInfo().appPackage --> application package to for Appium DesiredCapabilities appPackage
        //mobileTestingHelper.getApplicationInfo().appActivity --> application package to for Appium DesiredCapabilities appActivity
        //ios
        //mobileTestingHelper.getApplicationInfo().bundleId --> application package to for Appium DesiredCapabilities bundleId

        if(status) {
            startDriver(mobileTestingHelper.appiumUrl);
            System.out.println("Started ios Driver");
        }
        else {
            System.out.println("ios Driver Start Failed");
        }
    }

    private void startDriver(String appiumURL) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        //deviceName - MUST
        capabilities.setCapability("deviceName","Apple iPhone 6");
        //platformVersion - MUST
        capabilities.setCapability("platformVersion", "9.3.2");
        //platformName - MUST
        capabilities.setCapability("platformName", "ios");

        //udid - optional
        capabilities.setCapability(MobileCapabilityType.UDID, "a451b5e57d3510b45ef15ad710fa4f0245a22cd0");

        //(app) or (bundleId) one of them is MUST
        //if app is provided this should be (path to local machine script is running or any public URL of the app (should end with .ipa)
        //App need to be developer signed
        //In Mobile testing environment always application is installed from our app upload tool (DA Studio or Web Studio or REST API
        capabilities.setCapability("bundleId", mobileTestingHelper.getApplicationInfo().bundleId);

        System.out.println("Testing application id - " + mobileTestingHelper.getApplicationInfo().applicationId + ", name - " + mobileTestingHelper.getApplicationInfo().appName);


        try {
            //Intialize the driver, try to connect to the server (appium URL mentioned server)
            //New appium session will be intialized (this will create Apium session) MobileTesting session will be issued when appium URL is generated itself)
            driver =  new IOSDriver(new URL(appiumURL), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        catch (Exception exp) {
            if(driver == null && mobileTestingHelper != null)     {
                mobileTestingHelper.logoutSession();
                Thread.sleep(30000);
                System.out.println("Logoff user session");
            }

            throw exp;
        }



    }

    @Test
    //At this point Android driver is initialized fine and communication with the device is established
    //Script will be executed accordingly
    public void executeScript() throws Exception {

       //Your script Here

        try {

            if(driver != null) {
                driver.removeApp(mobileTestingHelper.getApplicationInfo().bundleId);
                assertTrue(true);
            }
            else {
                assertTrue(false);
            }

            Thread.sleep(1000);
        }catch (Exception e) {
            System.out.println("driver quit exception, because of app uninstall via Appium");
            assertTrue(false);
        }


    }

    @AfterClass
    //All the script execution is done, have to quit the driver to delete the session from the appium server
    public void tearDown() throws Exception {

        //driver.closeApp();
        try {
            if(driver != null)
                driver.quit();
        }
        catch (Exception exp) {
            System.out.println("driver quit exception, because of app uninstall via Appium");
        }


        if(mobileTestingHelper != null)     {
            mobileTestingHelper.logoutSession();
            Thread.sleep(30000);
            System.out.println("Logoff user session");
        }

    }
}