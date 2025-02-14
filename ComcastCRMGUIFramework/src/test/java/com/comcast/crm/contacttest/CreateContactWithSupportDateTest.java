package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertiesUtility;
import com.comcast.crm.generic.javautility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectreposatory.LoginPage;

public class CreateContactWithSupportDateTest {
	@Test
	public void createContactWithSupportDateTest() throws Throwable {
		
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
		String data = eLib.getDataFromExcel("contact", 4, 2);
		
		//getting todays date as support start date
		String startDate = jLib.getSystemDateYYYYMMDD();
		
		//getting 30 days after date as support end date
		String endDate = jLib.getRequiredDateYYYYMMDD(30);
		
		//adding a random number to the orgName to make it unique
		String lastName=data+jLib.getRandomNumber();
		
		//choosing and launching browser
		WebDriver driver=wLib.browserSelect(browser);
		wLib.waitForPageToLoad(driver);
		driver.get(url);
		
		//Step 1: Login
		LoginPage login= new LoginPage(driver);
		login.loginToApp(un, pwd);
		
		//Step 2: navigate to the organizations module
		driver.findElement(By.xpath("//a[contains(@href,'Contacts&action') and .='Contacts']")).click();
		
		//Step 3: Create on Create organization button
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Step 4: Enter the org name and click save to create
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
		WebElement startEle = driver.findElement(By.xpath("//input[@name='support_start_date']"));
		startEle.clear();
		startEle.sendKeys(startDate);
		WebElement endEle = driver.findElement(By.xpath("//input[@name='support_end_date']"));
		endEle.clear();
		endEle.sendKeys(endDate);
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		//validate the dates
		String actStartDate = driver.findElement(By.xpath("//span[@id='dtlview_Support Start Date']")).getText();
		String actEndDate = driver.findElement(By.xpath("//span[@id='dtlview_Support End Date']")).getText();
		if(actStartDate.equals(startDate) && actEndDate.equals(endDate)) {
			System.out.println("Start Date "+startDate+" and End Date "+endDate+" is vaildated====PASS");
			
		}
		else {
			System.out.println("Given startdate "+startDate +" and enddate "+endDate+" and Actual startdate "+actStartDate+" and enddate "+actEndDate+" are not same====FAIL");
			Assert.fail();
		
		}
		
		//Step 5: Logout
		
			
		
		Actions act=new Actions(driver);
		Thread.sleep(3000);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	
	
	}

}
