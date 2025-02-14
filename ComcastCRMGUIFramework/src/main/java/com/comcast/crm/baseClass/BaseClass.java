package com.comcast.crm.baseClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertiesUtility;
import com.comcast.crm.generic.javautility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectreposatory.HomePage;
import com.comcast.crm.objectreposatory.LoginPage;

public class BaseClass {
	public WebDriver driver;
	public static WebDriver sdriver;
	public ExtentTest test;
	/* create object */
	public PropertiesUtility pLib = new PropertiesUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();

	@BeforeSuite
	public void configBS() {
		System.out.println("=======DB Connected, Report config========");
	}

	@BeforeClass
	public void configBC() throws IOException {
		System.out.println("Launch browser");

		// Using JSON File for common data input
		String browser = pLib.getDataFromPropertiesFile("browser");
		String url = pLib.getDataFromPropertiesFile("url");
		System.out.println("=========DDT using .json=============");

		// choosing and launching browser
		driver = wLib.browserSelect(browser);
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
		wLib.waitForPageToLoad(driver);
		driver.get(url);

	}

	@BeforeMethod
	public void configBM() throws IOException {
		System.out.println("login");
		
		// Login
		LoginPage lp = new LoginPage(driver);
		String un = pLib.getDataFromPropertiesFile("username");
		String pwd = pLib.getDataFromPropertiesFile("password");
		lp.loginToApp(un, pwd);

	}

	@AfterMethod
	public void configAM() {
		System.out.println("logout");
		HomePage hp = new HomePage(driver);
		hp.signOut();
	}

	@AfterClass
	public void configAC() {
		System.out.println("Close browser");
		driver.quit();
	}

	@AfterSuite
	public void configAS() {
		System.out.println("=======DB DisConnected, Report backup========");
	}

}
