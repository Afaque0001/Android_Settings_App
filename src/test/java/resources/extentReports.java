package resources;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

public class extentReports {

    public class ExtentReporterNG {

         //static ExtentReports extent;
         public static Logger log = LogManager.getLogger(Base.class);

        public static ExtentReports getReportObject() throws IOException {
            //Report Path
            String path =System.getProperty("user.dir")+"\\reports\\index.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);

            ExtentReports extent =new ExtentReports();


            extent.attachReporter(reporter);

            //Additional Configuration for report
            reporter.config().setReportName("Appium Test Automation Results");
            reporter.config().setDocumentTitle("Test Results");
            reporter.config().setTimelineEnabled(true);
            reporter.config().setTimeStampFormat("dd-MM-yyy hh:mm:ss");
            reporter.config().setTheme(Theme.DARK);
            extent.setSystemInfo("Tester", "Afaque Ahmed");
            return extent;
         }
    }
}


