package com.blazedemo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtil {
	static WebDriver driver = null;

	public static WebDriver getDriverAccordingToBrowser(String browserName) {
		
		if (driver == null) {
			if (browserName.contentEquals("Chrome")) {
				driver = new ChromeDriver();
			}

			else if (browserName.contentEquals("Firefox")) {
				driver = new FirefoxDriver();
			} else if (browserName.contentEquals("Edge")) {
				driver = new EdgeDriver();
			} else
				System.out.println("Please enter proper browser name");
		}
		return driver;

	}

	public static void closeDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
