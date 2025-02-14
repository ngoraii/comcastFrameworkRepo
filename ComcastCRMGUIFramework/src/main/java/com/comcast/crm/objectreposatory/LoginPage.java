package com.comcast.crm.objectreposatory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage //Rule-1 create page specific POM Class 
{   
	
	WebDriver driver;
	
	
	//Rule-2 Object Identification
	@FindBy(name="user_name")
	private WebElement usernameField;
	
	@FindBy(name="user_password")
	private WebElement passwordField;
	
	@FindBy(id="submitButton")
	private WebElement submitButton;

	
	
	//Rule-3 Object Initialization [done in the Runner class or the Script]
		public LoginPage(WebDriver driver) {
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}
		
	
	//Rule-4 Object Encapsulation [All WebelElement variables declared private and giving access through the getters method]
		public WebElement getUsernameField() {
			return usernameField;
		}

		public WebElement getPasswordField() {
			return passwordField;
		}

		public WebElement getSubmitButton() {
			return submitButton;
		}
		
		
	//Rule-5 Object utilization or provide action
	public void loginToApp(String un, String pwd) {
	driver.manage().window().maximize();
	usernameField.sendKeys(un);
	passwordField.sendKeys(pwd);
	submitButton.click();
	}
}
