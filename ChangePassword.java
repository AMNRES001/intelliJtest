package com.soliditech.testing.selenium.conductor.mweb.tests;
import com.soliditech.testing.selenium.conductor.BasePageTest;
import com.soliditech.testing.selenium.conductor.mweb.config.MWEBURLConfig;
import com.soliditech.testing.selenium.conductor.mweb.utils.filehandlers.ConfigFileReader;
import com.soliditech.testing.selenium.conductor.util.TextGenUtil;
import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static com.soliditech.testing.selenium.conductor.SelectorFactory.forName;
import static com.soliditech.testing.selenium.conductor.actions.ActionFactory.*;

/**
 * @author Reshad
 */

@Config(browser = Browser.CHROME, url = MWEBURLConfig.MWEB_QA1_URL)
public class ChangePassword extends BasePageTest {

    TextGenUtil textGenUtil = new TextGenUtil();

    private final String password = "P@ssword1";

    private final String accountId = "65423720";
    // End of Variables

    @Test
    public void loginInstance() throws Exception {

        changePassword(password, accountId);
    }

    public void changePassword(String password, String accountId) throws Exception {
        // Log in to instance
        login(this, ConfigFileReader.getSeleniumAccountUsername(), ConfigFileReader.getSeleniumAccountPassword());
        openQuickSearch(this, accountId);
        switchToContentFrame(this);

        //Click Contacts
        doClick(this, forName("contacts"));

        String originalWindow = driver.getWindowHandle();
        WebElement primaryContact= driver.findElement(By.xpath("//*[contains(text(),'Main Account')]"));
        primaryContact.click();
        // Switch to current window
        Thread.sleep(2000);

        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        //
        doClick(this, forName("updatepassword"));
        //Update Password
        enterText(this, forName("password"), password);
        enterText(this, forName("cfpassword"), password);
        //Click Done
        doClick(this, forName("update"));
        System.out.println("Done");
        driver.quit();
    }



}