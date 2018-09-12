package andriod;

import helper.MobileTestingHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by ssu on 12/15/2016.
 */
public class DAAndroidAppiumTest {

    String appiumURL = null;
    String applicationUrl = null;
    private AppiumDriver driver;
    MobileTestingHelper mobileTestingHelper;

    @Before
    //Executed once for all the script in the class
    public void setUp() throws Exception {

        System.out.println("Starting ANDROID Driver with DA REST Interface");
        System.out.println(new Date().toString());


        mobileTestingHelper = new MobileTestingHelper();
        appiumURL = mobileTestingHelper.startAndGetAppiumUrl();

        if(appiumURL != null) {
            applicationUrl = mobileTestingHelper.getApplicationURL();
            startDriver(appiumURL, applicationUrl);
            System.out.println("Started ANDROID Driver");
        }
        else {
            System.out.println("ANDROID Driver Start Failed");
        }

    }

    private void startDriver(String appiumURL, String applicationUrl) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();


        capabilities.setCapability("deviceName","Any");
        capabilities.setCapability("platformVersion", "Any");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("newCommandTimeout", 300);
        capabilities.setCapability("appPackage", "com.keynote.keynotedemo");
        capabilities.setCapability("appActivity", "com.keynote.keynotedemo.UserInfo");



        //Intialize the driver, try to connect to the server (appium URL mentioned server)
        //New appium session will be intialized (this will create Apium session) MobileTesting session will be issued when appium URL is generated itself)
        driver = new AndroidDriver(new URL(appiumURL), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    //At this point Android driver is initialized fine and communication with the device is established
    //Script will be executed accordingly
    public void executeScript() throws Exception {

        System.out.println("Script start");
        System.out.println(new Date().toString());

        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/title"));
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_first_name")).sendKeys("Jack ");
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_last_name")).sendKeys("Turner");
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_phone")).sendKeys("777-777-777");
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/radioMale")).click();
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_email")).sendKeys("Jack@mobiletest.com");
        Thread.sleep(20000);
        driver.navigate().back();
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/next_button")).click();
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_recipients")).sendKeys("test@demo.com");
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_message")).sendKeys("Welcome to Keynote Mobile Tetsing demo");
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/send_button")).click();
        Thread.sleep(20000);
        driver.navigate().back();
        Thread.sleep(20000);
        driver.navigate().back();

        System.out.println("Script start 2");
        System.out.println(new Date().toString());

        //Running second time
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/title"));
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_first_name")).sendKeys("Jack ");
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_last_name")).sendKeys("Turner");
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_phone")).sendKeys("777-777-777");
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/radioMale")).click();
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_email")).sendKeys("Jack@mobiletest.com");
        Thread.sleep(20000);
        driver.navigate().back();
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/next_button")).click();
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_recipients")).sendKeys("test@demo.com");
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_message")).sendKeys("Welcome to Keynote Mobile Tetsing demo");
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/send_button")).click();
        Thread.sleep(20000);
        driver.navigate().back();
        Thread.sleep(20000);
        driver.navigate().back();

        //Running third script
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/title"));
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_first_name")).sendKeys("Jack ");
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_last_name")).sendKeys("Turner");
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_phone")).sendKeys("777-777-777");
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/radioMale")).click();
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_email")).sendKeys("Jack@mobiletest.com");
        Thread.sleep(20000);
        driver.navigate().back();
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/next_button")).click();
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_recipients")).sendKeys("test@demo.com");
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_message")).sendKeys("Welcome to Keynote Mobile Tetsing demo");
        Thread.sleep(20000);
        driver.findElement(By.id("com.keynote.keynotedemo:id/send_button")).click();
        Thread.sleep(20000);
        driver.navigate().back();
        Thread.sleep(20000);
        driver.navigate().back();

        System.out.println("Script end");
        System.out.println(new Date().toString());

        assertTrue(true);
    }
//    @Test
//    //At this point Android driver is initialized fine and communication with the device is established
//    //Script will be executed accordingly
//    public void executeScript1() throws Exception {
//
//        Thread.sleep(20000);
//        driver.findElement(By.id("com.keynote.keynotedemo:id/title"));
//        Thread.sleep(20000);
//        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_first_name")).clear();
//        Thread.sleep(20000);
//        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_last_name")).clear();
//        Thread.sleep(20000);
//        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_phone")).clear();
//        Thread.sleep(20000);
//        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_email")).clear();
//        Thread.sleep(20000);
//        driver.navigate().back();
//        Thread.sleep(20000);
//        driver.findElement(By.id("com.keynote.keynotedemo:id/next_button")).click();
//        Thread.sleep(20000);
//        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_recipients")).clear();
//        Thread.sleep(20000);
//        driver.findElement(By.id("com.keynote.keynotedemo:id/edit_message")).clear();
//        Thread.sleep(20000);
//        driver.findElement(By.id("com.keynote.keynotedemo:id/send_button")).click();
//        Thread.sleep(20000);
//        driver.navigate().back();
//        Thread.sleep(20000);
//        driver.navigate().back();
//
//        assertTrue(true);
//    }


    @After
    //All the script execution is done, have to quit the driver to delete the session from the appium server
    public void tearDown() throws Exception {

        //driver.closeApp();
        if(driver != null)
            driver.quit();

        if(mobileTestingHelper != null)     {
            mobileTestingHelper.logoutSession();
            System.out.println("Logoff user session");
        }



    }

}