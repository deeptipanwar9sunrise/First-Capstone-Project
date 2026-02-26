package com.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

//import com.blazedemo.tests.BaseClass;
import com.blazedemo.utils.WaitUtils;

public class HomePage{
//Select cities, click “Find Flights”.
	WebDriver driver;
	WaitUtils waitUtils;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitUtils = new WaitUtils(driver);
	}

	// elements
	@FindBy(tagName = "h1")
	WebElement header;
//	@FindBy(xpath = "//h1[contains(text(),'Welcome to the Simple Travel Agency!')]")
//	WebElement headerElement;
	@FindBy(xpath = "//select[@name='fromPort']")
	WebElement departureCityElement;
	@FindBy(xpath = "//select[@name='toPort']")
	WebElement destinationCityElement;
	@FindBy(xpath = "//input[@value='Find Flights']")
	WebElement findFlightsButton;

// actions
	public String getHeaderText() {
		 String actHeaderText = "";
		 Boolean flag = waitUtils.waitForElementVisibility(header);

		if (flag == true) {
		 actHeaderText = header.getText();
		}
		return actHeaderText;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getURL() {
		return driver.getCurrentUrl();
	}

	private void selectCity(WebElement element, String cityName) {
		Boolean flag = waitUtils.waitForElementVisibility(element);
		if (flag == true) {
			Select CityDropdown = new Select(element);
			CityDropdown.selectByVisibleText(cityName);
		}

	}

	public void chooseDepartureCity(String city) {
		selectCity(departureCityElement, city);
	}

	public void chooseDestinationCity(String city) {
		selectCity(destinationCityElement, city);
	}

	public WebElement findFlightsButton() {
		return findFlightsButton;
	}

	public boolean isDepartureDropdownDisplayed() {
	    return departureCityElement.isDisplayed();
	}

	public boolean isDestinationDropdownDisplayed() {
	    return destinationCityElement.isDisplayed();
	}
}