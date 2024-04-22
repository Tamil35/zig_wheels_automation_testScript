package testSuites;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.UsedCarsInChennaiPage;
import testsBase.BaseClass;

public class TestSuite2_UsedCarsInChennai extends BaseClass{

	public HomePage homePage;
	public UsedCarsInChennaiPage usedCars;
	
	//Test-Case-01
	@Test(priority = 1, testName = "TC01:Validating Used-Cars Dropdown", description = "Validating and Getting Dropdown of Used-Cars Section")	
	public void validateUsedCarsDropdown() throws IOException {
		logger.info("***TS02 - Starts***");
		logger.info("**TC01 - Starts**");
		try {
			//Initializing the Driver to HomePage Object
			homePage = new HomePage(driver);
			
			//Hovering to the Used Cars Options
			homePage.hoverToUsedCars();
			logger.info("Hovering to Used Cars Option");
			
			//Getting the Options in Used Cars Option
			List<String> options = homePage.getOptionsInUsedCarsDropdown();
			logger.info("Capturing the Options in Used Cars Dropdown");
		
			//Validating by size of list of options should be greater than zero
			logger.info("Validating the Options in Used Cars Dropdown");
			Assert.assertEquals(options.size()>0, true, "No Options in New Bikes Dropdown");
			
			//If Passed
			//Printing in Console
			System.out.println("Used Cars Dropdown Options:-");
			for(String option : options) {
				System.out.println("'" + option + "'");
			}
			//Writing in Excel
			homePage.writeOptionInUsedCarsDropdownInExcel("TC01");
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
	@Test(priority = 2, testName = "TC02:Validating Chennai Button", description = "Validating whether Chennai Button is Clickable")
	public void validateChennaiIsClickable() {
		logger.info("**TC02 - Starts**");
		try {
			//Getting the Button - Chennai
			WebElement chennaiBtn = homePage.getChennaiButton();
			logger.info("Getting the Chennai Button");
			
			//Checking the Button is Clickable
			logger.info("Validating Chennai Button is Clickable");
			Assert.assertEquals(isClickable(chennaiBtn), true);
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
	@Test(priority = 3, testName = "TC03:Validating Chennai Link", description = "Validating whether Used-Cars-In-Chennai-Page is getting opened after Clicking")
	public void validateChennaiLink() {
		logger.info("**TC03 - Starts**");
		try {
			//Clicking the Button - Chennai
			homePage.clickChennai();
			logger.info("Clicking the Chennai Button");
			
			//Validating the New Page URL after Clicking 
			logger.info("Validating the New Page URL whether the Button navigating to Used Cars in Chennai Page");
			Assert.assertEquals(getCurrentURL(), propertyUtililty.getUsedCarsInChennaiURL());
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
	@Test(priority = 4, testName = "TC04:Validating Is Popular Model Section Displayed" , description = "Validating whether Popular Models section is displayed or not")
	public void validatePopularModelsIsDisplayed() {
		logger.info("**TC04 - Starts**");
		try {
			//Initializing the Driver to Used Cars in Chennai Page Object
			usedCars = new UsedCarsInChennaiPage(driver);
			
			//Getting the Section WebElement of Popular Models
			WebElement secPopularModels = usedCars.getPopularModelSection();
			logger.info("Getting the Section WebElement of Popular Models");
			
			//Validating the Section - Popular Models is Displayed
			logger.info("Validating the Section - Popular Models is Displayed");
			Assert.assertEquals(secPopularModels.isDisplayed(), true);
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
	@Test(priority = 5, testName = "TC05:Displaying Popular Models", description = "Displaying All Popular Models in Chennai-Used-Cars Page")
	public void displayPopularModels() throws IOException {
		logger.info("**TC05 - Starts**");
		try {
			//Getting the List of Popular Models
			List<String> models = usedCars.getPopularModels();
			logger.info("Getting the List of Popular Models");
			
			//Displaying the  Popular Models
			logger.info("Displaying the  Popular Models");
			//Printing in Console
			System.out.println("Popular Models in Chennai (Used Cars):-");
			for(String model : models) {
				System.out.println("'" + model + "'");
			}
			//Writing in Excel
			usedCars.writeOptionInNewBikesDropdownInExcel("TC05");
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC05 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		logger.info("**TC05 - Completes**");
		logger.info("***TS02 - Completes***");
	}
	
	
}
