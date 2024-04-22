package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelUtility;

public class HomePage extends BasePage{

	//**Constructor**
	public HomePage(WebDriver driver) {
		super(driver);
	}

	//***For Suite-1***
	
	//**Locators**
	
	@FindBy(xpath = "//div[@id='headerNewNavWrap']/nav/div/ul/li/a[@href='/newbikes']")
	WebElement btn_NewBikes; //Button of New Bikes
	
	@FindBy(xpath = "//*[@id='headerNewNavWrap']/nav/div/ul/li[3]/ul/li")
	List<WebElement> list_NewBikesDropDownOptions; //List of Options in New Bikes Dropdown

	@FindBy(xpath = "//div[@id='headerNewNavWrap']/nav/div/ul/li/ul//span[.='Upcoming Bikes']")
	WebElement btn_OptionUpcomingBikes; //Button of Option - Upcoming Bikes
	
	//**Actions**
	
	//Hovering to New Bikes Button
	public void hoverToNewBikes(){
		Actions builder = new Actions(driver);
		builder.moveToElement(btn_NewBikes).perform();
	}
	
	//Returning the List of Options as String present in New Bikes Dropdown
	public List<String> getOptionsInNewBikesDropdown() {
		List<String> optionsOfNewBikesDropdown = new ArrayList<String>();
		for(WebElement option : list_NewBikesDropDownOptions) {
			optionsOfNewBikesDropdown.add(option.getText());
		}
		return optionsOfNewBikesDropdown;
	}
	
	//Writing the New Bikes Dropdown Options in Excel
	public void writeOptionInNewBikesDropdownInExcel(String sheetName) throws IOException {
		List<String> options = getOptionsInNewBikesDropdown();
		ExcelUtility.write("01", sheetName, 0, 0, "New Bikes Dropdown");
		for(int i=2; i<=options.size()+1; i++) {
			ExcelUtility.write("01", sheetName, i, 0, options.get(i-2));
		}
	}
	
	//Returning the WebElement of Option - Upcoming Bikes
	public WebElement getUpcomingBikesButton() {
		return btn_OptionUpcomingBikes;
	}
	
	//Clicking of Option - Upcoming Bikes
	public void clickUpcomingBikes() {
		btn_OptionUpcomingBikes.click();
	}

	
	//***For Suite-2***
	
	//**Locators**
	
	@FindBy(xpath = "//div[@id='headerNewNavWrap']/nav/div/ul/li/a[@href='/used-car']")
	WebElement btn_UsedCars; //Button of Used Cars
	
	@FindBy(xpath = "//div[@id='headerNewNavWrap']/nav/div/ul/li[7]/ul/li/div/ul/li/span")
	List<WebElement> list_UsedCarsDropDownOptions; // List of Options in Used Cars Dropdown
	
	@FindBy(xpath = "//div[@id='headerNewNavWrap']//div//li[7]//li/div[2]//li[4]/span")
	WebElement btn_OptionChennai; //Button of Option - Chennai
	
	//**Actions**
	
	//Hovering to Used Cars Button
	public void hoverToUsedCars() {
		Actions builder = new Actions(driver);
		builder.moveToElement(btn_UsedCars).perform();
	}
	
	//Returning the List of Options as String present in Used Cars Dropdown
	public List<String> getOptionsInUsedCarsDropdown() {
		List<String> optionsOfUsedCarsDropdown = new ArrayList<String>();
		for(WebElement option : list_UsedCarsDropDownOptions) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String optionString = (String) js.executeScript("return arguments[0].innerHTML",option);
			optionsOfUsedCarsDropdown.add(optionString);
		}
		return optionsOfUsedCarsDropdown;
	}
	
	//Writing the New Bikes Dropdown Options in Excel
	public void writeOptionInUsedCarsDropdownInExcel(String sheetName) throws IOException {
		List<String> options = getOptionsInUsedCarsDropdown();
		ExcelUtility.write("02", sheetName, 0, 0, "Used Cars Dropdown");
		for(int i=2; i<=options.size()+1; i++) {
			ExcelUtility.write("02", sheetName, i, 0, options.get(i-2));
		}
	}
	
	//Returning the WebElement of Option - Chennai
	public WebElement getChennaiButton() {
		return btn_OptionChennai;
	}
	
	//Clicking of Option - Chennai
	public void clickChennai() {
		btn_OptionChennai.click();
	}
	
	
	//***For Suite-3***
	
	//**Locators**
	
	@FindBy(xpath = "//div[@id='forum_login_wrap_lg']")
	WebElement div_Login; //Login Div
	
	@FindBy(xpath = "//div[@id='des_lIcon']")
	WebElement btn_Login; //Login Button
	
	@FindBy(xpath = "//div[@id='myModal3-modal-content']/div[1]/div/div[3]/div[6]/div/span[2]")
	WebElement btn_Google; //Google Button
	
	@FindBy(xpath = "//div[@id='myModal3-modal-content']/div[1]/div/div[3]/h4/span[1]")
	WebElement txt_Login; // Text of Login
	
	
	//**Actions**
	
	//Returning the WebElement of Login Div-Tag
	public WebElement getLogin() {
		return div_Login;
	}
	
	//Clicking of Login Button
	public void clickLogin() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", btn_Login);
	}
	
	//Returning the Main Heading of New Login Screen
	public String getHeadingInLoginScreen() {
		return txt_Login.getText();
	}
	
	//Returning the WebElement of Google Button
	public WebElement getGoogleButton() {
		return btn_Google;
	}
	
	//Clicking of Google Button
	public void clickGoogle() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", btn_Google);
	}
	
	//Switching of Window to Google Sign-in Page
	public void switchWindowToGoogleSign() throws InterruptedException {
		Thread.sleep(5000);
		List<String> handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(handles.get(1));
		driver.manage().window().maximize();
	}	

}
