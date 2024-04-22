package testSuites;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.GoogleSignInPage;
import pageObjects.HomePage;
import testsBase.BaseClass;

public class TestSuite3_Login extends BaseClass{

	public HomePage homePage;
	public GoogleSignInPage googleSignIn;
	//TC01
	@Test(priority = 1, testName = "TC01:Validating Login Button", description = "Validating whether Login is Clickable")
	public void valiateLoginButtonIsClickable() {
		logger.info("***TS03 - Starts***");
		logger.info("**TC01 - Starts**");
		try {
			//Initializing the Driver to HomePage Object
			homePage = new HomePage(driver);
			
			//Getting the Login Button
			WebElement loginBtn = homePage.getLogin();
			logger.info("Getting the Login Button");
			
			//Validating the Login Button is Clickable
			logger.info("Validating the Login Button is Clickable");
			Assert.assertEquals(isClickable(loginBtn), true);
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC01 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		logger.info("**TC01 - Completes**");
	}
	
	
	//TC02
	@Test(priority = 2, testName = "TC02:Validating Login Link", description = "Validating whether Login Page is getting opened after Clicking")
	public void validateLoginScreenVisible() {
		logger.info("**TC02 - Starts**");
		try {
			//Clicking the Login Button
			homePage.clickLogin();
			logger.info("Clicking the Login Button");
			
			//Getting the Heading of Login Screen
			String txtLoginScreen = homePage.getHeadingInLoginScreen();
			logger.info("Getting the Heading of Login Screen");
			
			
			//Validating the Login Screen by validating the heading of page
			logger.info("Validating the Login Screen");
			Assert.assertEquals(txtLoginScreen.contains("Login"), true);
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC02 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		logger.info("**TC02 - Completes**");
	}
	
	
	//TC03
	@Test(priority = 3, testName = "TC03:Validating Google Button", description = "Validating whether Google is Clickabe")
	public void validateGoogleButtonIsClickable() {
		logger.info("**TC03 - Starts**");
		try {
			//Getting the Google Login Button
			WebElement loginGoogle = homePage.getGoogleButton();
			logger.info("Getting the Google Login Button");
			
			//Validating the Google Button is Clickable
			logger.info("Validating the Google Button is Clickable");
			Assert.assertEquals(isClickable(loginGoogle), true);
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC03 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		logger.info("**TC03 - Completes**");
	}
	
	
	//TC04
	@Test(priority = 4, testName = "TC04:Validating Google Link", description = "Validating whether Google-Sign-In Page is getting opened after Clicking")
	public void validateGoogleLink() throws InterruptedException {
		logger.info("**TC04 - Starts**");
		try {
			//Clicking the Google Button
			homePage.clickGoogle();
			logger.info("Clicking the Google Button");
			
			//Switching to New Window which has opened
			homePage.switchWindowToGoogleSign();
			logger.info("Switching to Google Sign-in page");
			
			//Initializing the driver to Google Sign-in Page
			googleSignIn = new GoogleSignInPage(driver);
			
			//Getting the Title of Page
			String pageTitle = googleSignIn.getTitleOfPage();
			logger.info("Getting the Title of Page");
			
			//Validating the Google Sign-in Page
			logger.info("Validating the Google Sign-in Page");
			Assert.assertEquals(pageTitle.contains("Sign in - Google"), true);
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC04 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		logger.info("**TC04 - Completes**");
	}
	
	
	//TC05
	@Test(priority = 5, testName = "TC05:Capturing the Error Message After Invalid Mail Id", description = "Validating whether error message is displayed when invalid gmail id is entered and Capturing the error message")
	public void captureErrorMessafeWithInvalidMail() throws IOException {
		logger.info("**TC05 - Starts**");
		try {
			
			//Setting the Invalid Email Id
			googleSignIn.setEmailId(propertyUtililty.getInvalidEmail());
			logger.info("Setting the Email as Invalid Email");
			
			//Clicking the Next Button
			googleSignIn.clickNextAfterEmail();
			logger.info("Clicking the Next Button");
			
			//Getting the Error Message
			String msg = googleSignIn.getErrorMessageForInvalidEmail();
			logger.info("Getting the Error Message");
			
			//Displaying the Error Message
			logger.info("Displaying the Error Message");
			System.out.println(msg);
			
			//Writing in Excel
			googleSignIn.writeErrorMessageForInvalidEmailInExcel("TC05");
		}
		catch(Exception e) {
			//If any Exception Occurs, Test-Case Fails
			logger.error("TC05 - Failed");
			System.out.println(e);
			Assert.fail();
		}
		logger.info("**TC05 - Completes**");
		logger.info("***TS03 - Completes***");
	}
	
	
}
