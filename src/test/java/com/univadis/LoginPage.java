package com.univadis;

import org.openqa.selenium.By;

import com.wrapper.Elements;

public class LoginPage extends Elements {
	private static final By USER_NAME_TEXTBOX_LOCATOR = By.name("userId");
	private static final By PASSWORD_TEXTBOX_LOCATOR = By.name("password");
	private static final By LOGIN_BUTTON_LOCATOR = By.xpath("//button/span[contains(text(),\"Log In\")]/..");

	public HomePage doLogin(String emailAddress, String password) {
		
		enterText(USER_NAME_TEXTBOX_LOCATOR, emailAddress);
		enterText(PASSWORD_TEXTBOX_LOCATOR, password);
		clickOnElement(LOGIN_BUTTON_LOCATOR);
		return new HomePage();

	}

}
