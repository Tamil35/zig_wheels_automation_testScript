package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	
					features= {".//FeatureFiles/usedCarsInChennai.feature"},
					//features= {"@target/rerun.txt"},
					glue="stepDefinitions",
					plugin= {"pretty", "html:reports/cucumberReport.html", 
							  "rerun:target/rerun.txt",
							  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
							},
							
					dryRun=false,    
					monochrome=true,    
					publish=true
					//tags="@sanity"  
					//tags="@regression"
					//tags="@sanity and @regression" 
					//tags="@sanity and not @regression" 
					//tags="@sanity or @regression" 
		)
public class TestRunner {

		}