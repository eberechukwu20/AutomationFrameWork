package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.wrapper.Elements;

public class LandingPage extends Elements {

	private static final By SIGN_IN_LINK = By.xpath("//a[contains(text(),'Sign in')]");

	public SignInPage goToSignInPage() {
		clickOnElement(SIGN_IN_LINK);
//		SignInPage s = new SignInPage();
//		return s;
		return new SignInPage();
	}

}
