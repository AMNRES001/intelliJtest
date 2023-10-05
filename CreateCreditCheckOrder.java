package com.soliditech.testing.selenium.conductor.mweb.tests;
import com.soliditech.testing.selenium.conductor.BasePageTest;
import com.soliditech.testing.selenium.conductor.mweb.config.MWEBURLConfig;
import com.soliditech.testing.selenium.conductor.mweb.pageobjects.orders.CreateQuote;
import com.soliditech.testing.selenium.conductor.mweb.utils.filehandlers.ConfigFileReader;
import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import static com.soliditech.testing.selenium.conductor.SelectorFactory.forName;
import static com.soliditech.testing.selenium.conductor.SelectorFactory.forXPath;
import static com.soliditech.testing.selenium.conductor.actions.ActionFactory.*;


/**
 * @author Reshad
 */

@Config(browser = Browser.CHROME, url = MWEBURLConfig.MWEB_QA1_URL)
public class CreateCreditCheckOrder extends BasePageTest {


    //Initialize the Order and MA. Make sure the Promo Code is configured for Credit Check
    private final String masterAccountId = "65423698";
    private final String PROMO_CODE_NAME =  "Fibre Zoom - Standard Offer";
    private final String PRODUCT_NAME = "100Mbps Uncapped Fibre - Zoom";
    private final String QUOTE_SALES_TYPE = "New";
    private final String QUOTE_CATEGORY = "Fibre";
    private final String LINE_INSTRUCTION = "New line";
    private final String PROVIDER_SELECT = "Zoom";
    private final String QUOTE_OFFER = "Standard Offer";




    @Test
    public void executeCreditCheckOrder() throws Exception {

        // Log in to instance
        login(this, ConfigFileReader.getSeleniumAccountUsername(), ConfigFileReader.getSeleniumAccountPassword());

        // Select a customer
        openQuickSearch(this, masterAccountId);
        switchToContentFrame(this);
        //Create a New Quote
        CreateQuote createQuote = new CreateQuote(this);
        createQuote.clickMasterAccountSalesTab();
        createQuote.clickMasterAccountNewQuoteButton();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());


        //Switch to New Tab
        driver.switchTo().window(tabs.get(1));
        createQuote.selectSalesType(QUOTE_SALES_TYPE);
        //Thread.sleep(2000);
        doClick(this, forXPath("/html/body/form/table[4]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/span/div/a/div/b"));
        Actions actions = new Actions(driver);
        actions.sendKeys(QUOTE_CATEGORY);
        actions.sendKeys(Keys.ENTER);
        actions.perform();
        Select quoteCategory = new Select(driver.findElement(By.id("line0subcategoryId")));
        quoteCategory.selectByVisibleText(LINE_INSTRUCTION);
        doClick(this, forXPath("/html/body/form/table[4]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/div[1]/a/div/b"));
        actions.sendKeys(PROVIDER_SELECT);
        actions.sendKeys(Keys.ENTER);
        actions.perform();
        Select quoteOffer = new Select(driver.findElement(By.name("line0offerId")));
        quoteOffer.selectByVisibleText(QUOTE_OFFER);
        Thread.sleep(1000);
        doClick(this, forXPath("/html/body/form/table[4]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/div[2]/a/div/b"));
        actions.sendKeys(PROMO_CODE_NAME);
        actions.sendKeys(Keys.ENTER);
        actions.perform();
        Thread.sleep(1000);
        doClick(this, forXPath("/html/body/form/table[4]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/div[3]/a/div/b"));
        actions.sendKeys(PRODUCT_NAME);
        actions.sendKeys(Keys.ENTER);
        actions.perform();
        doClick(this, forXPath("/html/body/form/table[4]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a[1]"));
        Thread.sleep(2000);
        doClick(this, forXPath("/html/body/form/table[4]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a[1]"));
        Thread.sleep(2000);
        createQuote.proceedToOrderDetailsPage();

        //Proceeds Quote
        //Creates Installation Address from MA Address
        doClick(this, forName("line1addAddressBtn"));
        //Switch to New Tab
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(2));
        doClick(this, forName("copyMasterAccountAddress"));
        doClick(this, forXPath("/html/body/form/table/tbody/tr[2]/td/input"));
        //Switch to New Tab
        ArrayList<String> tabs3 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs3.get(1));
        //Create New Service Account, Press Credit Check button and Place the order
        doClick(this, forName("newServiceAccount"));
        doClick(this, forName("creditcheckacceptance"));
        doClick(this, forName("placeOrder"));

        }
    }
