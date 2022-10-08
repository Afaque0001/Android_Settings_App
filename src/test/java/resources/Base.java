package resources;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Base<driver> {

    //Appium Driver
    public static AppiumDriver driver;
    log_Helper helper = new log_Helper();
    //DesiredCapabilities
    public static DesiredCapabilities caps;

    //Wait
    public WebDriverWait wait;

    //logger
    public static Logger log = LogManager.getLogger(Base.class);

    @BeforeTest
    public void Initialize() throws Throwable {
        try {
            log.info("Initialization Start before Test");

            //DesiredCapabilities for an Appium
            caps = new DesiredCapabilities();
            caps.setCapability(CapabilityType.PLATFORM_NAME, "ANDROID");
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "ANDROID 11.0");
            caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            caps.setCapability(AndroidMobileCapabilityType.AVD, "AppiumClient");
            caps.setCapability(AndroidMobileCapabilityType.AVD_ARGS, "-no-boot-anim -no-snapshot-load");
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            caps.setCapability("appPackage", "com.android.settings");
            caps.setCapability("appActivity", ".Settings");
            caps.setCapability("noReset", "false");
            caps.setCapability("adbExceTimeout", "60000");
            caps.setCapability("ms:waitForAppLaunch", "90");
            caps.setCapability(AndroidMobileCapabilityType.APP_WAIT_DURATION, "180000");

            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AndroidDriver<AndroidElement>(url, caps);

            //Assertion for Android Driver: Check, Should not Null
            Assert.assertNotNull(driver);

        } catch (Exception e) {

            helper.exception(e);
        }
    }

    //Function to get Text value of an element
    public String GetText(String textLocator) {

        //finding element by Xpath
        MobileElement element = (MobileElement) driver.findElementByXPath(textLocator);

        //Store value into String
        String Text = element.getText();

        //return Text value
        return Text;
    }

    public String getScreenShot(String testCaseName, AndroidDriver driver) throws IOException
    {
        TakesScreenshot ts=(TakesScreenshot) driver;
        //Date Formatter
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        //Date Object
        Date date =new Date();

        String filename = sdf.format(date);
        //File Source
        File source =ts.getScreenshotAs(OutputType.FILE);

        //Where Screenshot is stored
        String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
        FileUtils.copyFile(source,new File(destinationFile));
        return destinationFile;
    }


}



