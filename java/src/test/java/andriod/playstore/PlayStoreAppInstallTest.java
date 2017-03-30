package andriod.playstore;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by ssu on 3/28/2017.
 */
public class PlayStoreAppInstallTest {
    String appiumURL = null;
    private AppiumDriver driver;
    private WebDriverWait wait;

    private static long INSTALL_DURATION_IN_SECONDS = 60L;
    private long explicitWaitTimeoutInSeconds = 10L;

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
        appiumURL = "http://127.0.0.1:4723/wd/hub/";


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

        this.wait = new WebDriverWait(driver, explicitWaitTimeoutInSeconds);

    }

    @Test(priority=1)
    //At this point Android driver is initialized fine and communication with the device is established
    //Script will be executed accordingly
    public void installApp() throws Exception {

        System.out.println("Installing app through play store");

        //Accept google terms and condition
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.vending:id/positive_button']")))).click();
        Thread.sleep(2000);
        //click in the search box
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.android.vending:id/search_box_idle_text']")))).click();
        //inject application name
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.android.vending:id/search_box_text_input']")))).sendKeys(testAppName);
        //click search
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.android.vending:id/suggest_text']")))).click();
        //install
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//android.widget.Button[contains(@text,'INSTALL')]")))).click();


        Thread.sleep(2000);

        WebElement accept = driver.findElement(By.xpath("//android.widget.Button[contains(@text,'ACCEPT')]"));

        //find and click accept if popped up
        if(accept != null)
            accept.click();

        new WebDriverWait(driver, INSTALL_DURATION_IN_SECONDS).until(
                ExpectedConditions.presenceOfElementLocated( By.xpath("//android.widget.Button[contains(@text,'OPEN')]") ));


        driver.quit();

        System.out.println("Installing app done");

        assertTrue(true);
    }

    @Test(priority=2)
    public void configureAppCapabilities() throws Exception {

        System.out.println("setup desired capabilities for app");

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
        capabilities.setCapability("appPackage", testAppPackage);
        capabilities.setCapability("appActivity", testAppActivity);
        //capabilities.setCapability("app", "https://adminportal.keynote.com/app/536565.apk");


        //Intialize the driver, try to connect to the server (appium URL mentioned server)
        //New appium session will be intialized (this will create Apium session) MobileTesting session will be issued when appium URL is generated itself)
        driver = new AndroidDriver(new URL(appiumURL), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority=3)
    public void executeScript() throws Exception {
        System.out.println("execute app script");
    }

    @AfterClass
    //All the script execution is done, have to quit the driver to delete the session from the appium server
    public void tearDown() throws Exception {

        //driver.closeApp();
        if(driver != null)
            driver.quit();

    }
}