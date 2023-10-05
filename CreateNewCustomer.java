package com.soliditech.testing.selenium.conductor.mweb.tests;

import com.soliditech.testing.selenium.conductor.BasePageTest;
import com.soliditech.testing.selenium.conductor.util.TextGenUtil;
import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static com.soliditech.testing.selenium.conductor.SelectorFactory.forName;
import static com.soliditech.testing.selenium.conductor.SelectorFactory.forXPath;
import static com.soliditech.testing.selenium.conductor.actions.ActionFactory.*;

/**
 * @author Hilton
 */

@Config(browser = Browser.CHROME, url = "https://solidqa.mwebaws.co.za/mwebcore/admin/login/login.jsp")
public class CreateNewCustomer extends BasePageTest {
	
	
	TextGenUtil textGenUtil = new TextGenUtil();
	
	/* Variables - change these if you wish to use specific details, otherwise leave as default
	Leave blank strings eg: "" if you want to leave out that field (only for non mandatory fields) */
	
	//Title (mandatory)
	String title = "Mr";
	
	//First Name (mandatory)
	String firstName = "John";
	
	//Last Name (mandatory)
	String lastName = "Smith";
	
	//ID Number Type (mandatory)
	String idNumberType = "RSA ID Number";
	
	//ID Number (leave blank if you want a random ID generated) (mandatory)
	String idNumber = "";
	
	//Birth Date (not mandatory)
	String birthDay = "";
	String birthMonth = "";
	String birthYear = "";

	//Home Phone Number (not mandatory)
	String homePhoneNumber = "";
	
	//Cellphone Number (leave blank if you want a random number generated) (mandatory)
	String cellNumber = "";
	
	//Email Address (leave blank if you want a random email generated) (mandatory)
	String emailAddress = "johnsmith@gmail.com";
	
	//Physical Address Details (mandatory)
	String physicalLine1 = "100 Fairway Close";
	String physicalLine2 = "Fairway Close";
	String physicalSuburb = "Goodwood";
	String physicalCity = "Cape Town";
	String physicalProvince = "Western Cape";
	String physicalCountry = "South Africa";
	String physicalStreetCode = "7460";
	
	//Postal Address Details - if the details are the same as the physical address, leave the strings blank
	String postalLine1 = "";
	String postalLine2 = "";
	String postalSuburb = "";
	String postalCity = "";
	String postalProvince = "";
	String postalCountry = "";
	String postalStreetCode = "";
			
	// Account Holder (leave blank if the same name as the customer)
	String accountHolder = "";
	
	//Account Number
	String accountNumber = "12345654321";
	
	//Account Type
	String accountType = "Cheque/Current Account";
	
	//Bank
	String bank = "ABSA BANK";
	
	//Branch Code
	String branchCode = "632005";
	
	
	/* ^ End of variables ^ */
	
