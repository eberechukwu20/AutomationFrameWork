package com.univadis;

import org.openqa.selenium.By;

import com.wrapper.Elements;

public class LandingPage extends Elements {

	private static final By LOGIN_LINK_LOCATOR = By.xpath("//a[@title=\"Login\"]");
	private static final By ACCEPT_COOKIES_BUTTON_LOCATOR = By.xpath("//button[text()='I Accept']");

	public LoginPage goToLoginPage() {
		clickOnElement(ACCEPT_COOKIES_BUTTON_LOCATOR);
		clickOnElement(LOGIN_LINK_LOCATOR);
		return new LoginPage();
	}
}
