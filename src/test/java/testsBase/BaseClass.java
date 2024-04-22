package testsBase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilities.PropertyUtilities;

public class BaseClass {
	
	//Driver
	public static WebDriver driver;
	//Logger
	public Logger logger;
	//Utilities
	public PropertyUtilities propertyUtililty;
	
	@Parameters({"browser"})
	@BeforeClass
	public void setup(String browserName) throws InterruptedException {
		
		//Log4j
		logger=LogManager.getLogger(this.getClass());
		logger.info("Testing Started");
		
		//Creating new Respective-Driver
		switch (browserName) {
		case "Chrome":
			driver = new ChromeDriver();
			break;
		case "Edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Incorrect Browser-Name");
		}
		
		//Deleting All Cookies
		driver.manage().deleteAllCookies();
		
		//Maximizing the Browser
		driver.manage().window().maximize();
		
		//Opening the Home Page of ZigWheels 
		propertyUtililty = new PropertyUtilities();
		driver.get(propertyUtililty.getHomePageURL());
		logger.info("Opening Zig Wheels Home Page");
		
		//Adding Implicit-Wait of 10 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	@AfterClass
	public void tearDown() {
		//Closing all tabs and the browser 
		logger.info("Testing Ended");
		driver.quit();
	}

	
	//Returning the Current URL of page
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	//Method to Check the WebElement is Clickable by Explicit Wait 
	public boolean isClickable(WebElement element)      
	{
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        return true;
	    }
	    catch (Exception e){
	    	return false;
	    }
	}

	//Capture Screenshot when test-case failes
	public static String takeScreenshot(String fileName) {
		
		String filePath = "./Screenshots/" + fileName + ".png";
		
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(filePath);
		try {
			FileHandler.copy(screenshotFile, destinationFile);
		} 
		catch (IOException e) {
			System.out.println("Can't Copy the Screenshot to a File");
		}
		
		return filePath;
	}
}
