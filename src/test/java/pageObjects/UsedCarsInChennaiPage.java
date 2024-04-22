package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelUtility;

public class UsedCarsInChennaiPage extends BasePage{

	//**Constructor**
	public UsedCarsInChennaiPage(WebDriver driver) {
		super(driver);
	}
	
	//***Test-Suite-2***
	
	//**Locators**
	
	@FindBy(xpath = "//div[.='Popular Models']")
	WebElement sec_PopularModels; //Popular Models Div
	
	@FindBy(xpath = "//div[@class ='gsc_thin_scroll']//li/label")
	List<WebElement> list_PopularModels; //List of Popular Models
	
	//**Actions**
	
	//Returning the Popular Model Div
	public WebElement getPopularModelSection() {
		return sec_PopularModels;
	}
	
	//Returning the List of Models as String in Popular Models 
	public List<String> getPopularModels(){
		List<String> models = new ArrayList<String>();
		for(WebElement model : list_PopularModels) {
			models.add(model.getText());
		}
		return models;
	}
	
	public void writeOptionInNewBikesDropdownInExcel(String sheetName) throws IOException {
		List<String> models = getPopularModels();
		ExcelUtility.write("02", sheetName, 0, 0, "Popular Models");
		for(int i=2; i<=models.size()+1; i++) {
			ExcelUtility.write("02", sheetName, i, 0, models.get(i-2));
		}
	}

}
