package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.wrapper.Elements;

public class AddNewAddresPage extends Elements {
	private static final By COMPANY_TEXTBOX_LOCATOR = By.id("company");
	private static final By ADDRESSLINE1_TEXTBOX_LOCATOR = By.id("address1");
	private static final By ADDRESSLINE2_TEXTBOX_LOCATOR = By.id("address2");
	private static final By CITY_TEXTBOX_LOCATOR = By.id("city");
	private static final By STATE_DROPDOWN_LOCATOR = By.id("id_state");
	private static final By ZIPCODE_TEXTBOX_LOCATOR = By.id("postcode");
	private static final By PHONE_NUMBER_TEXTBOX_LOCATOR = By.id("phone");
	private static final By MOBILE_NUMBER_TEXTBOX_LOCATOR = By.id("phone_mobile");
	private static final By OTHER_INFO_TEXTAREA_LOCATOR = By.id("other");
	private static final By ALIAS_TEXTBOX_LOCATOR = By.id("alias");
	private static final By SAVE_BUTTON_LOCATOR = By.xpath("//span[contains(text(),\"Save\")]");

	public MyAddressPage addNewAddress(String company, String address1, String address2, String city, int stateOption,
			String zipCode, String phoneNumber, String mobileNumber, String others, String alias) {
		enterText(COMPANY_TEXTBOX_LOCATOR, company);
		enterText(ADDRESSLINE1_TEXTBOX_LOCATOR, address1);
		enterText(ADDRESSLINE2_TEXTBOX_LOCATOR, address2);
		enterText(CITY_TEXTBOX_LOCATOR, city);
		selectFromDropDown(3, STATE_DROPDOWN_LOCATOR);
		clearText(ZIPCODE_TEXTBOX_LOCATOR);
		enterText(ZIPCODE_TEXTBOX_LOCATOR, zipCode);
		enterText(PHONE_NUMBER_TEXTBOX_LOCATOR, phoneNumber);
		enterText(MOBILE_NUMBER_TEXTBOX_LOCATOR, mobileNumber);
		clearText(ALIAS_TEXTBOX_LOCATOR);
		enterText(ALIAS_TEXTBOX_LOCATOR, alias);
		enterText(OTHER_INFO_TEXTAREA_LOCATOR, others);
		clickOnElement(SAVE_BUTTON_LOCATOR);
		return new MyAddressPage();
	}

}
