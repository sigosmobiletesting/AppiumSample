package andriod;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by Moorthi.Subramani on 10/25/2016.
 * Basic appium test to connect with manually inject the appium URL
 * Direct appium Url or appium manual URL started from mobile testing
 */
public class AppiumGlobalUrlTest {
    
    String appiumURL = null;
    private AppiumDriver driver;


    @BeforeClass
    //Executed once for all the script in the class
    public void setUp() throws Exception {

        System.out.println("Starting Android Driver");

        //Mobile testing Url (change this url from start Appium from DA Studio or web studio
        //http://127.0.0.1:4723/wd/hub/
        appiumURL = "http://visa.deviceanywhere.com:6232/resource/device/appium/wd/hub/";


        startDriver(appiumURL);

        System.out.println("Started Android Driver");

    }

    private void startDriver(String appiumURL) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        //deviceName - MUST
        capabilities.setCapability("deviceName","Samsung Galaxy S6 Edge");
        //platformVersion - MUST
        capabilities.setCapability("platformVersion", "10.2");
        //platformName - MUST
        capabilities.setCapability("platformName", "Android");

        //capabilities.setCapability("automationName", "XCUITest");

        capabilities.setCapability("userName", "admin@visa.com");
        capabilities.setCapability("password", "Harmony1");
        capabilities.setCapability("udid", "42d102d0");



        //udid - optional
        //capabilities.setCapability("udid", "76b7d07b");

        //platformName - MUST
        capabilities.setCapability("browserName","Chrome");


        //Intialize the driver, try to connect to the server (appium URL mentioned server)
        //New appium session will be intialized (this will create Apium session) MobileTesting session will be issued when appium URL is generated itself)
        driver = new AndroidDriver(new URL(appiumURL), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    //At this point Android driver is initialized fine and communication with the device is established
    //Script will be executed accordingly
    public void executeScript() throws Exception {

        Thread.sleep(2000);
        //driver.findElement(By.name("Add New Expense")).click();
        driver.get("http://www.google.com");

        Set<String> contextNames = driver.getContextHandles();
        for(String contextname: contextNames){
            System.out.println(contextname);
        }

        System.out.println(driver.getContext());

        //driver.switchTo().window("CHROMIUM");

//        System.out.println(driver.getPageSource());



        //driver.se

        Thread.sleep(3000);

        driver.navigate().back();

        assertTrue(true);
        assertTrue(true);
    }

    @AfterClass
    //All the script execution is done, have to quit the driver to delete the session from the appium server
    public void tearDown() throws Exception {

        //driver.closeApp();
        if(driver != null)
            driver.quit();

    }
}