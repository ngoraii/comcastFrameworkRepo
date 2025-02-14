package com.comcast.crm.objectreposatory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsSearchPopup {
	WebDriver driver;
	public OrganizationsSearchPopup(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="search_txt")
	private WebElement searchField;
	
	public WebElement getSearchField() {
		return searchField;
	}
	
	public void searchOrgInPopup(String orgName) {
		searchField.sendKeys(orgName + Keys.ENTER);
		driver.findElement(By.xpath("//a[.='" + orgName + "']")).click();
		
	}
}
