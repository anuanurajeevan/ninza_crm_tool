package com.ninza.crm.listnerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
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

import com.ninza.crm.baseClass.BaseClass;

public class ListnerImplementation implements ITestListener, ISuiteListener{

	
	ExtentReports report;
	ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		Date d=new Date();
		String date = d.toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark=new ExtentSparkReporter("./ExtentReports/Report_"+date+".html");
		spark.config().setDocumentTitle("CRM Report");
		spark.config().setReportName("Ninza CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Browser", "Edge");
		report.setSystemInfo("OS","Windows 10");
		
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test = report.createTest(methodName);
		test.log(Status.INFO, methodName+" Execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS,methodName+" Execution success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		test.log(Status.FAIL, methodName+" Execution Failed");
		Date d=new Date();
		String date = d.toString().replace(" ", "_").replace(":", "_");
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		String src=ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src, methodName+"_"+date);
		// TODO Auto-generated method stub
		
		
//		String methodName = result.getMethod().getMethodName();
//		System.out.println(methodName+" Execution Failed");
//		Date d = new Date();
//		String date = d.toString().replace(" ", "_").replace(":", "_");		
//		TakesScreenshot ts =(TakesScreenshot)BaseClass.sdriver;
//		File src = ts.getScreenshotAs(OutputType.FILE);
//		File dest = new File(".//Screenshots//"+methodName+date+".png");
//		try {
//			FileHandler.copy(src, dest);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		


	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, methodName+" Execution Failed");
	}

	

	
	

}
