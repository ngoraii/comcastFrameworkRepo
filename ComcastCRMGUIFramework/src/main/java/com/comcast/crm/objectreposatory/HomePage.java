package com.comcast.crm.objectreposatory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "Organizations")
		private WebElement orgLink;
		
	@FindBy(linkText = "Contacts")
	private	WebElement contactsLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement userIcon;
	
	@FindBy(xpath = "//a[.='Sign Out']")
	private WebElement signoutButton;

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getUserIcon() {
		return userIcon;
	}

	public WebElement getSignoutButton() {
		return signoutButton;
	}
	public void clickOrgLink() {
		orgLink.click();
	}
	public void clickContactsLink() {
		contactsLink.click();
	}
	
	public void signOut() {
		Actions act=new Actions(driver);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			}
		act.moveToElement(userIcon).perform();
		signoutButton.click();
	}
}
