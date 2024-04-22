package utilities;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class Listeners implements ITestListener {
	
	public String getTestName(ITestResult result) {
		Method method = result.getMethod().getConstructorOrMethod().getMethod();
	    Test test = method.getAnnotation(Test.class);
		return test.testName();
	}
	
	public void onStart(ITestContext context) {
		System.out.println("Testing Started");
	}
	
	public void onTestStart(ITestResult result) {
		System.out.println();
		Method method = result.getMethod().getConstructorOrMethod().getMethod();
	    Test test = method.getAnnotation(Test.class);
		System.out.println("Test-Case-'" + test.testName() + "' is Started");
	}
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test-Case is Success!!");
		System.out.println();
	}
	
	public void onTestFailure(ITestResult result) {
		System.out.println("Test-Case is Failed");
		System.out.println();
	}
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test-Case is Skipped");
		System.out.println();
	}
	public void onFinish(ITestContext context) {
		IResultMap failedMap = context.getFailedTests();
		IResultMap skippedMap = context.getSkippedTests();
		
		List<ITestResult> failedResults = new ArrayList<>(failedMap.getAllResults());
		List<ITestResult> skippeddResults = new ArrayList<>(skippedMap.getAllResults());
		
		if(failedResults.size()==0 && skippeddResults.size()==0) {
			System.out.println("Testing Completed Successfully, All Test-Cases Passed!!");
		}
		else {
			if(failedResults.size()>0) {
				System.out.println("Failed Test Cases("+ failedResults.size()  +"):-");
				for(ITestResult result: failedResults) {
					System.out.println(getTestName(result));
				}
			}
			if(skippeddResults.size()>0) {
				System.out.println("Skipped Test Cases("+ skippeddResults.size()  +"):-");
				for(ITestResult result: skippeddResults) {
					System.out.println(getTestName(result));
				}
			}
			
		}
		
	}
}
