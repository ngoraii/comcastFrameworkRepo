package com.comcast.crm.objectreposatory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class OrganizationsPage {
	WebDriverUtility wLib=new WebDriverUtility();
	WebDriver driver;
	public OrganizationsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement plusIcon;
	
	public WebElement getPlusIcon() {
		return plusIcon;
	}
	
	@FindBy(name="search_text")
	private WebElement searchBox;
	
	@FindBy(id="bas_searchfield")
	private WebElement searchInDropdown;
	
	@FindBy(name="submit")
	private WebElement searhNowButton;
	
	public WebElement getSearhNowButton() {
		return searhNowButton;
	}
	
	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getSearchInDropdown() {
		return searchInDropdown;
	}



	public void clickPlusIcon() {
		plusIcon.click();
	}
	

	public void searchFor(String searchFor, String in) {
		searchBox.sendKeys(searchFor);
		wLib.dropdownSelectByText(searchInDropdown, in);
		searhNowButton.click();
	}
}
