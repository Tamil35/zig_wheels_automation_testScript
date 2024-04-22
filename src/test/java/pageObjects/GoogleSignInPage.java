package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelUtility;

public class GoogleSignInPage extends BasePage{
	
	//**Constructor**
	public GoogleSignInPage(WebDriver driver) {
		super(driver);
	}
	
	//***For Test-Suite-3***
	
	//**Locators**
	@FindBy(xpath = "//input[@type='email']")
	WebElement inp_email; //Input Field of Email
	
	@FindBy(xpath = "//div[@id='identifierNext']")
	WebElement btn_nextAfterEmail; //Next Button in Email Page
	
	@FindBy(xpath = "//span[@class='AfGCob']/..")
	WebElement txt_errorEmail; //Text of Error Message of Invalid Email
	
	//**Actions**
	
	//Returns the Title of Page
	public String getTitleOfPage() { 
		return driver.getTitle();
	}
	
	//Set the Email in Email Text Field
	public void setEmailId(String email) {
		inp_email.clear();
		inp_email.sendKeys(email);
	}
	
	//Clicking of Next Button in Email Page
	public void clickNextAfterEmail() {
		btn_nextAfterEmail.click();
	}
	
	//Returns the Error Message of Invalid Email
	public String getErrorMessageForInvalidEmail() {
		return txt_errorEmail.getText();
	}
	
	public void writeErrorMessageForInvalidEmailInExcel(String sheetName) throws IOException {
		ExcelUtility.write("03", sheetName, 0, 0, "Error Message After Invalid Email");
		ExcelUtility.write("03", sheetName, 2, 0, getErrorMessageForInvalidEmail());
	}
}
