package com.blazedemo.tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.blazedemo.utils.ExtentManager;
import com.blazedemo.utils.ScreenshotUtility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class MyListener implements ITestListener {

	private static final Logger log = LogManager.getLogger(MyListener.class);
	ExtentReports extent = ExtentManager.getInstance();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		log.info("Test Started: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("Test Passed: " + result.getName());

		WebDriver driver = ((BaseClass) result.getInstance()).driver;
		if (driver != null) {
			String screenshotPath = ScreenshotUtility.captureScreenshot(driver, result.getName());
			log.info("Screenshot saved at: " + screenshotPath);
		} else {
			log.error("Driver was null. Screenshot not taken.");
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		log.error("Test Failed: " + result.getName());
		WebDriver driver = ((BaseClass) result.getInstance()).driver;
		if (driver != null) {
			String screenshotPath = ScreenshotUtility.captureScreenshot(driver, result.getName());
			//try {
				//test.addScreenCaptureFromPath(screenshotPath);
			//} catch (Exception e) {
				//e.printStackTrace();
			//}
			log.error("Reason: " + result.getThrowable());
			log.info("Screenshot saved at: " + screenshotPath);
		} else {
			log.error("Driver was null. Screenshot not taken.");
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log.warn("Test Skipped: " + result.getName());
	}
	@Override
	public void onFinish(ITestContext context) {
	    extent.flush();   // 🔥 THIS CREATES THE FILE
	}
}
