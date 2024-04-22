package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtilities {
	
	private Properties property;
	
	public PropertyUtilities() {
		
		String propertyFilePath = System.getProperty("user.dir") + "/src/test/resources/Properties.properties";
		FileReader reader = null;
		
		try {
			reader = new FileReader(new File(propertyFilePath));
			property = new Properties();
			property.load(reader);
		} 
		catch (FileNotFoundException e) {
			System.err.println("Property File Not Found");
		} 
		catch (IOException e) {
			System.err.println("Property File Can't be loaded");
		}
	}
	
	public String getHomePageURL() {
		return property.getProperty("HomePageURL");
	}

	public String getUpcomingBikesURL() {
		return property.getProperty("UpcomingBikesPageURL");
	}
	
	public String getUpcomingHondaBikesURL() {
		return property.getProperty("UpcomingHondaBikesPageURL");
	}
	
	public String getUsedCarsInChennaiURL() {
		return property.getProperty("UsedCarsInChennaiURL");
	}
	
	public String getInvalidEmail() {
		return property.getProperty("InvalidEmail");
	}
	
	public String getExecutionEnviroment() {
		return property.getProperty("execution_env");
	}
	
}

