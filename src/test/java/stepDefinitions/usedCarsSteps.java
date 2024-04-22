package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import factory.CucumberBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.UsedCarsInChennaiPage;

public class usedCarsSteps {
	public HomePage homePage=new HomePage(CucumberBase.getDriver());
	public UsedCarsInChennaiPage usedCars= new UsedCarsInChennaiPage(CucumberBase.getDriver());
	WebDriver driver;
	
	//Scenario 1: Validaing the functionality of used cars drop-down
	
	@Given("the user opens the zigwheels website")
	public void the_user_opens_the_zigwheels_website() throws IOException {
		driver=CucumberBase.getDriver();
		driver.get(CucumberBase.getProperties().getProperty("appURL"));
	}

	@When("the user hovers on the used cars option")
	public void the_user_hovers_on_the_used_cars_option() {
		homePage.hoverToUsedCars();
	}

	@Then("a drop-down opens displaying the options under used cars topic")
	public void a_drop_down_opens_displaying_the_options_under_used_cars_topic() throws IOException {
		List<String> options = homePage.getOptionsInUsedCarsDropdown();
		Assert.assertEquals(options.size()>0, true, "No Options in New Bikes Dropdown");
		System.out.println("Used Cars Dropdown Options:-");
		for(String option : options) {
			System.out.println("'" + option + "'");
		}
		homePage.writeOptionInUsedCarsDropdownInExcel("TC01");
	}
	
	//Scenario 2: Validating whether the chennai button under used cars section is clickable
	
	@Then("chennai button under used cars section is clickable")
	public void chennai_button_under_used_cars_section_is_clickable() {
		WebElement chennaiBtn = homePage.getChennaiButton();
		Assert.assertEquals(CucumberBase.isClickable(chennaiBtn), true);
	}

	
	//Scenario 3: Validating whether Used-Cars-In-Chennai-Page is getting opened after Clicking
	
	@When("the user clicks on the chennai button")
	public void the_user_clicks_on_the_chennai_button() {
		homePage.clickChennai();
	}

	@Then("a page displaying used cars in chennai is opened")
	public void a_page_displaying_used_cars_in_chennai_is_opened() throws IOException {
		Assert.assertEquals(CucumberBase.getCurrentURL(), CucumberBase.getProperties().getProperty("UsedCarsInChennaiURL"));
	}
	
	
	//Scenario 4: Validating whether Popular Models section is displayed or not
	
	@Then("the popular model section is displayed")
	public void the_popular_model_section_is_displayed() {
		WebElement secPopularModels = usedCars.getPopularModelSection();
		Assert.assertEquals(secPopularModels.isDisplayed(), true);
	}
	
	
	//Scenario 5: Displaying All Popular Models in Chennai-Used-Cars Page
	
	@Then("the popular models in the page is printed in the console")
	public void the_popular_models_displayed_in_the_page_is_printed_in_the_console() throws IOException {
		List<String> models = usedCars.getPopularModels();
		System.out.println("Popular Models in Chennai (Used Cars):-");
		for(String model : models) {
			System.out.println("'" + model + "'");
		}
		usedCars.writeOptionInNewBikesDropdownInExcel("TC05");
	}
	
	}
