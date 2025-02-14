package com.comcast.crm.orgtest;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertiesUtility;
import com.comcast.crm.generic.javautility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class createOrgWithTypeTest {
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
				String industry = eLib.getDataFromExcel("org", 4, 3);
				String type = eLib.getDataFromExcel("org", 4, 4);
				
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
				
				//Step 4: Enter the org name and click save to create
				driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
				
				//Select industry type
				WebElement dp = driver.findElement(By.xpath("//select[@name='industry']"));
				WebElement dp2 = driver.findElement(By.xpath("//select[@name='accounttype']"));
				Select s=new Select(dp);
				s.selectByVisibleText(industry);
				
				Select s2=new Select(dp2);
				s2.selectByVisibleText(type);
				
				//validate orgType in the header
				String actIndustry = driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
				String actType = driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();
				if(actIndustry.contains(industry) && actType.contains(type)) {
					System.out.println(industry+"\t"+type+ "is vaildated====PASS");
				}
				else {
					System.out.println("Industry Type Validation====FAIL");
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
