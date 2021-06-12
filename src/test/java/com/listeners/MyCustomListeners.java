package com.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utilities.Utilities;
import com.wrapper.Elements;

public class MyCustomListeners implements ITestListener {
	private ExtentHtmlReporter extentHtmlReporter;
	private ExtentReports extentReport;
	private ExtentTest extentTest;

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest = extentReport.createTest("Test Name: " + result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		extentTest.pass("The Test Has passed successfully" + result.getMethod().getDescription());
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		try {
			extentTest.addScreenCaptureFromPath(Elements.takeScreenshotForPage(result.getMethod().getMethodName()),
					result.getMethod().getMethodName());
			extentTest.assignAuthor(Utilities.readConfigFile("AUTHOR"));
			String groups[] = result.getMethod().getGroups();
			// String data = "";
			for (String d : groups) {
				extentTest.assignCategory(d);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		extentTest.fail("The Test Has failed " + result.getMethod().getDescription());

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.skip("The Test Has skipped" + result.getMethod().getDescription());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		File screenshots = new File(System.getProperty("user.dir") + "//screenshots");
		File report = new File(System.getProperty("user.dir") + "//reports");
		if(Utilities.readConfigFile("PRESERVE_REPORT").equalsIgnoreCase("FALSE"))
		{
		if (screenshots.exists() || report.exists()) {
			try {
				FileUtils.deleteDirectory(report);
				FileUtils.deleteDirectory(screenshots);
				report.mkdir();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		}
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat myDateFormat = new SimpleDateFormat("dd-MM-YYYY-HH-mm-SS");
		String stringDate = myDateFormat.format(date);
		String reportName = "AutomationSolution-" + stringDate + ".html";
		extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//reports//" + reportName);
		extentReport = new ExtentReports();
		extentReport.attachReporter(extentHtmlReporter);
		extentHtmlReporter.config().setTheme(Theme.DARK);
		extentHtmlReporter.config().setReportName(Utilities.readConfigFile("REPORT_TITLE"));

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentReport.flush();
	}

}
