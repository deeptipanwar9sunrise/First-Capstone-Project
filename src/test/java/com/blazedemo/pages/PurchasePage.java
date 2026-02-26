package com.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.blazedemo.utils.WaitUtils;

public class PurchasePage {

	WebDriver driver;
	WaitUtils waitUtils;

	public PurchasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitUtils = new WaitUtils(driver);
		waitUtils.waitForUrlContains("purchase.php");
	}

	// elements
	@FindBy(xpath = "//input[@id='inputName']")
	WebElement userFullName;
	@FindBy(xpath = "//input[@id='address']")
	WebElement address;
	@FindBy(xpath = "//input[@id='city']")
	WebElement city;
	@FindBy(xpath = "//input[@id='state']")
	WebElement state;
	@FindBy(xpath = "//input[@id='zipCode']")
	WebElement zipCode;
	@FindBy(xpath = "//select[@id='cardType']")
	WebElement cardTypeElement;
	@FindBy(xpath = "//input[@id='creditCardNumber']")
	WebElement creditCardNumber;
	@FindBy(xpath = "//input[@id='nameOnCard']")
	WebElement nameOnCard;
	@FindBy(xpath = "//input[@value='Purchase Flight']")
	WebElement purchaseFlightButton;

	// actions

	public String getURL() {

		return driver.getCurrentUrl();
	}

	public void getUserFullName(String name) {

		userFullName.sendKeys(name);
	}

	public void getAddress(String addr) {

		address.sendKeys(addr);
	}

	public void getCity(String cityaddr) {
		city.sendKeys(cityaddr);
	}

	public void getState(String stateaddr) {
		state.sendKeys(stateaddr);
	}

	public void getZipCode(String zipcodearea) {
		zipCode.sendKeys(zipcodearea);
	}

	public void selectCardType(WebElement element, String cardty) {
		Boolean flag = waitUtils.waitForElementVisibility(element);
		if (flag = true) {
			Select cardTypeDropdown = new Select(element);
			cardTypeDropdown.selectByValue(cardty);
		}
	}

	public void getCardType(String ctype) {

	selectCardType(cardTypeElement,ctype);
	}

	public void getCreditCardNumber(String credcardno) {
		creditCardNumber.sendKeys(credcardno);
	}

	public void getnameOnCard(String nmcard) {
		nameOnCard.sendKeys(nmcard);
	}

	public void getPurchaseFlightButton() {
		purchaseFlightButton.click();
	}

}