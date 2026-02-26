package com.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.blazedemo.utils.WaitUtils;

public class ConfirmationPage {
//Validate success message. 
	WebDriver driver;
	WaitUtils waitUtils;

	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitUtils = new WaitUtils(driver);
		waitUtils.waitForUrlContains("confirmation.php");
	}

	// elements
	@FindBy(xpath = "//h1[contains(text(),'Thank you for your purchase today!')]")
	WebElement confirmationMessage;

	// actions
	public String getHeaderText() {
		String actHeaderText = "";
		Boolean flag = waitUtils.waitForElementVisibility(confirmationMessage);

		if (flag == true) {
			actHeaderText = confirmationMessage.getText();
		}
		return actHeaderText;
	}

	public String getURL() {

		return driver.getCurrentUrl();
	}

}
