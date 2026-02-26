package com.blazedemo.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WaitUtils {
	WebDriver driver;
	WebDriverWait wait;
	private static final Logger log = LogManager.getLogger("WaitUtils.class");

	public WaitUtils(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public Boolean waitForElementVisibility(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
		return false;

	}

	public boolean waitForUrlContains(String partialUrl) {
		try {
			wait.until(ExpectedConditions.urlContains(partialUrl));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}