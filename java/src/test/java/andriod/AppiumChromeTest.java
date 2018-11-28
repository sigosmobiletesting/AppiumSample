package andriod;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
public class AppiumChromeTest {
    
    String appiumURL = null;
    private AppiumDriver driver;


    @BeforeClass
    //Executed once for all the script in the class
    public void setUp() throws Exception {

        System.out.println("Starting Android Driver");

        //Mobile testing Url (change this url from start Appium from DA Studio or web studio
        //http://127.0.0.1:4723/wd/hub/
        appiumURL = "http://172.20.118.87:80/da/ensemble/device/gLOB3rgwQSDHjkRYPsW0KA/appium/wd/hub/";


        startDriver(appiumURL);

        System.out.println("Started Android Driver");

    }

    private void startDriver(String appiumURL) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        //deviceName - MUST
        capabilities.setCapability("deviceName","Samsung Galaxy 5");
        //platformVersion - MUST
        capabilities.setCapability("platformVersion", "4.4.4");
        //platformName - MUST
        capabilities.setCapability("platformName","Android");

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
        driver.get("https://05-daq.str.progressivedirect.com/Slot1/dq/ApplicationStart.aspx?captureErrorLogs=&offering=CO&Product=AU&classType=&enableBlockerVisibility=&disableCache=&debug=&disableBundlingAndMinification=&enableThreadLockLogging=&enableInFlightTesting=&enablePacketErrorsDisplay=&enableDeviceId=&isMoveOutOfState=N&equityTransferAmount=&equityTransferDate=&moveOutOfStatePolicyNbr=&enableHVDFBrokerUseDoc=&Stubbing=&zipCode=80020&residency=&HQXSupportedBrowser=Y&ServiceOverrides=MobileDetect%3dMobileDetectForceAndroid%26QuoteAbandonment%3dNormal+Quote&ABTestOverrides=%26chat_in_quote_abtest%3d0505A%26cov_pkg_use_spss_abtest_model5%3d1992B");

        Thread.sleep(3000);

        Set<String> contextNames = driver.getContextHandles();
        for(String contextname: contextNames){
            System.out.println(contextname);
        }

        System.out.println(driver.getContext());

        Thread.sleep(5000);

        WebElement elementFN =   driver.findElementById("NameAndAddressEdit_embedded_questions_list_FirstName");

        //TouchAction touchAction = new TouchAction(driver);
        //Point p = ((Locatable) elementFN).getCoordinates().onPage();
        //driver.performTouchAction(touchAction.tap(PointOption.point(p.getX(), p.getY())));


        elementFN.click();

        Thread.sleep(3000);

        elementFN.sendKeys("A1");

        WebElement elementLN =   driver.findElementById("NameAndAddressEdit_embedded_questions_list_LastName");

        elementLN.click();

        //driver.performTouchAction(touchAction.tap(PointOption.point(elementLN.getLocation().getX(), elementLN.getLocation().getX())));

        Thread.sleep(3000);


        elementLN.sendKeys("Test");

        Thread.sleep(3000);

        driver.findElementById("next").click();

        Thread.sleep(30000);

        driver.navigate().back();

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