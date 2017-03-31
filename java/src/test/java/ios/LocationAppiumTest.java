package ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by ssu on 2/15/2017.
 */
public class LocationAppiumTest {

    String appiumURL = null;
    private AppiumDriver driver;


    @Before
    //Executed once for all the script in the class
    public void setUp() throws Exception {

        System.out.println("Starting ios Driver");

        //Mobile testing Url (change this url from start Appium from DA Studio or web studio
        appiumURL = "http://10.120.22.65:4723/wd/hub/";


        startDriver(appiumURL);

        System.out.println("Started Android Driver");

    }

    private void startDriver(String appiumURL) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        //deviceName - MUST
        capabilities.setCapability("deviceName", "Apple iPhone 6");
        //platformVersion - MUST
        capabilities.setCapability("platformVersion", "9.3.2");
        //platformName - MUST
        capabilities.setCapability("platformName", "ios");

        //udid - optional
        capabilities.setCapability(MobileCapabilityType.UDID, "5e55aadc1dac2ecbdeee4b60196992b976df17c4");

        //(app) or (bundleId) one of them is MUST
        //if app is provided this should be (path to local machine script is running or any public URL of the app (should end with .ipa)
        //App need to be developer signed
        //In Mobile testing environment always application is installed from our app upload tool (DA Studio or Web Studio or REST API
        capabilities.setCapability("bundleId", "ca.meridiancu.meridian");

        //Intialize the driver, try to connect to the server (appium URL mentioned server)
        //New appium session will be intialized (this will create Apium session) MobileTesting session will be issued when appium URL is generated itself)
        driver = new IOSDriver(new URL(appiumURL), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    //At this point Android driver is initialized fine and communication with the device is established
    //Script will be executed accordingly
    public void executeScript() throws Exception {



        Thread.sleep(2000);



        //AppiumDriverLocalService appiumDriverLocalService = AppiumDriverLocalService.buildDefaultService();
        //appiumDriverLocalService.start();


        Location loc1 = new Location(37.563803, -122.046973, 0.0);
        driver.setLocation(loc1);
        //driver.launchApp();

        Thread.sleep(5000);

        driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[9]")).click();

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
        if (driver != null)
            driver.quit();


    }
}