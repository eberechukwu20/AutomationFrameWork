package com.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.data.BrowserName;
import com.data.Devices;
import com.pages.LandingPage;
import com.pages.MyAccountPage;
import com.pages.SignInPage;
import com.utilities.Utilities;
import com.wrapper.Elements;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(com.listeners.MyCustomListeners.class)
public class LandingPageTest {
	SignInPage signInPage;

	@BeforeMethod(description = "sets up the requiremnt for the tests")
	public void setup() {
		LandingPage landingPage = new LandingPage();
		landingPage.launchBrowser(BrowserName.CHROME);
		landingPage.setScreenSize(Devices.PC);
		landingPage.loadPage(Utilities.readConfigFile("URL"));
		signInPage = landingPage.goToSignInPage();

	}

	@Description("verifies login feature is working or not")
	@Severity(SeverityLevel.NORMAL)
	@Story("Verify the Login Feature")
	@Test(testName = "login Test", description = "verifies login feature is working or not", groups = {
			"e2e,smoke" }, dataProviderClass = com.dataproviders.UserLoginDataProvider.class, dataProvider = "login data provider")
	public void loginTest(String emailAddress, String password, String userName) {
		MyAccountPage myAccountPage = signInPage.doLogin(emailAddress, password);
		Assert.assertEquals(myAccountPage.getUserName(), userName);
	}

	@AfterMethod(description = "Close the browser sessions")
	public void tearDown() {
		signInPage.quitBrowserSession();
	}

}
