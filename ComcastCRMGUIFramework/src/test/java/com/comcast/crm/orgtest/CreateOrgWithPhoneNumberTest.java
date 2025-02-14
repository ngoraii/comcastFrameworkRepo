package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertiesUtility;
import com.comcast.crm.generic.javautility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrgWithPhoneNumberTest {
	@Test
	
	public void createOrgWithPhoneNumber()throws Throwable {
		
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
		String data = eLib.getDataFromExcel("org", 7, 2);
		String phoneNum = eLib.getDataFromExcel("org", 7, 3);
		
		//adding a random number to the orgName to make it unique
		String orgName=data+jLib.getRandomNumber();
		
		//choosing and launching browser
		WebDriver driver=wLib.browserSelect(browser);
		wLib.waitForPageToLoad(driver);
		driver.get(url);
		
		//Step 1: Login
		driver.findElement(By.name("user_name")).sendKeys(un);
		driver.findElement(By.name("user_password")).sendKeys(pwd+Keys.ENTER);
		
		//Step 2: navigate to the organizations module
		driver.findElement(By.xpath("(//a[.='Organizations'])[1]")).click();
		
		//Step 3: Create on Create organization button
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//Step 4: Enter the org name & phone number and click save to create
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		driver.findElement(By.id("phone")).sendKeys(phoneNum);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		//validate phone number
		String phone = driver.findElement(By.id("dtlview_Phone")).getText();
		if(phone.equals(phoneNum)) {
			System.out.println(phoneNum+" is vaildated====PASS");
		}
		else {
			System.out.println(phoneNum+"is not present====FAIL");
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
