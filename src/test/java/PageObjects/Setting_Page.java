package PageObjects;
import TestCases.TC_Change_Dark_Mode;
import io.appium.java_client.MobileBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import resources.Base;
import resources.log_Helper;
import java.io.IOException;
import java.util.Objects;

public class Setting_Page extends Base {

    //logger
    public static Logger log = LogManager.getLogger(TC_Change_Dark_Mode.class);
    log_Helper helper = new log_Helper();
    public static WebDriverWait wait;

    //Wait Duration Defined
    static int duration = 60;
    public void Validate_And_Click_Display_Option() throws IOException {

        //Logging
        log.info("Executing Method: Validate_And_Click_Display_Option");

        //Variables
        String elementText = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[5]/android.widget.RelativeLayout/android.widget.TextView[1]";
        String actualText = "Display";

        By DisplayOption = By.xpath(elementText);

        //Wait Object
        WebDriverWait wait = new WebDriverWait(driver, duration);

        //Waiting till the element is clickable
        log.info("Wait till the DisplayOption element is clickable");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(DisplayOption));
        }   catch (Exception e) {
            helper.exception(e);
        }

        //Getting text value of a element
        String expectedText = GetText(elementText);
        log.info("Get Element Text value : " + expectedText);
        Assert.assertEquals(actualText,expectedText);

        //Condition: Whether DisplayOption is available or not
        try {
            if (Objects.equals(actualText, expectedText)) {

                //Explicit Wait for DisplayOption
                log.info("Wait till the DisplayOption element is clickable");
                wait.until(ExpectedConditions.elementToBeClickable(DisplayOption));

                var element = driver.findElement(DisplayOption);
                element.click();
                log.info("Display Option Clicked");

            } else {
                System.out.println("Error : Display Page not loaded");
                log.error("Display Page Not Loaded");
            }
        }
        catch (Exception e) {

            helper.exception(e);
          }
    }


    public void Validate_State_And_Click_Theme_Button() {

        //Logging
        log.info("Executing Method: Validate_State_And_Click_Theme_Button");

        //Variable
        String summaryText = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView[2]";
        String accessibilityId = "Dark theme";
        String offText = "Off / Will never turn on automatically";
        String onText = "On / Will never turn off automatically";
        String darkModeActivationMessage = "Dark Mode Activated Successfully";
        String darkModeDeactivationMessage = "Dark Mode Deactivated Successfully";

        //Get element by AccessibilityId
        MobileBy.ByAccessibilityId summary = new MobileBy.ByAccessibilityId(accessibilityId);

        //Wait Object
        wait = new WebDriverWait(driver, duration);
        //Explicit Wait for summary
        wait.until(ExpectedConditions.elementToBeClickable(summary));

        //Getting text value of a element
        String expectedText = GetText(summaryText);
        log.info("Getting Text Value for Comparison :\t" + expectedText);


        //Condition: Activate Dark Mode if it is Inactive
        if(Objects.equals(expectedText, offText)) {

            //click on toggle Button
            driver.findElement(summary).click();
            log.info("Toggle button clicked, Button Name :\t " +summary.toString().substring(20,30));

            //Log Success Message
            log.info("Success Message :\t " + darkModeActivationMessage);

            //Get Text
            String assertedText = GetText(summaryText);
            log.info("Getting Text Value for Comparison :\t" + expectedText);

            //Deactivate Assertion
            Assert.assertEquals(assertedText,onText);
            log.info("Text Asserted, Actual Text is :\t " + expectedText);

        }
        //Condition: Deactivate Dark Mode if it is Active
        else if (Objects.equals(expectedText, onText)) {

            //click on toggle Button
            driver.findElement(summary).click();
            log.info("Toggle button clicked, Button Name :\t " +summary.toString().substring(20,30));

            //Log Success Message
            log.info("Success Message :\t\t " + darkModeDeactivationMessage);

            //Get Text
            String assertedText = GetText(summaryText);
            log.info("Getting Text Value for Comparison :\t" + expectedText);

            //Activate Assertion
            Assert.assertEquals(assertedText,offText);
            log.info("Text Asserted, Actual Text is :\t " + expectedText);
        }
        else {
            System.out.println("Expected Text: On / Off message is not found on a field");
            log.info("Expected Text: On / Off message is not found on a field");
    }
    }
}