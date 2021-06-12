package com.examples;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkingwithHandles {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(5000);
		WebDriverManager.chromedriver().setup();
		WebDriver wd = new ChromeDriver();
		wd.get("http://139.59.91.96:5001/selenium-workbook/open-a-new-window.html#");
		By newButtonLocator = By.linkText("Open A New Window");
		WebElement newButton = wd.findElement(newButtonLocator);
		newButton.click();
	//Step1: Get the current Handle----> String
		String currenthandle=wd.getWindowHandle(); //handle for the current page which selenium is automating
		System.out.println(currenthandle);
	//Step 2: Get All the window handles------------Set<String>
		Set<String> allHandles=wd.getWindowHandles();
		System.out.println(allHandles);
		//Iterate 
		for(String handle:allHandles)
		{
			if(!currenthandle.equalsIgnoreCase(handle)) 
			{
				wd.switchTo().window(handle);
				if(wd.getTitle().equalsIgnoreCase("Open a new window")) { //Step 3: for 3 handles
					By h1Tag = By.tagName("h1");
					WebElement h1Element = wd.findElement(h1Tag);
					System.out.println(h1Element.getText());
				}
				else {
					System.out.println(wd.getTitle());
				}
			}
		}
		
	}

}
