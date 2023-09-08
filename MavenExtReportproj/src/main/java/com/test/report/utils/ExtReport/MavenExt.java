package com.test.report.utils.ExtReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MavenExt {

	ExtentReports report;
	ExtentTest logger;
	WebDriver d;

	@Test

	public void verifyBlogTitle()
	{
	report=new ExtentReports("D:\\ExtentReportFolder\\ReportOfRun.html");
		logger=report.startTest("This is the title of the report");

		System.setProperty("webdriver.chrome.driver","C:\\SeleniumJar\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		
		// d= new ChromeDriver();
		//WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
	    opt.addArguments("--remote-allow-origins=*");
		d= new ChromeDriver(opt);
	logger.log(LogStatus.INFO, "Browser Started");
	d.get("https://parabank.parasoft.com/");	

	logger.log(LogStatus.INFO, "Site Launch Successfully");

	String title=d.getTitle();

	Assert.assertTrue(title.contains("Google"));
	logger.log(LogStatus.INFO, "title checking Done");
	}

	@AfterMethod

	public void tearDown(ITestResult result)
	{
	if (result.getStatus()==ITestResult.FAILURE)
	{
		logger.log(LogStatus.FAIL, "Title Verification Failed");
	}
	if(result.getStatus()==ITestResult.SUCCESS)
	{
		logger.log(LogStatus.PASS, "Title Verification Ok");	
	}

	report.endTest(logger);
	report.flush();

	d.get("D:\\ExtentReportFolder\\ReportOfRun.html");
	}
	}


