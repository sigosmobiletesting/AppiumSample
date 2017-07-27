package ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by Moorthi.Subramani on 10/25/2016.
 * Basic appium test to connect with manually inject the appium URL
 * Direct appium Url or appium manual URL started from mobile testing
 */
public class HybridWebViewAppiumTest {

    String appiumURL = null;
    private AppiumDriver driver;


    @Before
    //Executed once for all the script in the class
    public void setUp() throws Exception {

        System.out.println("Starting ios Driver");

        //Mobile testing Url (change this url from start Appium from DA Studio or web studio
        appiumURL = "";
        //appiumURL = "";


        startDriver(appiumURL);

        System.out.println("Started ios Driver");

    }

    private void startDriver(String appiumURL) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        //deviceName - MUST
        capabilities.setCapability("deviceName","Apple iPhone 6");
        //platformVersion - MUST
        capabilities.setCapability("platformVersion", "10.2.1");
        //platformName - MUST
        capabilities.setCapability("platformName", "ios");

        //WebKitProxy
        capabilities.setCapability("startIWDP", "true");

        //udid - optional
       // capabilities.setCapability(MobileCapabilityType.UDID, "a451b5e57d3510b45ef15ad710fa4f0245a22cd0");

        //use automationName and xcodeConfigFile for iOS 10+
        capabilities.setCapability("automationName", "XCUITest");
        //capabilities.setCapability("xcodeConfigFile", "/Users/mcit/Desktop/EnsembleBridge/config/da_certificate.xcconfig");

        //(app) or (bundleId) one of them is MUST
        //if app is provided this should be (path to local machine script is running or any public URL of the app (should end with .ipa)
        //App need to be developer signed
        //In Mobile testing environment always application is installed from our app upload tool (DA Studio or Web Studio or REST API
        capabilities.setCapability("bundleId", "");

        //Intialize the driver, try to connect to the server (appium URL mentioned server)
        //New appium session will be intialized (this will create Apium session) MobileTesting session will be issued when appium URL is generated itself)
        driver =  new IOSDriver(new URL(appiumURL), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    //At this point Android driver is initialized fine and communication with the device is established
    //Script will be executed accordingly
    public void executeScript() throws Exception {

       Set<String> handlers = driver.getContextHandles();

        if(handlers.size() > 1) {

        }

        assertTrue(true);
    }

    @After
    //All the script execution is done, have to quit the driver to delete the session from the appium server
    public void tearDown() throws Exception {

        //driver.closeApp();
        if(driver != null)
            driver.quit();

    }
}