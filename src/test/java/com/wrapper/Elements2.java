package com.wrapper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.data.AlertOption;
import com.data.BrowserName;
import com.data.Devices;
import com.utilities.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Elements2 {

	private static WebDriver wd;
	private static String userName;
	private static String accessKey;
	private static String remoteUrl;
	private WebDriverWait wait;// Explicit

	public WebDriver launchBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			wd = new ChromeDriver();
			wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //Implicity Wait-All the selenium the scripts
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			wd = new FirefoxDriver();
		}

		else {
			System.err.print("Invalid Browser Name!!!");
		}
		long timeOut = Long.parseLong(Utilities.readConfigFile("WAIT_TIME"));
		wait = new WebDriverWait(wd, timeOut);
		return wd; // Session
	}

	public void launchBrowser(BrowserName browserName) {
		System.out.println(Utilities.readConfigFile("REMOTE_TEST"));
		if (Utilities.readConfigFile("REMOTE_TEST").equalsIgnoreCase("TRUE")) // Remote testing
		{
			if (Utilities.readConfigFile("REMOTE_TOOL").equalsIgnoreCase("LAMBDA")) {
				System.out.println("Running on Lamda test");
				userName = Utilities.readConfigFile("LAMDA_AUTOMATE_USERNAME");
				accessKey = Utilities.readConfigFile("LAMDA_AUTOMATE_ACCESS_KEY");
				remoteUrl = "https://" + userName + ":" + accessKey + "@hub.lambdatest.com/wd/hub";
			}

			else if (Utilities.readConfigFile("REMOTE_TOOL").equalsIgnoreCase("bs")) {
				System.out.println("Running on Browser stack");
				userName = Utilities.readConfigFile("AUTOMATE_USERNAME");
				accessKey = Utilities.readConfigFile("AUTOMATE_ACCESS_KEY");
				remoteUrl = "https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

			} else if (Utilities.readConfigFile("REMOTE_TOOL").equalsIgnoreCase("grid")) {
				System.out.println("Running on grid");
				remoteUrl = "http://3.143.221.232:4444/wd/hub";

			}

			DesiredCapabilities caps = new DesiredCapabilities();
//			caps.setCapability("os_version", "10");
//			caps.setCapability("resolution", "1920x1080");
			caps.setCapability("browserName", "chrome");
//			caps.setCapability("browser_version", "90");
			// caps.setCapability("os", "Windows");
			// caps.setCapability("name", Utilities.readConfigFile("REPORT_TITLE")); // test
			// name
			// caps.setCapability("build", "BStack Build Number 1"); // CI/CD job or build
			// name
			URL url = null;
			try {
				url = new URL(remoteUrl);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wd = new RemoteWebDriver(url, caps);

		} else { // local machine

			if (browserName == BrowserName.CHROME) {
				WebDriverManager.chromedriver().setup();
				wd = new ChromeDriver();
			} else if (browserName == BrowserName.FIREFOX) {
				WebDriverManager.firefoxdriver().setup();
				wd = new FirefoxDriver();
			}

			else if (browserName == BrowserName.BROWSERSTACK) {
				System.out.println("ADD the remote Browser testing code");
			}

			else {
				System.err.print("Invalid Browser Name!!!");
			}
		}
	}

	public void loadPage(String URL) {
		wd.get(URL);
	}

	public void quitBrowserSession() {
		wd.quit();
	}

	public void clickOnElement(By elementLocator) {
		WebElement element = wd.findElement(elementLocator);
		element.click();
	}

	protected String getElementAttribute(By userNameLocator, String attributeName) {
		// TODO Auto-generated method stub
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameLocator)); //explicit wait
		String value = element.getAttribute(attributeName);
		return value;
	}

	public void clickOnElement(WebElement elementLocator) {

		elementLocator.click();
	}

	public WebElement getElement(By elementLocator) {
		// TODO Auto-generated method stub
		WebDriverWait Tempwait = new WebDriverWait(wd, 10);
		WebElement element = Tempwait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
		return element;
	}

	public WebElement getElement(WebElement element, By elementLocator) {
		// TODO Auto-generated method stub
		WebElement childElement = element.findElement(elementLocator);
		return childElement;
	}

	public void enterText(By elementLocator, String textToEnter) {
		WebElement element = wd.findElement(elementLocator);
		element.sendKeys(textToEnter);
	}

	public void clearText(By elementLocator) {
		WebElement element = wd.findElement(elementLocator);
		element.clear();
	}

	public void selectFromDropDown(By dropDOwnLocator, String visibleText) {
		WebElement element = wd.findElement(dropDOwnLocator);
		Select dropDown = new Select(element);
		dropDown.selectByVisibleText(visibleText);
	}

	public void selectFromDropDown(String value, By dropDownLocator) {
		WebElement element = wd.findElement(dropDownLocator);
		Select dropDown = new Select(element);
		dropDown.selectByValue(value);
	}

	public void selectFromDropDown(int index, By dropDownLocator) {
		WebElement element = wd.findElement(dropDownLocator);
		Select dropDown = new Select(element);
		dropDown.selectByIndex(index);
	}

	public void setScreenSize(Devices device) {
		if (device == Devices.PC) {
			wd.manage().window().maximize();
		}
	}

	public String getTextFromElement(By elementLocator) {
		WebElement element = wd.findElement(elementLocator);
		String text = element.getText();
		return text;

	}

	public List<WebElement> getAllElements(By elementLocator) {
		// TODO Auto-generated method stub
		List<WebElement> elementList = wd.findElements(elementLocator);
		System.out.println("Number of WebElements found " + elementList.size());
		return elementList;

	}

	public void clickOnAlert(AlertOption option) {
		Alert activeAlert = wd.switchTo().alert(); // Alert is an interface!!
		if (option == AlertOption.ACCEPT) {
			activeAlert.accept();
		} else if (option == AlertOption.CANCEL) {
			activeAlert.dismiss();

		}
	}

	public static String takeScreenshotForPage(String fileName) {

		TakesScreenshot pageScreenShot = (TakesScreenshot) wd;
		File f = pageScreenShot.getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "//screenshots//" + fileName + ".png";
		File myFile = new File(System.getProperty("user.dir") + "//screenshots//" + fileName + ".png");
		try {
			FileUtils.copyFile(f, myFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return filePath;

	}

}
