package com.comcast.crm.objectreposatory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsInfoPage {
	WebDriver driver;

	public ContactsInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "dvHeaderText")
	private WebElement header;

	@FindBy(xpath = "//td[@id='mouseArea_Organization Name']/a")
	private WebElement orgNameField;

	@FindBy(xpath = "//span[@id='dtlview_Support Start Date']")
	private WebElement actStartDateField;

	@FindBy(xpath = "//span[@id='dtlview_Support End Date']")
	private WebElement actEndDateField;

	public WebElement getHeader() {
		return header;
	}

	public WebElement getOrgNameField() {
		return orgNameField;
	}

	public WebElement getActStartDateField() {
		return actStartDateField;
	}

	public WebElement getActEndDateField() {
		return actEndDateField;
	}

	public String headerText() {
		String headerText = header.getText();
		return headerText;
	}

	public String actStartDate() {
		String actStartDate = actStartDateField.getText();
		return actStartDate;
	}

	public String actOrgName() {
		String actOrgName = orgNameField.getText();
		return actOrgName;
	}

	public String actEndDate() {
		String actEndDate = actEndDateField.getText();
		return actEndDate;
	}

}
