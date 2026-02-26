package com.blazedemo.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.blazedemo.pages.ConfirmationPage;
import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;

//@Listeners(MyListener.class)
public class FlightBookingTest extends BaseClass {
	public static final Logger log = LogManager.getLogger(FlightBookingTest.class);

	// Automate booking a flight from Boston to New York.
	// Assert that the confirmation page is displayed.
	@Test(priority = 3, groups = "functional")
	public void TC03_end2endFlow() {
		// verify home page header, title, URL
		log.info("verify home page header, title, URL");
		HomePage hp = new HomePage(driver);
		String actHeaderText = hp.getHeaderText();
		Assert.assertEquals(actHeaderText, "Welcome to the Simple Travel Agency!");
		String actTitle = hp.getTitle();
		Assert.assertEquals(actTitle.trim(), "BlazeDemo");
		String actURL = hp.getURL();
		Assert.assertEquals(actURL, "https://blazedemo.com/");
		// choose your departure city as Boston
		log.info("Choose your departure city as Boston");
		hp.chooseDepartureCity("Boston");
		// choose your destination city
		log.info("Choose your destination city as New York");
		hp.chooseDestinationCity("New York");
		// click on find flights button
		log.info("Click on find flights button");
		hp.findFlightsButton().click();
		// verify the list of flights from Boston to New York, verify that user is
		// navigated to reserve page URL
		log.info("Verify user is navigated to reserve page URL");
		ReservePage rp = new ReservePage(driver);
		String actualURLReservePage = rp.getURL();
		Assert.assertEquals(actualURLReservePage, "https://blazedemo.com/reserve.php");
		System.out.println(rp.getTableDisplayed());
		// click on choose this flight button for your choice
		log.info("Click on choose this flight button for your choice");
		rp.choosethisFlightButton().click();
		// verify that user navigates to purchase page
		log.info("Verify that user navigates to purchase page");
		PurchasePage pp = new PurchasePage(driver);
		String actualURLPurchasePage = pp.getURL();
		Assert.assertEquals(actualURLPurchasePage, "https://blazedemo.com/purchase.php");
		// fill in the information
		log.info("Fill in the information for purchase");
		pp.getUserFullName("Deepti Panwar");
		pp.getAddress("Sector14, Hiran Magri");
		pp.getCity("Udaipur");
		pp.getState("Rajasthan");
		pp.getZipCode("313001");
		pp.getCardType("amex");
		pp.getCreditCardNumber("845883457884");
		pp.getnameOnCard("Deepti Panwar");
		// click on purchase flight button
		log.info("Click on purchase flight button");
		pp.getPurchaseFlightButton();
		// verify that user navigates to confirmation page
		ConfirmationPage cp = new ConfirmationPage(driver);
		String actualURLConfirmationPage = cp.getURL();
		Assert.assertEquals(actualURLConfirmationPage, "https://blazedemo.com/confirmation.php");
		// verify that confirmation message is displayed
		log.info("Verify that confirmation message page is displayed");
		String actHeaderTextConfirm = cp.getHeaderText();
		Assert.assertEquals(actHeaderTextConfirm, "Thank you for your purchase today!");
	}

	@Test(priority = 1, groups = "smoke")
	public void TC01_verifyHomePageLoads() {

		log.info("TC01 - Verify homepage loads");
		System.out.println("BeforeMethod Driver = " + driver);
		HomePage hp = new HomePage(driver);

		Assert.assertEquals(hp.getHeaderText(), "Welcome to the Simple Travel Agency!");

		Assert.assertEquals(hp.getTitle().trim(), "BlazeDemo");

		Assert.assertTrue(hp.isDepartureDropdownDisplayed());
		Assert.assertTrue(hp.isDestinationDropdownDisplayed());
	}

	@Test(priority = 2, groups = "functional")
	public void TC02_searchFlightsValidCities() {

		log.info("TC02 - Search flights with valid cities");

		HomePage hp = new HomePage(driver);

		hp.chooseDepartureCity("Boston");
		hp.chooseDestinationCity("New York");
		hp.findFlightsButton().click();

		ReservePage rp = new ReservePage(driver);

		Assert.assertEquals(rp.getURL(), "https://blazedemo.com/reserve.php");

		Assert.assertTrue(rp.getTableDisplayed());
	}

	@DataProvider(name = "bookingData")
	public Object[][] bookingData() {
		return new Object[][] { { "Boston", "New York" }, { "Philadelphia", "London" }, { "Paris", "Rome" } };
	}

	@Test(priority = 4, dataProvider = "bookingData", groups="data-driven")
	public void TC04_multipleBookings(String from, String to) {

	    log.info("TC04 - Data driven booking");

	    HomePage hp = new HomePage(driver);

	    hp.chooseDepartureCity(from);
	    hp.chooseDestinationCity(to);
	    hp.findFlightsButton().click();

	    ReservePage rp = new ReservePage(driver);

	    Assert.assertTrue(rp.getTableDisplayed());

	    driver.navigate().back(); // reset for next iteration
	}

@Test(priority = 5, groups="negative")
public void TC05_blankCreditCard() {

    log.info("TC05 - Blank credit card");

    HomePage hp = new HomePage(driver);
    hp.chooseDepartureCity("Boston");
    hp.chooseDestinationCity("New York");
    hp.findFlightsButton().click();

    ReservePage rp = new ReservePage(driver);
    rp.choosethisFlightButton().click();

    PurchasePage pp = new PurchasePage(driver);

    pp.getUserFullName("Test User");
    pp.getCreditCardNumber("");  // blank
    pp.getPurchaseFlightButton();

    Assert.assertTrue(driver.getCurrentUrl()
            .contains("purchase.php"));
}

@Test(priority = 6, groups="negative")
public void TC06_invalidCreditCardCharacters() {

    log.info("TC06 - Invalid credit card characters");

    HomePage hp = new HomePage(driver);
    hp.chooseDepartureCity("Boston");
    hp.chooseDestinationCity("New York");
    hp.findFlightsButton().click();

    ReservePage rp = new ReservePage(driver);
    rp.choosethisFlightButton().click();

    PurchasePage pp = new PurchasePage(driver);

    pp.getUserFullName("Test User");
    pp.getCreditCardNumber("ABC@123$%");
    pp.getPurchaseFlightButton();

    Assert.assertTrue(driver.getCurrentUrl()
            .contains("purchase.php"));
}

@Test(priority = 7, groups="negative")
public void TC07_sameDepartureAndDestination() {

    log.info("TC07 - Same departure and destination city");

    HomePage hp = new HomePage(driver);

    hp.chooseDepartureCity("Boston");
    hp.chooseDestinationCity("Boston");
    hp.findFlightsButton().click();

    ReservePage rp = new ReservePage(driver);

    Assert.assertTrue(rp.getTableDisplayed());
}
}