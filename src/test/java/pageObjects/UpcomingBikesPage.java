package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import utilities.ExcelUtility;

public class UpcomingBikesPage extends BasePage{

	public Actions builder;
	public JavascriptExecutor js;

	//**Constructor**
	public UpcomingBikesPage(WebDriver driver) {
		super(driver);
	}

	//***For Test-Suite-1***

	//**Locators**
	
	@FindBy(xpath = "//select[@id='makeId']")
	WebElement btn_ManufacturerDropdown; //Button of Manufacturer Dropdown

	@FindBy(xpath = "//div[@id='carModels']/div[1]/div[1]")
	WebElement txt_UpcomingBikesMessage; //Text of Message in Honda Upcoming Bikes

	@FindBy(xpath = "//ul[@id='modelList']/li[not(contains(@class, ' hidden')) and contains(@class, 'modelItem')]")
	List<WebElement> list_CurrentUpcomingHondaBikes; //List of Current Showing Honda Upcoming Bikes

	@FindBy(xpath = "//ul[@id='modelList']/li/span")
	WebElement btn_ViewMore; //Button of View More

	@FindBy(xpath = "//ul[@id='modelList']/li[contains(@class, 'modelItem')]")
	List<WebElement> list_AllUpcomingHondaBikes; //List of All Honda Upcoming Bikes

	By txt_bikeName_loc = By.xpath("./div/div[3]/a/strong"); //Honda Upcoming Bike Name
	By txt_bikePriceLoc = By.xpath("./div/div[3]/div[1]"); //Honda Upcoming Bike Price
	By txt_bikeLaunch_loc = By.xpath("./div/div[3]/div[2]"); //Honda Upcoming Bike Launch

	//**Actions**
	
	//Clicking of Dropdown of Manufacturer
	public void clickManufactureDropdown() {
		btn_ManufacturerDropdown.click();
	}
	
	//Returning the List of Options as String in Manufacturer Dropdown
	public List<String> getOptionsInManufacturerDropdown() {
		List<String> options = new ArrayList<String>();
		Select maunfaturerSelector =  new Select(btn_ManufacturerDropdown);
		for(WebElement option : maunfaturerSelector.getOptions()) {
			options.add(option.getText());
		}
		return options;
	}
	
	//Writing the Manufacturer Dropdown Options in Excel
	public void writeOptionInNewBikesDropdownInExcel(String sheetName) throws IOException {
		List<String> options = getOptionsInManufacturerDropdown();
		ExcelUtility.write("01", sheetName, 0, 0, "Manufacturer Dropdown");
		for(int i=2; i<=options.size()+1; i++) {
			ExcelUtility.write("01", sheetName, i, 0, options.get(i-2));
		}
		System.out.println("Manufacturer Dropdown is written in Excel");
	}
	
	
	//Returning the WebElement of Honda in Manufacturer Dropdown
	public WebElement getHondaElementFromManufactureDropdown() {
		Select maunfaturerSelector =  new Select(btn_ManufacturerDropdown);
		List<WebElement> manufacturers = maunfaturerSelector.getOptions();
		return manufacturers.get(3);
	}
	
	//Selecting the Honda in Manufacturer Dropdown
	public void selectHondaFromManufactureDropdown(){
		Select maunfaturerSelector =  new Select(btn_ManufacturerDropdown);
		maunfaturerSelector.selectByValue("53");
	}
	
	//Returning the Button of View-More
	public WebElement getViewMoreButton() {
		return btn_ViewMore;
	}
	
	//Clicking the View-More Button until all Honda upcoming bikes are shown
	public void clickViewMore() {
		String stringCount = txt_UpcomingBikesMessage.getText();
		stringCount = stringCount.replaceAll("[^0-9]", "");
		int bikesCount =  Integer.parseInt(stringCount);
		while(list_CurrentUpcomingHondaBikes.size() < bikesCount) {
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", btn_ViewMore);
			js.executeScript("arguments[0].click()", btn_ViewMore);
		}
	}
	
