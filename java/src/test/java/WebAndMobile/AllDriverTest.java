package webAndMobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by ssu on 3/28/2017.
 */
public class AllDriverTest {

    String automationType = "Android";
    String appiumURL = "";
    private WebDriver driver;

    public AllDriverTest(String _testType) {
        this.automationType = _testType;
    }

    public AllDriverTest(String _testType, String _appiumUrl) {
        this.automationType = _testType;
        this.appiumURL = _appiumUrl;
    }


    @BeforeClass
    //Executed once for all the script in the class
    public void setUp() throws Exception {


        if(automationType.equalsIgnoreCase("android") || automationType.equalsIgnoreCase("Default test name")) {
            startAndroidDriver(this.appiumURL);
        }
        else if(automationType.equalsIgnoreCase("chrome")) {
            startChromeDriver();
        }
    }

    private void startChromeDriver() throws Exception {

        System.out.println("Starting Desktop Chrome Driver");

        File currentDirectory = new File(new File(".").getAbsolutePath());
        String path = currentDirectory.getCanonicalPath() + "\\src\\test\\java\\webAndMobile\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", path);

        driver = new ChromeDriver();

        System.out.println("Started  Desktop Chrome Driver");

    }

    private void startAndroidDriver(String appiumURL) throws Exception {

        System.out.println("Starting Android Driver");

        //Mobile testing Url (change this url from start Appium from DA Studio or web studio
        //http://127.0.0.1:4723/wd/hub/
        //appiumURL = "http://10.120.22.160:80/da/ensemble/device/ce67deba-d92d-480d-8f09-29d785ac1e0c/9340/web/appium/wd/hub/";

        DesiredCapabilities capabilities = new DesiredCapabilities();

        //deviceName - MUST
        capabilities.setCapability("deviceName","Samsung Galaxy 5");
        //platformVersion - MUST
        capabilities.setCapability("platformVersion", "4.4.4");
        //platformName - MUST
        capabilities.setCapability("platformName","Android");

        //platformName - MUST
        capabilities.setCapability("browserName","Chrome");

        driver = new AndroidDriver(new URL(appiumURL), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        System.out.println("Started Android Driver");

    }

    @Test
    //At this point Android driver is initialized fine and communication with the device is established
    //Script will be executed accordingly
    public void executeScript() throws Exception {

        Thread.sleep(2000);
        //driver.findElement(By.name("Add New Expense")).click();
        driver.get("http://www.google.com");

        Thread.sleep(3000);

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