package Andriod;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.*;

/**
 * Created by ssu on 3/28/2017.
 */
public class PlayStoreAppInstallTest {
    String appiumURL = null;
    private AppiumDriver driver;
    private WebDriverWait wait;

    private static long INSTALL_DURATION_IN_SECONDS = 60L;

    // app to install - details
    final String testAppName = "Chromecast";
    final String testAppPackage = "com.google.android.apps.chromecast.app";
    final String testAppActivity = ".DiscoveryActivity";


    @BeforeClass
    //Executed once for all the script in the class
    public void setUp() throws Exception {

        System.out.println("Starting Android Driver");

        //Mobile testing Url (change this url from start Appium from DA Studio or web studio
        //http://127.0.0.1:4723/wd/hub/
        appiumURL = "http://10.120.22.160:80/da/ensemble/device/735d65b9-d15b-4d3c-a5c7-66b37959fb61/9340/web/appium/wd/hub/";


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

        //(app) or (appPackage and appActivity) one of them is MUST
        //if app is provided this should be (path to local machine script is running or any public URL of the app (should end with .apk)
        //In Mobile testing environment always application is installed from our app upload tool (DA Studio or Web Studio or REST API
        capabilities.setCapability("appPackage", "com.android.vending");
        capabilities.setCapability("appActivity", "com.android.vending.AssetBrowserActivity");
                //capabilities.setCapability("app", "https://adminportal.keynote.com/app/536565.apk");


        //Intialize the driver, try to connect to the server (appium URL mentioned server)
        //New appium session will be intialized (this will create Apium session) MobileTesting session will be issued when appium URL is generated itself)
        driver = new AndroidDriver(new URL(appiumURL), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    //At this point Android driver is initialized fine and communication with the device is established
    //Script will be executed accordingly
    public void executeScript() throws Exception {
        // wait until search bar is visible, and then tap on it
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.android.vending:id/search_box_idle_text\")"))))
                .click();

        // type in the name of the app into the search bar
        driver.findElement(MobileBy.className("android.widget.EditText"))
                .sendKeys(testAppName);

        // tap on the suggested option that contains the app name
        // im using lowercase because of Google's design choice - they list all suggestions in lower case
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.android.vending:id/suggest_text\").text(\"" + testAppName.toLowerCase() + "\")"))))
                .click();

        // wait for the app title to be displayed
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.android.vending:id/li_title\").text(\"" + testAppName + "\")"))));

        // tap on the triple dot icon located on the app's tile
        driver.findElement(MobileBy.xpath("//android.widget.TextView[@content-desc=\"App: " + testAppName + "\"]/following-sibling::android.widget.ImageView[@resource-id=\"com.android.vending:id/li_overflow\"]"))
                .click();

        // tap on the Install button
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.vending:id/title\").text(\"Install\")"))
                .click();

        // tap on accept
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.android.vending:id/continue_button\")"))
                .click();

        // wait until "installed" shows up for INSTALL_DURATION_IN_SECONDS
        new WebDriverWait(driver, INSTALL_DURATION_IN_SECONDS).until(ExpectedConditions.presenceOfElementLocated(
                MobileBy.xpath("//android.widget.TextView[@content-desc=\"App: " + testAppName + "\"]/following-sibling::android.view.View[@resource-id=\"com.android.vending:id/li_label\"][@content-desc=\"Installed\"]")));

        // quit current driver instance - this quits the google playstore
        // and allows us to prepare for next stage - starting up the freshly installed app
        driver.quit();

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