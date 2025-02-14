package com.comcast.crm.orgtest;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertiesUtility;
import com.comcast.crm.generic.javautility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectreposatory.CreateNewOrganizationPage;
import com.comcast.crm.objectreposatory.HomePage;
import com.comcast.crm.objectreposatory.LoginPage;
import com.comcast.crm.objectreposatory.OrganizationInfoPage;
import com.comcast.crm.objectreposatory.OrganizationsPage;

public class CreateOrgTest {
	@Test
	
	public void validateOrgname() throws FileNotFoundException, IOException, ParseException, InterruptedException {
				
				/*create object*/
				PropertiesUtility pLib=new PropertiesUtility();
				ExcelUtility eLib=new ExcelUtility();
				JavaUtility jLib=new JavaUtility();
				WebDriverUtility wLib=new WebDriverUtility();
				
				//Using JSON File for common data input
				String browser=pLib.getDataFromPropertiesFile("browser");
				String url=pLib.getDataFromPropertiesFile("url");
				String un=pLib.getDataFromPropertiesFile("username");
				String pwd=pLib.getDataFromPropertiesFile("password");
				System.out.println("=========DDT using .json=============");
				
				
				//fetching data for script from excel
				String data = eLib.getDataFromExcel("org", 1, 2);
				
				//adding a random number to the orgName to make it unique
				String orgName=data+jLib.getRandomNumber();
				
				//choosing and launching browser
				WebDriver driver=wLib.browserSelect(browser);
				wLib.waitForPageToLoad(driver);
				driver.get(url);
				
				//Step 1: Login
				LoginPage f = new LoginPage(driver);
				f.loginToApp(un, pwd);
				
				//Step 2: navigate to the organizations module
				HomePage hp=new HomePage(driver);
				hp.clickOrgLink();
				
				//Step 3: Click on Create organization button
				OrganizationsPage op=new OrganizationsPage(driver);
				op.clickPlusIcon();
				
				//Step 4: Enter the org name and click save to create
				CreateNewOrganizationPage cop=new CreateNewOrganizationPage(driver);
				cop.enterOrgName(orgName);
				cop.clickSaveButton();
				
				//validate orgName in the header
				OrganizationInfoPage oip=new OrganizationInfoPage(driver);
				String headerText = oip.getHeaderText();
				
				if(headerText.contains(orgName)) {
					System.out.println(orgName+"is vaildated====PASS");
					}
				else {
					System.out.println(orgName+"is not present====FAIL");
					Assert.fail();
				}
				//Step 5: Logout
				hp.signOut();
				driver.quit();
	}

}
