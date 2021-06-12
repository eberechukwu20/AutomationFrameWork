package com.univadis;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.data.BrowserName;
import com.data.Devices;

import com.utilities.Utilities;

public class LoginTest {
	LoginPage loginPage;

	@BeforeMethod(description = "sets up the requiremnt for the tests")
	public void setup() {
		LandingPage landingPage = new LandingPage();
		landingPage.launchBrowser(BrowserName.CHROME);
		landingPage.setScreenSize(Devices.PC);
		landingPage.loadPage("https://www.univadis.co.uk/");
		loginPage = landingPage.goToLoginPage();

	}

	@Test
	public void loginTest() {
		HomePage homePage = loginPage.doLogin("jatinvsharma@gmail.com", "Qweqwe`1");
		Assert.assertEquals(homePage.getUserName(), "Jatin Sharma");
	}
}
