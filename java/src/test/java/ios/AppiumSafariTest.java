package ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by Moorthi.Subramani on 10/25/2016.
 * Basic appium test to connect with manually inject the appium URL
 * Direct appium Url or appium manual URL started from mobile testing
 */
public class AppiumSafariTest {

    String appiumURL = null;
    private AppiumDriver driver;


    @BeforeClass
    //Executed once for all the script in the class
    public void setUp() throws Exception {

        System.out.println("Starting iOS Driver");

        //Mobile testing Url (change this url from start Appium from DA Studio or web studio
        //http://127.0.0.1:4723/wd/hub/
        //appiumURL = "http://172.20.122.28:6232/resource/device/appium/wd/hub/session";
       // appiumURL = "http://10.120.100.76:80/da/ensemble/device/VxUBxBLUeJh5TaUqji0jeQ/appium/wd/hub/";

        appiumURL = "http://172.20.118.86:8080/wd/hub/";

        startDriver(appiumURL);

        System.out.println("Started iOS Driver");

    }

    private void startDriver(String appiumURL) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        //deviceName - MUST
        capabilities.setCapability("deviceName", "iPad");
        //platformVersion - MUST
        capabilities.setCapability("platformVersion", "12.0");
        //platformName - MUST
        capabilities.setCapability("platformName", "ios");

        capabilities.setCapability("automationName", "XCUITest");

        //capabilities.setCapability("userName", "admin@visa.com");
        //capabilities.setCapability("password", "Harmony1");
        //capabilities.setCapability("udid", "c7cfd075ccc97406d956f839f0b8488809482976");


        //udid - optional
        capabilities.setCapability("udid", "d2ca6bb2e74d8d6c164a9a5e76d3a3be711885ee");
        capabilities.setCapability("webDriverAgentUrl", "http://10.120.102.88:8100");
        capabilities.setCapability("usePrebuiltWDA", true);
        capabilities.setCapability("startIWDP", true);


        //platformName - MUST
        capabilities.setCapability("browserName", "Safari");


        //Intialize the driver, try to connect to the server (appium URL mentioned server)
        //New appium session will be intialized (this will create Apium session) MobileTesting session will be issued when appium URL is generated itself)
        driver = new IOSDriver(new URL(appiumURL), capabilities);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

    }

    @Test
    //At this point Android driver is initialized fine and communication with the device is established
    //Script will be executed accordingly
    public void executeScript() throws Exception {

        Thread.sleep(2000);
        //driver.findElement(By.name("Add New Expense")).click();
        driver.get("https://05-daq.str.progressivedirect.com/Slot1/dq/ApplicationStart.aspx?captureErrorLogs=&offering=CO&Product=AU&classType=&enableBlockerVisibility=&disableCache=&debug=&disableBundlingAndMinification=&enableThreadLockLogging=&enableInFlightTesting=&enablePacketErrorsDisplay=&enableDeviceId=&isMoveOutOfState=N&equityTransferAmount=&equityTransferDate=&moveOutOfStatePolicyNbr=&enableHVDFBrokerUseDoc=&Stubbing=&zipCode=80020&residency=&HQXSupportedBrowser=Y&ServiceOverrides=MobileDetect%3dMobileDetectForceAndroid%26QuoteAbandonment%3dNormal+Quote&ABTestOverrides=%26chat_in_quote_abtest%3d0505A%26cov_pkg_use_spss_abtest_model5%3d1992B");

        Thread.sleep(7000);

        Set<String> contextNames = driver.getContextHandles();
        for (String contextname : contextNames) {
            System.out.println(contextname);
        }

        System.out.println(driver.getContext());

        Thread.sleep(5000);

        WebElement elementFN = driver.findElementById("NameAndAddressEdit_embedded_questions_list_FirstName");
        WebElement elementLN = driver.findElementById("NameAndAddressEdit_embedded_questions_list_LastName");
        WebElement elementNext = driver.findElementById("next");


        //elementFN.click();

        tapOnElement(elementFN);

        setContextToNative();
        driver.findElement(By.xpath("//XCUIElementTypeKey[@name='A']")).click();

       // driver.getKeyboard().pressKey("A");
        //driver.getKeyboard().releaseKey("A");
        setContextToWebview();

        Thread.sleep(3000);
        //elementFN.sendKeys("A1");
        //elementLN.click();

        tapOnElement(elementLN);
        Thread.sleep(3000);
        setContextToNative();

        Thread.sleep(3000);

        driver.hideKeyboard();

        tapOnElement(elementNext);

        setContextToWebview();


        Thread.sleep(30000);

        driver.navigate().back();

        assertTrue(true);
    }

    public float[] getElementCenter(WebElement element) {
        setContextToWebview();
        JavascriptExecutor js = (JavascriptExecutor) driver;

// get webview dimensions
        Long webviewWidth = (Long) js.executeScript("return screen.width");
        Long webviewHeight = (Long) js.executeScript("return screen.height");

// get element location in webview
        int elementLocationX = element.getLocation().getX();
        int elementLocationY = element.getLocation().getY();

// get the center location of the element
        int elementWidthCenter = element.getSize().getWidth() / 2;
        int elementHeightCenter = element.getSize().getHeight() / 2;
        int elementWidthCenterLocation = elementWidthCenter + elementLocationX;
        int elementHeightCenterLocation = elementHeightCenter + elementLocationY;

// switch to native context
        driver.context("NATIVE_APP");
        float deviceScreenWidth, deviceScreenHeight;

// offset
        int offset = 115;

// get the actual screen dimensions
        deviceScreenWidth = driver.manage().window().getSize().getWidth();
        deviceScreenHeight = driver.manage().window().getSize().getHeight();

// calculate the ratio between actual screen dimensions and webview dimensions
        float ratioWidth = deviceScreenWidth / webviewWidth.intValue();
        float ratioHeight = deviceScreenHeight / webviewHeight.intValue();

// calculate the actual element location on the screen
        float elementCenterActualX = elementWidthCenterLocation * ratioWidth;
        float elementCenterActualY = (elementHeightCenterLocation * ratioHeight) + offset;
        float[] elementLocation = {elementCenterActualX, elementCenterActualY};

// switch back to webview context
        setContextToWebview();
        return elementLocation;
    }

    public void tapOnElement(WebElement element) {
        float[] elementLocation = getElementCenter(element);
        int coordinateX, coordinateY;
        coordinateX = (int) Math.round(elementLocation[0]);
        coordinateY = (int) Math.round(elementLocation[1]);
        driver.context("NATIVE_APP");
        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(coordinateX, coordinateY) ).perform();
        setContextToWebview();
    }

    public void setContextToWebview(){
        Set<String> availableContexts = driver.getContextHandles();

        for (String context : availableContexts) {

            if(context.toLowerCase().contains("webview")) {
                driver.context(context);
                break;
            }
        }
    }

    public void setContextToNative(){
        driver.context("NATIVE_APP");
    }



    @AfterClass
    //All the script execution is done, have to quit the driver to delete the session from the appium server
    public void tearDown() throws Exception {

        //driver.closeApp();
        if(driver != null)
            driver.quit();

    }
}