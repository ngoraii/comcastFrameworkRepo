package com.comcast.crm.orgtest;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.comcast.crm.baseClass.BaseClass;
import com.comcast.crm.baseClass.UtilityClassObject;
import com.comcast.crm.objectreposatory.CreateNewOrganizationPage;
import com.comcast.crm.objectreposatory.HomePage;
import com.comcast.crm.objectreposatory.OrganizationInfoPage;
import com.comcast.crm.objectreposatory.OrganizationsPage;
@Listeners(com.comcast.crm.generic.listenerutility.ListenerImp.class)
public class OrgTest extends BaseClass {
	 

	@Test
	public void validateOrgname() throws EncryptedDocumentException, IOException {
		// fetching data for script from excel
		UtilityClassObject.getTest().log(Status.INFO, "fetching data for script from excel");
		String data = eLib.getDataFromExcel("org", 1, 2);

		// adding a random number to the orgName to make it unique
		UtilityClassObject.getTest().log(Status.INFO, "adding a random number to the orgName");
		String orgName = data + jLib.getRandomNumber();

		// Step 2: navigate to the organizations module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to the organizations");
		HomePage hp = new HomePage(driver);
		hp.clickOrgLink();

		// Step 3: Click on Create organization button
		UtilityClassObject.getTest().log(Status.INFO, "Click on Create organization");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickPlusIcon();

		// Step 4: Enter the org name and click save to create
		UtilityClassObject.getTest().log(Status.INFO, "Enter the org name and click save");
		CreateNewOrganizationPage cop = new CreateNewOrganizationPage(driver);
		cop.enterOrgName(orgName);
		cop.clickSaveButton();

		// validate orgName in the header
		UtilityClassObject.getTest().log(Status.INFO, "validate orgName in the header");
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headerText = oip.getHeaderText();

		if (headerText.contains(orgName)) {
			UtilityClassObject.getTest().log(Status.PASS, "is vaildated====PASS");
		} else {
			UtilityClassObject.getTest().log(Status.FAIL, "is not present====FAIL");
			Assert.fail();
		}
	}

	@Test

	public void deleteOrgName() throws IOException, InterruptedException {
		// fetching data for script from excel
		UtilityClassObject.getTest().log(Status.INFO, "fetching data for script from excel");
		String data = eLib.getDataFromExcel("org", 1, 2);
		String searchForOrg = eLib.getDataFromExcel("org", 10, 3);

		// adding a random number to the orgName to make it unique
		UtilityClassObject.getTest().log(Status.INFO, "adding a random number to the orgName");
		String orgName = data + jLib.getRandomNumber();

		// Step 2: navigate to the organizations module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to the organizations");
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

		// goto organizations page
		UtilityClassObject.getTest().log(Status.INFO, "goto organizations page");
		Thread.sleep(1000);
		hp.getOrgLink().click();

		// search for the org name
		UtilityClassObject.getTest().log(Status.INFO, "search for the org name");
		op.searchFor(orgName, searchForOrg);
		Thread.sleep(2000);
		// delete the org name
		UtilityClassObject.getTest().log(Status.INFO, "delete the org name");
		driver.findElement(By.xpath("//a[.='" + orgName + "']/../following-sibling::td[5]/a[2]")).click();
		Thread.sleep(1000);
		wLib.switchToAlertAndAccept(driver);
		Thread.sleep(1000);
		UtilityClassObject.getTest().log(Status.PASS, "is deleted====PASS");
		// verify its deleted

	}

	@Test

	public void validateOrgIndustryType()
			throws FileNotFoundException, IOException, ParseException, InterruptedException {

		// fetching data for script from excel
		UtilityClassObject.getTest().log(Status.INFO, "fetching data for script from excel");
		String data = eLib.getDataFromExcel("org", 1, 2);
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		// adding a random number to the orgName to make it unique
		UtilityClassObject.getTest().log(Status.INFO, "adding a random number to the orgName");
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

		// Select industry type
		UtilityClassObject.getTest().log(Status.INFO, "Select industry type");
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);

		WebElement dp = cnop.getIndustryDropdown();
		WebElement dp2 = cnop.getTypeDropdown();
		Select s = new Select(dp);
		s.selectByVisibleText(industry);

		Select s2 = new Select(dp2);
		s2.selectByVisibleText(type);

		// validate orgName in the header
		UtilityClassObject.getTest().log(Status.INFO, "validate orgName in the header");
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actIndustry = oip.getIndustry();
		String actType = oip.getType();
		if (actIndustry.contains(industry) && actType.contains(type)) {
			UtilityClassObject.getTest().log(Status.PASS, industry + "\t" + type + "is vaildated====PASS");

		} else {
			UtilityClassObject.getTest().log(Status.FAIL, industry + "Industry Type Validation====FAIL");
			Assert.fail();
		}

	}

	@Test

	public void createOrgWithPhoneNumber() throws Throwable {

		// fetching data for script from excel
		UtilityClassObject.getTest().log(Status.INFO, "fetching data for script from excel");
		String data = eLib.getDataFromExcel("org", 7, 2);
		String phoneNum = eLib.getDataFromExcel("org", 7, 3);
		System.out.println(phoneNum);

		// adding a random number to the orgName to make it unique
		UtilityClassObject.getTest().log(Status.INFO, "adding a random number to the orgName");
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
		cop.enterPhoneNum(phoneNum);
		cop.clickSaveButton();

		// validate phone number
		UtilityClassObject.getTest().log(Status.INFO, "validate phone number");
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String phone = oip.getPhoneNumber();
		Assert.assertEquals(phone, phoneNum);
		UtilityClassObject.getTest().log(Status.PASS, "is vaildated====PASS");

	}

}
