package ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by Moorthi.Subramani on 10/25/2016.
 * Basic appium test to connect with manually inject the appium URL
 * Direct appium Url or appium manual URL started from mobile testing
 */
public class BasicAppiumTest {

    String appiumURL = null;
    private AppiumDriver driver;


    @Before
    //Executed once for all the script in the class
    public void setUp() throws Exception {

        System.out.println("Starting ios Driver");

        //Mobile testing Url (change this url from start Appium from DA Studio or web studio
        appiumURL = "http://10.120.22.186:80/da/ensemble/device/dbb4e254-b671-40aa-8ccc-dcd1fc86907f/30180/web/appium/wd/hub/";
        //appiumURL = "http://10.120.22.38:4723/wd/hub/";//"http://SFO-AMP-TCV-009.deviceanywhere.com:80/da/ensemble/device/um4dW1x6rd3XGMyNhfUzYQ/appium/wd/hub/";


        startDriver(appiumURL);

        System.out.println("Started ios Driver");

    }

    private void startDriver(String appiumURL) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        //deviceName - MUST
        capabilities.setCapability("deviceName","Apple iPhone 6");
        //platformVersion - MUST
        capabilities.setCapability("platformVersion", "10.2");
        //platformName - MUST
        capabilities.setCapability("platformName", "ios");

        //udid - optional
       // capabilities.setCapability(MobileCapabilityType.UDID, "a451b5e57d3510b45ef15ad710fa4f0245a22cd0");

        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("xcodeConfigFile", "/Users/mcit/Desktop/EnsembleBridge/config/da.xcconfig");

        //(app) or (bundleId) one of them is MUST
        //if app is provided this should be (path to local machine script is running or any public URL of the app (should end with .ipa)
        //App need to be developer signed
        //In Mobile testing environment always application is installed from our app upload tool (DA Studio or Web Studio or REST API
        capabilities.setCapability("bundleId", "com.kone.KitchenSink");

        //Intialize the driver, try to connect to the server (appium URL mentioned server)
        //New appium session will be intialized (this will create Apium session) MobileTesting session will be issued when appium URL is generated itself)
        driver =  new IOSDriver(new URL(appiumURL), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    //At this point Android driver is initialized fine and communication with the device is established
    //Script will be executed accordingly
    public void executeScript() throws Exception {

        //XcuPath
        Thread.sleep(2000);
        driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[2]/XCUIElementTypeButton[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[2]")).click();
        Thread.sleep(1000);
        //driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTable[1]/XCUIElementTypeCell[1]")).click();
        //Thread.sleep(1000);
        //driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeScrollView[1]/XCUIElementTypeButton[1]")).click();
        //Thread.sleep(1000);
        driver.findElement(By.xpath("//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeTabBar[1]/XCUIElementTypeButton[5]")).click();
        Thread.sleep(5000);

//        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAButton[2]")).click();
//        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
//        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
//        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
//        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIATableView[1]/UIATableCell[1]/UIAStaticText[1]")).click();
//        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAScrollView[1]/UIATextField[1]")).click();
//        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAScrollView[1]/UIATextField[1]")).sendKeys("Keynote Systems");
//        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIAScrollView[2]/UIASecureTextField[1]")).sendKeys("Keynote Systems");
//        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[1]")).click();
//        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[1]")).click();
//        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[1]")).click();
//        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[1]")).click();
//        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIATableView[1]/UIATableCell[2]/UIAStaticText[1]")).click();
//        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAButton[1]")).click();
//        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[2]/UIAScrollView[1]/UIATableView[1]/UIATableCell[3]/UIAStaticText[1]")).click();

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