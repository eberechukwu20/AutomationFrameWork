package com.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.data.AlertOption;
import com.data.BrowserName;
import com.pages.AddNewAddresPage;
import com.pages.LandingPage;
import com.pages.MyAccountPage;
import com.pages.MyAddressPage;
import com.pages.SignInPage;
import com.utilities.Utilities;

@Listeners(com.listeners.MyCustomListeners.class)
public class AddNewAddressTest {
	private SignInPage signInPage;
	private MyAccountPage myAccountPage;
	private AddNewAddresPage addNewAddresPage;
	private MyAddressPage myAddressPage;

	@BeforeMethod(description = "setup for the address test")
	public void setup() {
		LandingPage landingPage = new LandingPage();
		landingPage.launchBrowser(BrowserName.CHROME);
		landingPage.loadPage(Utilities.readConfigFile("URL"));
		signInPage = landingPage.goToSignInPage();
		myAccountPage = signInPage.doLogin(Utilities.readConfigFile("BASE_USER"),
				Utilities.readConfigFile("BASE_PASSWORD"));
		myAddressPage = myAccountPage.goToMyAddressPage();

	}

	@Test(testName = "Add new Address Test", description = "verifies if the user can add new address")
	public void addNewAddressTest() {
		addNewAddresPage = myAddressPage.goToNewAddressPage();
		MyAddressPage myAddressPage = addNewAddresPage.addNewAddress("Infosys", "ABC 123", "PQE 434", "mumbai", 5,
				"34546", "1223445", "9999999999", "Demo", "office 11111");
		Assert.assertTrue(myAddressPage.isAddressPresent("office 11111"));

	}

	@Test(testName = " Update specific address", description = "Update address based on Alias")
	public void updateAddressTest() {

		AddNewAddresPage addNewAddresPage = myAddressPage.updateAddressPage("office address2431");
		MyAddressPage myAddressPage = addNewAddresPage.addNewAddress("CG", "ABC 123", "PQE 434", "mumbai", 5, "34546",
				"1223445", "9999999999", "Demo", "office 454656");
		Assert.assertTrue(myAddressPage.isAddressPresent("office 454656"));

	}

	@Test(testName = " Delete specific address", description = "Delete address based on Alias")
	public void deleteAddressTest() {
		MyAddressPage myaddressNew = myAddressPage.deleteAddress("office 11111", AlertOption.ACCEPT);
		Assert.assertFalse(myaddressNew.isAddressPresent("office 11111"));

	}

	@AfterMethod(description = "close the session")
	public void tearDown() {
		myAddressPage.quitBrowserSession();
	}
}
