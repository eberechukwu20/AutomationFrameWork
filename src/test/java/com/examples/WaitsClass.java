package com.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utilities.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WaitsClass {
	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver wd = new ChromeDriver();
		String timeSec= Utilities.readConfigFile("WAIT_TIME");
		long time = Long.parseLong(timeSec);
		WebDriverWait wait = new WebDriverWait(wd, time);
		wd.get("http://139.59.91.96:5001/selenium-workbook/slow-loading-elements.html");
		By fadeInButtonLocator = By.id("fadeInText");
		//WebElement button = wd.findElement(fadeInButtonLocator); // non sync
		WebElement button2 = wait.until(ExpectedConditions.elementToBeClickable(fadeInButtonLocator)); //sync
		button2.click();
		By textLocator = By.id("theText");
		WebElement textElement = wd.findElement(textLocator);
		WebElement textElement2 =	wait.until(ExpectedConditions.visibilityOfElementLocated(textLocator));
		System.out.println(textElement2.getText());
		


	}
}
