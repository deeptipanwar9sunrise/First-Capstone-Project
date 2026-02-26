package com.blazedemo.pages;

//Choose a flight
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.blazedemo.utils.WaitUtils;

public class ReservePage {
	WebDriver driver;
	WaitUtils waitUtils;

	public ReservePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitUtils = new WaitUtils(driver);

		waitUtils.waitForUrlContains("reserve.php");
		waitUtils.waitForElementVisibility(flightTable);
	}

	// elements
	@FindBy(xpath = "//table[@class='table']")
	WebElement flightTable;
	@FindBy(xpath = "//tr[1]/td[1]")
	WebElement choosethisFlightButton;

	// actions

	public String getURL() {

		return driver.getCurrentUrl();
	}

	public boolean getTableDisplayed() {

		return flightTable.isDisplayed();

	}

	public WebElement choosethisFlightButton() {
		return choosethisFlightButton;
	}

}
