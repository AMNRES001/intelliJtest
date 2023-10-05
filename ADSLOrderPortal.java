package com.soliditech.testing.selenium.conductor.mweb.tests;

import com.soliditech.testing.selenium.conductor.BasePageTest;
import com.soliditech.testing.selenium.conductor.util.TextGenUtil;
import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.soliditech.testing.selenium.conductor.SelectorFactory.forXPath;
import static com.soliditech.testing.selenium.conductor.actions.ActionFactory.*;
import static com.soliditech.testing.selenium.conductor.actions.WebElementSelector.forCss;
import static com.soliditech.testing.selenium.conductor.actions.WebElementSelector.forId;

@Config(browser = Browser.CHROME, url = "https://portaldev.mwebaws.co.za")
public class ADSLOrderPortal extends BasePageTest {

	Boolean isNew = true;
	TextGenUtil textGenUtil = new TextGenUtil();
	
	@Test
	public void executeADSLOrderPortal() throws Exception {
		
				// Remove "Read-only" attributes from all "Input" elements
				List<WebElement> inputs = driver.findElements(By.tagName("input"));

				for (WebElement input : inputs) {
				    ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')",input);
				}
				
				// Choose product from main page (ADSL)
				doClick(this, forXPath("//*[@id='header-container']/nav/ul/li[4]/a"));
				doClick(this, forId("btn-order-now-4"));
				
				doClick(this, forCss("#dk5-ddlInclLineToggle1.dk-select.linespeed-select"));
				doClick(this, forCss("#dk5-listbox.dk-select-options li:nth-child(3)"));
				
				doClick(this, forCss("#dk4-ddlLineSpeed1.dk-select.linespeed-select"));
				doClick(this, forCss("#dk4-listbox.dk-select-options li:nth-child(3)"));
				
				doClick(this, forId("btn-order-now-1"));
				doClick(this, forId("order-summary-submit"));
				
				if (isNew == true) {
					
					// New Customer details
					enterText(this, forId("name"), "Bugs"); // Name
					enterText(this, forId("surname"), "Bunny"); // Surname
					enterText(this, forId("mobile-number"), textGenUtil.generateCellNumber()); // Mobile Number
					enterText(this, forId("email"), textGenUtil.generateEmailAddress(true)); // Email address
					doClick(this, forId("_hc")); // "I am Human" check-box
					doClick(this, forXPath("//*[@id='register']/button")); // Register button 
					waitAction(this, 4000);
					
					// Account details
					enterText(this, forId("dnnumber"), textGenUtil. generateLandlineNumber()); // ADSL Line number
					enterText(this, forId("identitynumber"), textGenUtil.generateSaId()); // ID Number
					enterText(this, forId("residentialsstreetnumber"), "30"); // Street Number
					enterText(this, forId("residentialstreetname"), "Rosmead Avenue"); // Street Name
					
					//Suburb input
					enterText(this, forId("residentialsuburb"), "Kenilworth"); 
					waitAction(this);
					doClick(this, forCss("#slist1 li:nth-child(3)"));		
					
					// Bank name
					doClick(this, forCss("#bankingname.ng-untouched.ng-pristine.ng-invalid"));
					doClick(this, forCss("#banklist li:nth-child(1)"));
					
					// Account number and -holder
					enterText(this, forId("bankaccountnumber"), textGenUtil.generateRandomString(false, false, true, false, 10)); // Account Number
					enterText(this, forId("bankingaccountname"), "GG Moore"); // Account Number
					
					// Account Type
					doClick(this, forCss("#bankingaccounttype.border-bottom-radius.ng-untouched.ng-pristine.ng-invalid"));
					doClick(this, forCss("#accounttypelist li:nth-child(1)"));
					
					// Accept check-boxes
					doClick(this, forId("terms"));
					
					// Submit
					waitAction(this, 2000);
					doClick(this, forId("btnSubmit"));
					
				} else {
					
					// Existing Customer details
					enterText(this, forId("user_id"), "50269896"); // Username
					enterText(this, forId("passwd"), "Test1234!"); // Password
					doClick(this, forXPath("//*[@id='login']/div[1]/div[3]/input")); // "I am Human" check-box
					doClick(this, forId("btn-login")); // Login button 
					
					// Telephone number
					enterText(this, forId("dnnumber"), textGenUtil.generateLandlineNumber());
					
					// Accept check-box
					doClick(this, forId("terms"));

					// Submit
					waitAction(this, 2000);
					doClick(this, forId("btnSubmit"));
					
					// Add test comment to check repository
										
		}	
	}
}
