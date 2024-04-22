package testSuites;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.UpcomingBikesPage;
import testsBase.BaseClass;

public class TestSuite1_UpcomingHondaBikes extends BaseClass{

	public HomePage homePage;
	public UpcomingBikesPage upcomingBikesPage;

	
	//Test-Case-01
	@Test(priority = 1, testName = "TC01:Validating New-Bikes Dropdown", description = "Validating Dropdown of New-Bikes Section and Capturing the Options")
	public void validateNewBikesDropdown() throws IOException{
		logger.info("***TS01 - Starts***");
		logger.info("**TC01 - Starts**");
		try {
			//Initializing the Driver to HomePage Object
			homePage = new HomePage(driver);
			
			//Hovering to the New Bikes Option
			homePage.hoverToNewBikes();
			logger.info("Hovering to New Bikes Option");
			
			//Getting the Options in New Bikes Dropdown
			List<String> options = homePage.getOptionsInNewBikesDropdown();
			logger.info("Capturing the Options in New Bikes Dropdown");
			
			//Validating by size of list of options should be greater than zero
			logger.info("Validating the Options in New Bikes Dropdown");
			Assert.assertEquals(options.size()>0, true, "No Options in New Bikes Dropdown");
			
			//If Passed
			//Printing in Console
			System.out.println("New Bikes Dropdown Options:-");
			for(String option : options) {
				System.out.println("'" + option + "'");
			}
			//Writing in Excel
			homePage.writeOptionInNewBikesDropdownInExcel("TC01");
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC01 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		logger.info("**TC01 - Completes**");
	}

	
	//Test-Case-02
	@Test(priority = 2, testName = "TC02:Validating Upcoming-Bikes Button", description = "Validating whether Upcoming-Bikes Button is Clickable")
	public void validateUpcomingBikeIsClickable() {
		
		logger.info("**TC02 - Starts**");
		try {
			//Getting the Button - Upcoming Bikes
			WebElement upcomingBikesBtn = homePage.getUpcomingBikesButton();
			logger.info("Getting the Upcoming Bikes Button");
			
			//Checking the Button is Clickable
			logger.info("Validating Upcoming-Bikes Button is Clickable");
			Assert.assertEquals(isClickable(upcomingBikesBtn), true);
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC02 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		
		logger.info("**TC02 - Completes**");
	}

	
	//Test-Case-03
	@Test(priority = 3, testName = "TC03:Validating Upcoming-Bikes Link", description = "Validating whether Upcoming-Bikes-Page is getting opened after Clicking")
	public void validateUpcomingBikesButton() {
		logger.info("**TC03 - Starts**");
		try {
			//Clicking the Button - Upcoming Bikes
			homePage.clickUpcomingBikes();
			logger.info("Clicking the Upcoming-Bikes Button");
			
			//Validating the New Page URL after Clicking 
			logger.info("Validating the New Page URL whether the Button navigating to Upcoming Bikes Page");
			Assert.assertEquals(getCurrentURL(), propertyUtililty.getUpcomingBikesURL());
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC03 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		logger.info("**TC03 - Completes**");
	}

	
	//Test-Case-04
	@Test(priority = 4, testName = "TC04:Validating Manufacuter Dropdown", description = "Validating and Getting Dropdown of Manufacturing")
	public void validateManufacturerDropdown() throws IOException{
		logger.info("**TC04 - Starts**");
		try {
			//Initializing Upcoming Bikes Page with current driver
			upcomingBikesPage = new UpcomingBikesPage(driver);
			
			//Clicking Manufacturer Dropdown
			upcomingBikesPage.clickManufactureDropdown();
			
			//Getting the Options from Manufacturer Dropdown
			List<String> manufacturerOptions = upcomingBikesPage.getOptionsInManufacturerDropdown();
			logger.info("Capturing the Options in Manufactuer Dropdown");
			
			//Validating by size of list of options should be greater than zero 
			logger.info("Validating the Options in Manufacturer Dropdown");
			Assert.assertEquals(manufacturerOptions.size()>1, true, "No Options in Manufacturer Dropdown");
			
			//If passes,
			//Printing in Console
			System.out.println("Maufactures Dropdown Options:-");
			for(String option : manufacturerOptions) {
				System.out.println("'" + option + "'");
			}
			//Writing in Excel
			upcomingBikesPage.writeOptionInNewBikesDropdownInExcel("TC04");
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC04 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		logger.info("**TC04 - Completes**");
	}


	//Test-Case-05 
	@Test(priority = 5, testName = "TC05:Validating Honda Button", description = "Validating whether Honda Button is Clickable")
	public void validateHondaButtonIsClickable() {
		logger.info("**TC05 - Starts**");
		try {
			//Getting Honda Buttton to Validate
			WebElement btn_Honda = upcomingBikesPage.getHondaElementFromManufactureDropdown();
			logger.info("Getting the Honda Button");
			
			//Validating the Honda Button is Clickable by Explicit Wait Method
			logger.info("Validating the Honda Button is Clickable");
			Assert.assertEquals(isClickable(btn_Honda), true);
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC05 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		logger.info("**TC05 - Completes**");
	}

	
	//Test-Case-06
	@Test(priority = 6, testName = "TC06:Validating Honda Link", description = "Validating whether Upcoming-Honda-Bikes-Page is getting opened after Clicking")
	public void validateHondaButton() {
		logger.info("**TC06 - Starts**");
		try {
			//Selecting Honda from Manufacturer Dropdown
			upcomingBikesPage.selectHondaFromManufactureDropdown();
			logger.info("Selecting Honda Option From Manufacturer Dropdown");
			
			//Validating the New Page URL after Clicking
			logger.info("Validating the New Page URL whether the Option navigating to Honda Upcoming Bikes Page");
			Assert.assertEquals(getCurrentURL(), propertyUtililty.getUpcomingHondaBikesURL());
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC06 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		logger.info("**TC06 - Completes**");
	}


	//Test-Case-07
	@Test(priority = 7, testName = "TC07:Validating View More Button", description = "Validating whether View-More Button is Clickable")
	public void validateViewMoreButtonIsClickable() {
		logger.info("**TC07 - Starts**");
		try {
			//Getting View More Button
			WebElement viewMore = upcomingBikesPage.getViewMoreButton();
			logger.info("Getting View More Button");
			
			//Validating the View More Button Is Clickable
			logger.info("Validating the View More Button Is Clickable");
			Assert.assertEquals(isClickable(viewMore), true);
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC07 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		
		logger.info("**TC07 - Completes**");
	}
	

	//Test-Case-08 
	@Test(priority = 8, testName = "TC08:Validating Only Upcoming Honda Bikes in Page" , description = "Validating whether only Upcoming Honda Bikes showing in the page")
	public void validateUpcomingHondaBikes() {
		logger.info("**TC08 - Starts**");
		try {
			//Clicking View More Button
			upcomingBikesPage.clickViewMore();
			logger.info("Clicking View More Button");
			
			//Getting All Honda Upcoming Bikes to validate 
			List<String[]> upcomingBikes = upcomingBikesPage.getAllUpcomingBikes();
			logger.info("Getting all Honda Upcoming Bikes to validata only honda bikes are shown");
			
			//Validating the Upcoming Bikes are only from Honda
			logger.info("Validating the Upcoming Bikes are only from Honda");
			for(String[] bike : upcomingBikes) {
				Assert.assertEquals(bike[0].contains("Honda"), true);
			}
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC08 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		logger.info("**TC08 - Completes**");
	}

	
	//Test-Case-09
	@Test(priority = 9, testName = "TC09:Validating The Bikes Count in Message", description = "Validating whether the number of bikes displayed and in the message that shows the numbers are same")
	public void validateTheMessage() {
		logger.info("**TC09 - Starts**");
		try {
			
			//Getting No. of Honda Upcoming Bikes from message
			int bikeCountInMessage = upcomingBikesPage.getNoOfBikesFromMessage();
			logger.info("Getting No. of Honda Upcoming Bikes from message");
			
			//Getting No. of Honda Upcoming Bikes from page
			int bikeCountDisplayed = upcomingBikesPage.getUpcomingBikeElementsDisplayed().size();
			logger.info("Getting No. of Honda Upcoming Bikes from page");
			
			//Validating the Message count is matching with showing bikes count
			logger.info("Validating the Message count is matching with showing bikes count");
			Assert.assertEquals(bikeCountInMessage, bikeCountDisplayed);
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC09 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		logger.info("**TC09 - Completes**");
	}

	
	//Test-Case-10 
	@Test(priority = 10, testName = "TC10:Displaying All Upcoming Honda Bikes", description = "Displaying All Upcoming Honda Bikes")
	public void displayAllBikeDetails() throws IOException{
		logger.info("**TC10 - Starts**");
		try {
			//Getting All Honda Upcoming Bikes
			List<String[]> upcomingBikes = upcomingBikesPage.getAllUpcomingBikes();
			logger.info("Getting All Honda Upcoming Bikes");
			
			//Displaying All Honda Upcoming Bikes
			logger.info("Displaying All Honda Upcoming Bikes");
			//Printing in Console
			for(String[] bike : upcomingBikes) {
				System.out.println("-> " + bike[0] + " | " + bike[1] + " | " + bike[2]);
			}
			//Writing in Excel
			upcomingBikesPage.writeAllHondaUpcomingBikesExcel("TC10");
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC10 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		logger.info("**TC10 - Completes**");
	}

	
	//Test-Case-11:Displaying the bike details below 4Lakh in console 
	@Test(priority = 11 , testName = "TC11:Displaying Upcoming Honda Bikes Less Than 4Lakhs",  description = "Displaying Upcoming Honda Bikes Less Than 4Lakhs")	
	public void displayBelow4LakhBikeDetails() throws IOException{
		logger.info("**TC11 - Starts**");
		try {
			//Getting Honda Upcoming Bikes Below 4-Lakhs
			List<String[]> upcomingBikes = upcomingBikesPage.getUpcomingBikesLessThan(400000);
			logger.info("Getting Honda Upcoming Bikes Below 4-Lakhs");
			
			//Displaying Honda Upcoming Bikes Below 4-Lakhs
			logger.info("Displaying Honda Upcoming Bikes Below 4-Lakhs");
			//Printing in Console
			for(String[] bike : upcomingBikes) {
				System.out.println("-> " + bike[0] + " | " + bike[1] + " | " + bike[2]);
			}
			//Writing in Excel
			upcomingBikesPage.writeHondaUpcomingBikesBelow4LakhExcel("TC11");
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC11 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		logger.info("**TC11 - Completes**");
		logger.info("***TS01 - Completes***");
	}
}
