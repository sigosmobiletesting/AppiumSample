package Andriod;

import helper.MobileTestingHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by ssu on 2/22/2017.
 */
public class DAAndroidAppFromFileUrlTest {
    String appiumURL = null;
    private AppiumDriver driver;
    MobileTestingHelper mobileTestingHelper;

    @Before
    //Executed once for all the script in the class
    public void setUp() throws Exception {

        System.out.println("Starting ANDROID Driver with DA REST Interface");


        mobileTestingHelper = new MobileTestingHelper();

        //Parameters: mcd,
        //            application file (upload to mobile testing repository and install app to device
        //Response: true or false (success or fail)
        boolean status = mobileTestingHelper.start(25056, "http://dadaccess11dvsm.win.keynote.com:8081/app/2028.apk"); //"http://SFO-AMP-TCV-009.deviceanywhere.com:80/da/ensemble/device/gGDVekHrZkL-oLheOWFI4g/appium/wd/hub/";

        //If response is success all required information is available in mobileTestingHelper object
        //mobileTestingHelper.appiumUrl --> appium url to pass to appium driver
        //ANDROID:
        //mobileTestingHelper.getApplicationInfo().appPackage --> application package to for Appium DesiredCapabilities appPackage
        //mobileTestingHelper.getApplicationInfo().appActivity --> application package to for Appium DesiredCapabilities appActivity
        //iOS
        //mobileTestingHelper.getApplicationInfo().bundleId --> application package to for Appium DesiredCapabilities bundleId

        if(status) {
            startDriver(mobileTestingHelper.appiumUrl);
            System.out.println("Started ANDROID Driver");
        }
        else {
            System.out.println("ANDROID Driver Start Failed");
        }

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
        capabilities.setCapability("appPackage", mobileTestingHelper.getApplicationInfo().appPackage); //"com.expensemanager");
        capabilities.setCapability("appActivity", mobileTestingHelper.getApplicationInfo().appActivity);
        //capabilities.setCapability("app", "http://tcportal21qasm.win.keynote.com/app/6591.apk");


        //Intialize the driver, try to connect to the server (appium URL mentioned server)
        //New appium session will be intialized (this will create Apium session) MobileTesting session will be issued when appium URL is generated itself)
        driver = new AndroidDriver(new URL(appiumURL), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    //At this point Android driver is initialized fine and communication with the device is established
    //Script will be executed accordingly
    public void executeScript() throws Exception {
        Thread.sleep(5000);
        //driver.findElement(By.name("Add New Expense")).click();
        //driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.expensemanager:id/addNewExpense']")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.expensemanager:id/expenseAmountInput']")).sendKeys("80");
//        Thread.sleep(2000);
//
//        driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.expensemanager:id/payee']")).sendKeys("BOFA");
//        driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.expensemanager:id/editCategory']")).click();
//        driver.findElement(By.name("OK")).click();
//        driver.findElement(By.name("Loans")).click();
//        driver.findElement(By.name("Auto")).click();
//        driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.expensemanager:id/editPaymentMethod']")).click();
//        driver.findElement(By.name("Credit Card")).click();
//        driver.findElement(By.name("OK")).click();
//        driver.findElement(By.name("Today Expense:")).click();
//        driver.findElement(By.name("Loans:Auto")).click();
//        driver.findElement(By.name("Delete")).click();
//        driver.findElement(By.name("OK")).click();
//        driver.navigate().back();

        //Remove application
        driver.removeApp(mobileTestingHelper.getApplicationInfo().appPackage);

        assertTrue(true);
    }

    @After
    //All the script execution is done, have to quit the driver to delete the session from the appium server
    public void tearDown() throws Exception {

        if(mobileTestingHelper != null)     {
            mobileTestingHelper.logoutSession();
            System.out.println("Logoff user session");
        }

        //driver.closeApp();
        if(driver != null)
            driver.quit();



    }
}