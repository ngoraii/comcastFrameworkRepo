package practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brand, String product) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in");
		Thread.sleep(10000);
		//search for the product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brand, Keys.ENTER);
		
		//get the product price
		String pricePath="//span[.='"+product+"']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a/span/span/span[2]";
		String price = driver.findElement(By.xpath(pricePath)).getText();
		System.out.println("For the product : "+product+" the price is "+price);
		driver.quit();
	}



@DataProvider
public Object[][] getData() throws EncryptedDocumentException, IOException{
	ExcelUtility eLib=new ExcelUtility();
	int rowCount = eLib.getRowCount("amazon");
	Object[][] objArr=new Object[rowCount][2];
	for(int i=0;i<rowCount;i++) {
	objArr[i][0]=eLib.getDataFromExcel("amazon", i+1, 0);
	objArr[i][1]=eLib.getDataFromExcel("amazon", i+1, 1);
	}
	return objArr;
}
}