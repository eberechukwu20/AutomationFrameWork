package com.examples;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver wd = new ChromeDriver();
		wd.get("http://139.59.91.96:5001/selenium-workbook/popups.html#");
		// By alertLocator = By.id("alert");
		By alertLocator = By.xpath("//a[text()='Confirm']");
		WebElement element = wd.findElement(alertLocator);
		element.click();
		// JavaScript or Browser Page Alert
		//switchTo is a method in TargetLocator
		Alert myAlert = wd.switchTo().alert(); // frame handles(tabs) alerts
		String myData = myAlert.getText();
		System.out.println(myData);
		myAlert.dismiss();
		By modalButtonLocator = By.xpath("//a[text()='Modal']");
		WebElement modalElement = wd.findElement(modalButtonLocator);
		modalElement.click();
		//WebBased ALerts  Modals: ELements present on the page
		By modalLocator = By.className("modal-content");
		WebElement modal = wd.findElement(modalLocator);

		By modalHeading = By.id("myModalLabel");
		WebElement heading = modal.findElement(modalHeading);
		System.out.println(heading.getText());
		By inputTextBoxLocator = By.tagName("input");
		WebElement inputElement = modal.findElement(inputTextBoxLocator);
		inputElement.sendKeys("Mahendra");

		By okButtonLocator = By.xpath("//button[text()='Cancel']");
		WebElement okayButtonELement = wd.findElement(okButtonLocator);
		okayButtonELement.click();
	}

}
