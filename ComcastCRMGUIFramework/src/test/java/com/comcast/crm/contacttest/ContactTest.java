package com.comcast.crm.contacttest;

import java.io.IOException;


import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.comcast.crm.baseClass.BaseClass;
import com.comcast.crm.baseClass.UtilityClassObject;
import com.comcast.crm.objectreposatory.ContactsInfoPage;
import com.comcast.crm.objectreposatory.ContactsPage;
import com.comcast.crm.objectreposatory.CreateNewContactPage;
import com.comcast.crm.objectreposatory.CreateNewOrganizationPage;
import com.comcast.crm.objectreposatory.HomePage;
import com.comcast.crm.objectreposatory.OrganizationsPage;
import com.comcast.crm.objectreposatory.OrganizationsSearchPopup;

/**
 * @author ngorai
 * test cases to verify contact functionalities
 */

//@Listeners(com.comcast.crm.generic.listenerutility.ListenerImp.class)
public class ContactTest extends BaseClass {
	

	@Test
	public void createContactTest() throws EncryptedDocumentException, IOException {

		// fetching data for script from excel
		UtilityClassObject.getTest().log(Status.INFO, "fetching data from excel");
		String data = eLib.getDataFromExcel("contact", 1, 2);

		// adding a random number to the orgName to make it unique
		UtilityClassObject.getTest().log(Status.INFO, "adding a random number to the orgName");
		String lastName = data + jLib.getRandomNumber();

		// Step 2: navigate to the contacts module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to the contacts module");
		HomePage hp = new HomePage(driver);
		hp.clickContactsLink();

		// Step 3: Click on Create contact button
		UtilityClassObject.getTest().log(Status.INFO, "Click on Create contact button");
		ContactsPage cp = new ContactsPage(driver);
		cp.clickCreateContactBttn();

		// Step 4: Enter the last name and click save to create
		UtilityClassObject.getTest().log(Status.INFO, "Entering the last name and click save");
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.enterLastName(lastName);
		cncp.clickSaveButton();

		// validate orgName in the header
		UtilityClassObject.getTest().log(Status.INFO, "validating orgName");
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String header = cip.headerText();
		if (header.contains(lastName)) {
			UtilityClassObject.getTest().log(Status.PASS, "is vaildated====PASS");
		} else {
			UtilityClassObject.getTest().log(Status.FAIL, "is not present====FAIL");
		}

	}

	@Test
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException, InterruptedException {
		
		// fetching data for script from excel
		UtilityClassObject.getTest().log(Status.INFO, "fetching data from excel");
		String data = eLib.getDataFromExcel("contact", 1, 2);

		// adding a random number to the orgName to make it unique
		UtilityClassObject.getTest().log(Status.INFO, "adding a random number to the orgName");
		String lastName = data + jLib.getRandomNumber();
		String orgName = data + jLib.getRandomNumber();

		// Step 2: navigate to the organizations module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to the organizations module");
		HomePage hp = new HomePage(driver);
		hp.clickOrgLink();

		// Step 3: Click on Create organization button
		UtilityClassObject.getTest().log(Status.INFO, "Click on Create organization button");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickPlusIcon();

		// Step 4: Enter the org name and click save to create
		UtilityClassObject.getTest().log(Status.INFO, "Enter the org name and click save");
		CreateNewOrganizationPage cop = new CreateNewOrganizationPage(driver);
		cop.enterOrgName(orgName);
		cop.clickSaveButton();

		// Step 5: navigate to the contacts module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to the contacts module");
		Thread.sleep(1000);
		hp.clickContactsLink();

		// Step 6: Click on Create contacts button
		UtilityClassObject.getTest().log(Status.INFO, "Click on Create contacts button");
		ContactsPage cp = new ContactsPage(driver);
		cp.clickCreateContactBttn();

		// Step 4: enetr lastname, search the org name and click save to create
		UtilityClassObject.getTest().log(Status.INFO, "enetr lastname, search the org name and click save");
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.enterLastName(lastName);
		cncp.clickSelectOrgBttn();

		wLib.switchToTabByURL(driver, "Accounts&action");
		OrganizationsSearchPopup osp = new OrganizationsSearchPopup(driver);
		osp.searchOrgInPopup(orgName);
		wLib.switchToTabByURL(driver, "Contacts&action");

		cncp.clickSaveButton();

		// validate orgName in the header
		UtilityClassObject.getTest().log(Status.INFO, "validate orgName in the header");
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String actOrgName = cip.actOrgName();
		Assert.assertEquals(actOrgName, orgName);
		UtilityClassObject.getTest().log(Status.PASS, "is vaildated====PASS");

	}

	@Test
	public void createContactWithSupportDateTest() throws EncryptedDocumentException, IOException {

		// fetching data for script from excel
		UtilityClassObject.getTest().log(Status.INFO, "fetching data from excel");
		String data = eLib.getDataFromExcel("contact", 4, 2);

		// getting todays date as support start date
		UtilityClassObject.getTest().log(Status.INFO, "getting todays date");
		String startDate = jLib.getSystemDateYYYYMMDD();

		// getting 30 days after date as support end date
		UtilityClassObject.getTest().log(Status.INFO, "getting 30 days after date");
		String endDate = jLib.getRequiredDateYYYYMMDD(30);

		// adding a random number to the lastName to make it unique
		UtilityClassObject.getTest().log(Status.INFO, "adding a random number to the lastName");
		String lastName = data + jLib.getRandomNumber();

		// Step 2: navigate to the contacts module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to the contacts module");
		HomePage hp = new HomePage(driver);
		hp.clickContactsLink();

		// Step 3: Click on Create organization button
		UtilityClassObject.getTest().log(Status.INFO, "Click on Create organization button");
		ContactsPage cp = new ContactsPage(driver);
		cp.clickCreateContactBttn();

		// Step 4: Enter the org name and click save to create
		UtilityClassObject.getTest().log(Status.INFO, "Enter the org name and click save");
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.enterLastName(lastName);
		cncp.supportStartEndDates(startDate, endDate);
		cncp.clickSaveButton();

		// validate the dates
		UtilityClassObject.getTest().log(Status.INFO, "validate the dates");
		ContactsInfoPage cip = new ContactsInfoPage(driver);

		String actStartDate = cip.actStartDate();
		String actEndDate = cip.actEndDate();
		Assert.assertEquals(actStartDate, startDate);
		Assert.assertEquals(actEndDate, endDate);
		UtilityClassObject.getTest().log(Status.PASS, "is vaildated====PASS");

	}

}
