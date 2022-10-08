package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.IOUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;


public class Listeners extends Base implements ITestListener {
    ExtentTest test;
    ExtentReports extent= extentReports.ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();

    public Listeners() throws IOException {
    }

    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        String testMethodName =result.getMethod().getMethodName();
        test= extent.createTest(testMethodName)
                 .assignAuthor("Afaque Ahmed")
                 .assignCategory("Mobile UI Test")
                 .assignDevice("Android Emulator");

         extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {

        //Test Case Status
        extentTest.get().log(Status.PASS, "Test Passed Successfully");

        //Test Case Name
        String testMethodName = result.getMethod().getMethodName();

        //Screenshot
         try {
             //Converting Captured image to its base64
             String filePath = getScreenShot(result.getMethod().getMethodName(), (AndroidDriver) driver);
             InputStream in = new FileInputStream(filePath);
             byte[] imageBytes = IOUtils.toByteArray(in);
             String base64 = Base64.getEncoder().encodeToString(imageBytes);
             test.log(Status.PASS,"Attached Screenshot ", MediaEntityBuilder.createScreenCaptureFromBase64String("data:image/png;base64,"+base64).build());
         }catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void onTestFailure(ITestResult result) {

        //Test Status
        extentTest.get().log(Status.FAIL, "Test Failed");
        extentTest.get().fail(result.getThrowable());

        //Test Case Name
        String testMethodName = result.getMethod().getMethodName();

        //Screenshot
        try {

            //Android Driver
            driver =(AndroidDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

            //Converting Captured image to its base64
            String filePath = getScreenShot(result.getMethod().getMethodName(), (AndroidDriver) driver);

            InputStream in = new FileInputStream(filePath);
            byte[] imageBytes = IOUtils.toByteArray(in);
            String base64 = Base64.getEncoder().encodeToString(imageBytes);
            test.log(Status.FAIL, "Attached Screenshot ", MediaEntityBuilder.createScreenCaptureFromBase64String("data:image/png;base64," + base64).build());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        extent.flush();
    }
}



