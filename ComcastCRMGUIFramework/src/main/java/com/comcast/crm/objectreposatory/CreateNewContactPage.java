package com.comcast.crm.objectreposatory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {
	
	WebDriver driver;
	public CreateNewContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastnameTextField;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveButton;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement selectOrgButton;
	
	@FindBy(xpath="//input[@name='support_start_date']")
	private WebElement supportStartDate;
	
	@FindBy(xpath="//input[@name='support_end_date']")
	private WebElement supportEndDate;

	public WebElement getLastnameTextField() {
		return lastnameTextField;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getSelectOrgButton() {
		return selectOrgButton;
	}

	public WebElement getSupportStartDate() {
		return supportStartDate;
	}

	public WebElement getSupportEndDate() {
		return supportEndDate;
	}
	
	public void enterLastName(String lastname) {
		lastnameTextField.sendKeys(lastname);
	}
	
	public void clickSelectOrgBttn() {
		selectOrgButton.click();
	}
	
	public void supportStartEndDates(String startDate, String endDate) {
		supportStartDate.clear();
		supportStartDate.sendKeys(startDate);
		supportEndDate.clear();
		supportEndDate.sendKeys(endDate);
	}
	
	public void clickSaveButton() {
		saveButton.click();
	}

}
