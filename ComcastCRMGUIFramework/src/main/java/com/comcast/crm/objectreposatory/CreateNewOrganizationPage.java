package com.comcast.crm.objectreposatory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	private WebElement orgNameField;
	
	@FindBy(name="industry")
	private WebElement industryDropdown;
	
	@FindBy(name="accounttype")
	private WebElement typeDropdown;
	
	@FindBy(id="phone")
	private WebElement phoneNumberField;
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveButton;
	
	public WebElement getSaveButton() {
		return saveButton;
	}
	
	public WebElement getOrgNameField() {
		return orgNameField;
	}

	public WebElement getIndustryDropdown() {
		return industryDropdown;
	}

	public WebElement getTypeDropdown() {
		return typeDropdown;
	}

	public WebElement getPhoneNumberField() {
		return phoneNumberField;
	}
	
	public void enterOrgName(String orgName) {
		orgNameField.sendKeys(orgName);
	}
	
	public void enterPhoneNum(String phoneNum) {
		phoneNumberField.sendKeys(phoneNum);
	}
	

	public void clickSaveButton() {
		saveButton.click();
	}
	
	public void selectIndustryTypeDp(String industry,String type) {
		Select s= new Select(industryDropdown);
		s.selectByVisibleText(industry);
		
		Select s2= new Select(typeDropdown);
		s2.selectByVisibleText(type);
	}


}
