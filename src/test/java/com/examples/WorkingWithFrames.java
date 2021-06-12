package com.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkingWithFrames {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver wd = new ChromeDriver();
		wd.get("http://139.59.91.96:5001/selenium-workbook/wyswyg-editor.html");
		
		By headingPageLocator = By.tagName("h1");
		WebElement headingElement = wd.findElement(headingPageLocator);
		System.out.println(headingElement.getText());
		wd.switchTo().frame("editor_ifr");
		By editorLocator = By.id("tinymce");
		WebElement editor = wd.findElement(editorLocator);
		editor.sendKeys(Keys.CONTROL+"i");
		editor.sendKeys("abc");
		wd.switchTo().defaultContent();//main document or takes you the parent frame
		System.out.println(headingElement.getText());
		
	}

}
