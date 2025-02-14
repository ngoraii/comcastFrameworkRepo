package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	public void waitForElementVisibility(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	
	public void switchToTabByURL(WebDriver driver, String partialURL) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);
			String actURL = driver.getCurrentUrl();
			if(actURL.contains(partialURL)) {
				break;
			}
		}
	}
	
	
	
	public void switchToTabByTitle(WebDriver driver, String partialTitle) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);
			String actTitle = driver.getTitle();
			if(actTitle.contains(partialTitle)) {
				break;
			}
		}
	}
	
	public void switchToFrameByIndex(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	
	public void switchToFrameByNameId(WebDriver driver, String nameId) {
		driver.switchTo().frame(nameId);
	}
	
	public void switchToFrameByWebElement(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	
	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertAndDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public void dropdownSelectByText(WebElement element, String text) {
		Select s=new Select(element);
		s.selectByVisibleText(text);
	}
	
	public void dropdownSelectByIndex(WebElement element, int index) {
		Select s=new Select(element);
		s.selectByIndex(index);
	}
	
	public void mouseHoverOnElement(WebDriver driver, WebElement element) {
		Actions act=new Actions(driver);
		act.moveToElement(element).build().perform();
	}
	
	public void doubleClickOnElement(WebDriver driver, WebElement element) {
		Actions act=new Actions(driver);
		act.doubleClick(element).build().perform();
	}
	
	public void rightClickOnElement(WebDriver driver, WebElement element) {
		Actions act=new Actions(driver);
		act.contextClick(element);
	}
	public WebDriver browserSelect(String browser) {
		WebDriver driver;
		if(browser.contains("chrome"))
			driver=new ChromeDriver();
		else if(browser.contains("firefox"))
			driver=new FirefoxDriver();
		else if(browser.contains("edge"))
			driver=new EdgeDriver();
		else 
			driver=new ChromeDriver();
		return driver;
			
	}

}
