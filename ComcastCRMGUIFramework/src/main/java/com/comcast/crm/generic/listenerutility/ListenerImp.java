package com.comcast.crm.generic.listenerutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.baseClass.BaseClass;
import com.comcast.crm.baseClass.UtilityClassObject;

public class ListenerImp implements ITestListener, ISuiteListener{

	public ExtentTest test;
	public ExtentReports report;
	@Override
	public void onStart(ISuite suite) {
		String time=new Date().toString().replace(":","_").replace(" ", "_");
		
		//Extentspark Report config
		ExtentSparkReporter spark=new ExtentSparkReporter("./advanceReports/report"+time+".html");
		spark.config().setDocumentTitle("Test Suite Resuts");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//add env info and create test
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Winows11");
		report.setSystemInfo("Browser", "ChromeBrowser");		
		
	}

	@Override
	public void onFinish(ISuite suite) {
		//Extent Report backup
		
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO,"======"+result.getMethod().getMethodName()+" STARTED======");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS,"======"+result.getMethod().getMethodName()+" EXECUTED======");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		TakesScreenshot ts=(TakesScreenshot) BaseClass.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		String time=new Date().toString().replace(":","_").replace(" ", "_");
		
		test.addScreenCaptureFromBase64String(src, result.getMethod().getMethodName()+time);
		test.log(Status.FAIL,"======"+result.getMethod().getMethodName()+" FAILED======");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		test.log(Status.INFO,"======"+result.getMethod().getMethodName()+" SKIPPED======");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	

}
