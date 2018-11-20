package andriod;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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


    @BeforeClass
    //Executed once for all the script in the class
    public void setUp() throws Exception {

        System.out.println("Starting Android Driver");

        //Mobile testing Url (change this url from start Appium from DA Studio or web studio
        //http://127.0.0.1:4723/wd/hub/
        appiumURL = "http://10.120.100.67:80/da/ensemble/device/VXtb5Hn0xB9i9TcY2ooT6Q/appium/wd/hub/";


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
        capabilities.setCapability("appPackage", "com.keynote.keynotedemo");
        capabilities.setCapability("appActivity", "com.keynote.keynotedemo.UserInfo");
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
        Thread.sleep(5000);
        driver.findElement(By.id("com.expensemanager:id/addNewExpenseButton")).click();
        //driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.expensemanager:id/addNewExpense']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.expensemanager:id/expenseAmountInput']")).sendKeys("80");
       Thread.sleep(5000);
//DS
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