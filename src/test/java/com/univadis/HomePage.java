package com.univadis;

import org.openqa.selenium.By;

import com.wrapper.Elements;

public class HomePage extends Elements {

	private static final By USER_NAME_LOCATOR = By.xpath("//span[@class=\"user-account__user-name\"]");

	public String getUserName() {
		return getElementAttribute(USER_NAME_LOCATOR, "data-fullname");
	}

}
