package com.comcast.crm.contacttest;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertiesUtility;
import com.comcast.crm.generic.javautility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrgTest {
	@Test
	public void createContactWithOrgTest() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		
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
					String data = eLib.getDataFromExcel("contact", 1, 2);
					
					//adding a random number to the orgName to make it unique
					String lastName=data+jLib.getRandomNumber();
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
					
					//Step 3: Click on Create organization button
					driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
					
					//Step 4: Enter the org name and click save to create
					driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
					driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
					
					//Step 5: navigate to the contacts module
					Thread.sleep(1000);
					driver.findElement(By.xpath("//a[contains(@href,'Contacts&action') and .='Contacts']")).click();
					
					//Step 6: Click on Create contacts button
					driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
					
					//Step 4: Enter the org name and click save to create
					driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
					driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
					wLib.switchToTabByURL(driver, "Accounts&action");
					driver.findElement(By.id("search_txt")).sendKeys(orgName+Keys.ENTER);
					driver.findElement(By.xpath("//a[.='"+orgName+"']")).click();
					wLib.switchToTabByURL(driver, "Contacts&action");
					driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
					
					//validate orgName in the header
					String actOrgName = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/a")).getText();
					if(actOrgName.contains(orgName)) {
						System.out.println(orgName+"is vaildated====PASS");
						}
					else {
						System.out.println(orgName+"is not present====FAIL");
						}
					
					//Step 5: Logout
					Actions act=new Actions(driver);
					Thread.sleep(2000);
					act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).build().perform();
					Thread.sleep(1000);
					driver.findElement(By.linkText("Sign Out")).click();
					driver.quit();
					
	}


}
