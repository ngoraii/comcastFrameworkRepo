package com.comcast.crm.objectreposatory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "span.dvHeaderText") 
	private WebElement headerText;
	
	@FindBy(id="dtlview_Phone")
	private WebElement phoneNum;
	
	@FindBy(xpath="//span[@id='dtlview_Industry']")
	private WebElement industry;
	
	@FindBy(xpath="//span[@id='dtlview_Type']")
	private WebElement type;
	
	public String getIndustry() {
		return industry.getText();
	}


	public String getType() {
		return type.getText();
	}


	public WebElement getPhoneNum() {
		return phoneNum;
	}


	public String getHeaderText() {
		String text = headerText.getText();
		return text;
	}
	
	public String getPhoneNumber() {
		String phoneNumber=phoneNum.getText();
		return phoneNumber;
	}
}
