package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.wrapper.Elements;

public class SignInPage extends Elements {

	private static final By EMAIL_ADDRESS_TEXTBOX_LOCATOR = By.id("email");
	private static final By PASSWORD_TEXTBOX_LOCATOR = By.id("passwd");
	private static final By SIGN_IN_BUTTON_LOCATOR = By.id("SubmitLogin");
	private static final By REG_EMAIL_ADDRESS_TEXTBOX_LOCATOR = By.id("email_create");
	private static final By CREATE_ACCOUNT_BUTTON_LOCATOR = By.id("SubmitCreate");
	private static final By FORGOT_PASSWORD_LINK = By.linkText("Forgot your password?");
	
	

	public MyAccountPage doLogin(String userName, String password) {
		enterText(EMAIL_ADDRESS_TEXTBOX_LOCATOR, userName);
		enterText(PASSWORD_TEXTBOX_LOCATOR, password);
		clickOnElement(SIGN_IN_BUTTON_LOCATOR);
//		MyAccountPage account = new MyAccountPage();
//		return account;
		return new MyAccountPage();
	}

	public void doRegistration(String customerEmailAddress) {
		enterText(REG_EMAIL_ADDRESS_TEXTBOX_LOCATOR, customerEmailAddress);
		clickOnElement(CREATE_ACCOUNT_BUTTON_LOCATOR);
	}

	public void forgotPassword() {
		clickOnElement(FORGOT_PASSWORD_LINK);
	}

}
