package TestCases;

import PageObjects.Setting_Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import resources.Base;

import java.io.IOException;

public class TC_Change_Dark_Mode extends Base{
    public static Logger log = LogManager.getLogger(TC_Change_Dark_Mode.class);
    Setting_Page setting = new Setting_Page();

    @Test
    public void Activate_and_Deactivate_Dark_Mode() throws IOException {

        log.info("\n\n \t\t Test Started \n");
        log.info("Start to execute Method: Validate_And_Click_Display_Option");
        setting.Validate_And_Click_Display_Option();
        log.info("Method Executed: Validate_And_Click_Display_Option");
        log.info("Start to execute Method: Validate_State_And_Click_Theme_Button");
        setting.Validate_State_And_Click_Theme_Button();
        log.info("Method Executed: Validate_State_And_Click_Theme_Button");
        log.info("Test Completed Successfully");
    }


}
