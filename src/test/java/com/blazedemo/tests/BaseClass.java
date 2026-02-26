package com.blazedemo.tests;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.blazedemo.utils.DriverUtil;

public abstract class BaseClass {
	protected  WebDriver driver;
	public static final Logger log = LogManager.getLogger(BaseClass.class);

	@BeforeMethod(alwaysRun=true)
	public void initDriver() {
		// open browser
		driver = DriverUtil.getDriverAccordingToBrowser("Chrome");
		System.out.println("BeforeMethod executing");
		//driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// open application URL
		log.info("Open application URL");
		driver.get("https://blazedemo.com");
	}

	@AfterMethod(alwaysRun=true)
	public void closeBrowser() {
		log.info("Closing browser");
		DriverUtil.closeDriver();
		//if(driver!=null) {
			//driver.quit();}
	}
}