	@Test
	public void createNewCustomer() throws Exception {
		
		// Log in to instance
		login(this, "hdorma", "****");
		
		//Open the 'New Personal' page
		doClick(this, forXPath("/html/body/div[1]/div/div[1]/div/div/ul[2]/li[4]/a"));
		
		//Switch to the current frame
		switchToContentFrame(this);
		
		//Select the contact title
		Select titleDropdown = new Select(driver.findElement(By.name("titleId")));
		titleDropdown.selectByVisibleText(title);
		
		//Enter the first and last name
		enterText(this, forName("firstName"), firstName);
		enterText(this, forName("lastName"), lastName);
		
		//Select the ID Number Type and generate an ID
		Select idDropdown = new Select(driver.findElement(By.name("masterIdNumberTypeId")));
		idDropdown.selectByVisibleText(idNumberType);
		if(idNumber!="")
		{
			enterText(this, forName("masterIdNumber"),idNumber);
		}
		else
		{
			enterText(this, forName("masterIdNumber"),textGenUtil.generateSaId());
		}
		
		//If birth date variables chosen fill in the birth date fields
		if(birthDay!="" && birthMonth!="" && birthYear!="")
		{
			enterText(this, forName("birthDay"), birthDay);
			enterText(this, forName("birthMonth"), birthMonth);
			enterText(this, forName("birthYear"), birthYear);
		}
		
		//If home phone number variable chosen fill in the home phone number field
		if(homePhoneNumber!="")
		{
			enterText(this, forName("homeNumber"), homePhoneNumber);
		}
		
		//Enter the cellphone number
		if(cellNumber!="")
		{
			enterText(this, forName("cellNumber"), cellNumber);
		}
		else
		{
			enterText(this, forName("cellNumber"), textGenUtil.generateCellNumber());
		}
		
		//Enter the email address
		if(emailAddress!="")
		{
			enterText(this, forName("emailAddress"), emailAddress);
		}
		else
		{
			enterText(this, forName("emailAddress"), textGenUtil.generateEmailAddress(true));
		}
		
		//Enter the address details
		enterText(this, forName("physicalLine1"), physicalLine1);
		enterText(this, forName("physicalLine2"), physicalLine2);
		enterText(this, forName("physicalSuburb"), physicalSuburb);
		enterText(this, forName("physicalCity"), physicalCity);
		
		Select physicalProvinceDropdown = new Select(driver.findElement(By.name("physicalProvinceId")));
		physicalProvinceDropdown.selectByVisibleText(physicalProvince);
		
		
		Select physicalCountryDropdown = new Select(driver.findElement(By.name("physicalCountryId")));
		physicalCountryDropdown.selectByVisibleText(physicalCountry);
		
		enterText(this, forName("physicalCode"), physicalStreetCode);
		
		//Enter the postal address details
		if(postalLine1 !="" && postalLine2!="" && postalSuburb!="" && postalCity!="" && postalProvince!="" && postalCountry!="" && postalStreetCode!="")
		{
			enterText(this, forName("postalLine1"), postalLine1);
			enterText(this, forName("postalLine2"), postalLine2);
			enterText(this, forName("postalSuburb"), postalSuburb);
			enterText(this, forName("postalCity"), postalCity);
			
			Select postalProvinceDropdown = new Select(driver.findElement(By.name("postalProvinceId")));
			postalProvinceDropdown.selectByVisibleText(postalProvince);
			
			
			Select postalCountryDropdown = new Select(driver.findElement(By.name("postalCountryId")));
			postalCountryDropdown.selectByVisibleText(postalCountry);
			
			enterText(this, forName("postalCode"), postalStreetCode);
		}
		else
		{
			enterText(this, forName("postalLine1"), physicalLine1);
			enterText(this, forName("postalLine2"), physicalLine2);
			enterText(this, forName("postalSuburb"), physicalSuburb);
			enterText(this, forName("postalCity"), physicalCity);
			
			Select postalProvinceDropdown = new Select(driver.findElement(By.name("postalProvinceId")));
			postalProvinceDropdown.selectByVisibleText(physicalProvince);
			
			
			Select postalCountryDropdown = new Select(driver.findElement(By.name("postalCountryId")));
			postalCountryDropdown.selectByVisibleText(physicalCountry);
			
			enterText(this, forName("postalCode"), physicalStreetCode);
		}
		
		//Click the "Create Account" button
		doClick(this, forName("create"));
		
		
		//Enter the Account Extended Details
		
		//Enter the account holder
		if(accountHolder!="")
		{
			enterText(this, forName("accountHolder"), accountHolder);
		}
		else
		{
			enterText(this, forName("accountHolder"), firstName + " " + lastName);
		}
		
		//Enter the account number
		enterText(this, forName("accountNumber"), accountNumber);
		
		//Select the account type
		Select accountTypeDropdown = new Select(driver.findElement(By.name("accountTypeId")));
		accountTypeDropdown.selectByVisibleText(accountType);
		
		//Select the bank
		Select bankDropdown = new Select(driver.findElement(By.name("bankId")));
		bankDropdown.selectByVisibleText(bank);
		
		//Enter the branch code
		enterText(this, forName("branchNumber"), branchCode);
		
		//Click the "Save" button
		doClick(this, forName("saveBankSkipMarketingDetails"));
		
		//Click the "Continue" button
		doClick(this, forName("continue"));
		
		
		//System.out.println(""); //use this line to toggle a breakpoint, in order to leave the master account open, then run in debug mode
	}

}

