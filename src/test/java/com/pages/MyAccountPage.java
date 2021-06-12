package com.pages;

import org.openqa.selenium.By;

import com.wrapper.Elements;

public class MyAccountPage extends Elements {
	private static final By USERNAME_SPAN_LOCATOR = By.xpath("//a[@class='account']/span");
	private static final By MYADDRESS_BUTTON_LOCATOR = By.xpath("//span[text()='My addresses']");

	public String getUserName() {
		return getTextFromElement(USERNAME_SPAN_LOCATOR);
	}

	public MyAddressPage goToMyAddressPage() {
		clickOnElement(MYADDRESS_BUTTON_LOCATOR);
		return new MyAddressPage();
	}
}
