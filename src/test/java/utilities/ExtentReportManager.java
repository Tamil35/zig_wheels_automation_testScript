package utilities;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testsBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports report;
	public ExtentTest test;
	
	public String getTestName(ITestResult result) {
		Method method = result.getMethod().getConstructorOrMethod().getMethod();
	    Test test = method.getAnnotation(Test.class);
		return test.testName();
	}
	
	public void onStart(ITestContext context) {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String fileName = "ExtentReport_"+ timestamp + ".html";
		String fileLocation = System.getProperty("user.dir") + "/reports/" + fileName ;
		
		sparkReporter = new ExtentSparkReporter(fileLocation);
		
		sparkReporter.config().setDocumentTitle("Hackthon Project");
		sparkReporter.config().setReportName("Zig Wheels");
		sparkReporter.config().setTheme(Theme.DARK);
		
		//Setting Properties
		report = new ExtentReports();
		report.attachReporter(sparkReporter);
		report.setSystemInfo("Application", "Zig Wheels");
		report.setSystemInfo("Operating System", System.getProperty("os.name"));
		report.setSystemInfo("User Name", System.getProperty("user.name"));
		//String browser = context.getCurrentXmlTest().getParameter("browser");
		report.setSystemInfo("Browser", "Chrome");
	}
	
	public void onTestSuccess(ITestResult result) {
		test = report.createTest(result.getTestClass().getName());
		test.log(Status.PASS, "Test-Case '" + getTestName(result) + "' is Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		test = report.createTest(result.getTestClass().getName());
		test.log(Status.FAIL, "Test-Case '" + getTestName(result) + "' is Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		String ssPath = BaseClass.takeScreenshot(getTestName(result));
		test.addScreenCaptureFromPath(ssPath);
	}
	
	public void onTestSkipped(ITestResult result) {
		test = report.createTest(result.getTestClass().getName());
		test.log(Status.SKIP, "Test-Case '" + getTestName(result) + "' is Skipped");
	}
	
	public void onFinish(ITestContext context) {
		report.flush();
	}

}