	//Returning the list of All Honda Upcoming Bikes as String Array 
	public List<String[]> getAllUpcomingBikes() {
		List<String[]> upcomingBikes = new ArrayList<String[]> ();		
		for(WebElement upcomingHondaBike : list_AllUpcomingHondaBikes) {
			String bikeName = upcomingHondaBike.findElement(txt_bikeName_loc).getText();
			String bikePrice = upcomingHondaBike.findElement(txt_bikePriceLoc).getText();
			String bikeLaunch = upcomingHondaBike.findElement(txt_bikeLaunch_loc).getText();
			String[] bike = {bikeName, bikePrice, bikeLaunch};
			upcomingBikes.add(bike);
		}
		return upcomingBikes;
	}
	
	//Returning the Total Honda Upcoming Bikes from the Message
	public int getNoOfBikesFromMessage() {
		String stringCount = txt_UpcomingBikesMessage.getText();
		stringCount = stringCount.replaceAll("[^0-9]", "");
		return Integer.parseInt(stringCount);		
	}

	//Returning the List of Honda Upcoming Bikes as WebElements
	public List<WebElement> getUpcomingBikeElementsDisplayed(){
		return list_AllUpcomingHondaBikes;
	}
	
	public void writeAllHondaUpcomingBikesExcel(String sheetName) throws IOException {
		ExcelUtility.write("01", sheetName, 0, 0, "All Honda Upcoming Bikes");
		List<String[]> allHondaUpcomingBikes = getAllUpcomingBikes();
		for(int i=2; i<=allHondaUpcomingBikes.size()+1; i++) {
			String[] bikeInfo =  allHondaUpcomingBikes.get(i-2);
			for(int j=0; j<bikeInfo.length; j++) {
				ExcelUtility.write("01", sheetName, i, j, bikeInfo[j]);
			}
			
		}
		System.out.println("All Honda Upcoming Bikes is written in Excel");
		
	}
	
	//Returning the list of Honda Upcoming Bikes which is less than 4 Lakhs as String Array
	public List<String[]> getUpcomingBikesLessThan(int amount) {
		List<String[]> upcomingBikes = new ArrayList<String[]> ();		
		for(WebElement upcomingHondaBike : list_AllUpcomingHondaBikes) {
			String bikeName = upcomingHondaBike.findElement(txt_bikeName_loc).getText();
			String bikePrice = upcomingHondaBike.findElement(txt_bikePriceLoc).getText();
			String bikeLaunch = upcomingHondaBike.findElement(txt_bikeLaunch_loc).getText();
			double price=0;
			bikePrice = bikePrice.replaceAll("Rs. ", "");
			if(bikePrice.contains("Lakh")) {
				price = Double.parseDouble(bikePrice.replaceAll("[^0-9.]", ""));
				price = Math.round(price*100000);
			}
			else {
				price = Double.parseDouble(bikePrice.replaceAll("[^0-9]", ""));
				price = Math.round(price);
			}
			if(price<amount) {
				String[] bike = {bikeName, "Rs. " + bikePrice, bikeLaunch};
				upcomingBikes.add(bike);
			}
		}
		return upcomingBikes;
	}
	
	public void writeHondaUpcomingBikesBelow4LakhExcel(String sheetName) throws IOException {
		ExcelUtility.write("01", sheetName, 0, 0, "Honda Upcoming Bikes Below 4-Lakhs");
		List<String[]> hondaUpcomingBikes = getUpcomingBikesLessThan(400000);
		for(int i=2; i<=hondaUpcomingBikes.size()+1; i++) {
			String[] bikeInfo =  hondaUpcomingBikes.get(i-2);
			for(int j=0; j<bikeInfo.length; j++) {
				ExcelUtility.write("01", sheetName, i, j, bikeInfo[j]);
			}
			
		}
		System.out.println("Honda Upcoming Bikes below 4-Lakhs is written in Excel");
		
	}





}
