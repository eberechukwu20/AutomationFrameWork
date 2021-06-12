package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.data.AlertOption;
import com.wrapper.Elements;

public class MyAddressPage extends Elements {
	private static final By NEW_ADDRESS_BUTTON_LOCATOR = By.xpath("//span[text()='Add a new address']");
	private static final By ADDRESS_HEADING_LOCATOR = By.xpath("//h3[@class=\"page-subheading\"]");
	private static final By UPDATE_ADDRESS_LINK_LOCATOR = By.xpath("//span[text()='Update']/..");
	private static final By DELETE_ADDRESS_LINK_LOCATOR = By.xpath("//a[@title=\"Delete\"]");

	public AddNewAddresPage goToNewAddressPage() {
		clickOnElement(NEW_ADDRESS_BUTTON_LOCATOR);
		return new AddNewAddresPage();
	}

	public boolean isAddressPresent(String heading) {
		List<WebElement> elementList = getAllElements(ADDRESS_HEADING_LOCATOR);
		String elementText;
		boolean isFound = false;
		for (int index = 0; index < elementList.size(); index++) {
			elementText = elementList.get(index).getText();
			if (elementText.equalsIgnoreCase(heading)) {
				isFound = true;
				break;
			}
		}
		return isFound;

	}

	public AddNewAddresPage updateAddressPage(String heading) {
		// TODO Auto-generated method stub
		By addressBox = By.xpath("//h3[@class='page-subheading' and text()='" + heading + "']/../..");
		WebElement addressBoxElement = getElement(addressBox);
		WebElement updateButtonLink = getElement(addressBoxElement, UPDATE_ADDRESS_LINK_LOCATOR);
		clickOnElement(updateButtonLink);
		return new AddNewAddresPage();
	}

	public MyAddressPage deleteAddress(String heading, AlertOption option) {
		By addressBox = By.xpath("//h3[@class='page-subheading' and text()='" + heading + "']/../..");
		WebElement addressBoxElement = getElement(addressBox);
		WebElement deleteButtonLink = getElement(addressBoxElement, DELETE_ADDRESS_LINK_LOCATOR);
		clickOnElement(deleteButtonLink);
		clickOnAlert(option);
		return new MyAddressPage();

	}

}
