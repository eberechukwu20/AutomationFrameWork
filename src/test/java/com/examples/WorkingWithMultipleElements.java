package com.examples;

import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkingWithMultipleElements {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver wd = new ChromeDriver();
		wd.get("http://139.59.91.96:5001/selenium-workbook/shopping-cart.html");
		By itemPriceLocator = By.xpath("//table/tbody/re");
		WebElement elementq=wd.findElement(itemPriceLocator); // will return the FIRST WEB ELEMENT IN THE PAGE WITH given locator
		System.out.println(elementq.getText());
		
		List<WebElement> myelements= wd.findElements(itemPriceLocator);
		HashSet<String> uniquePrice = new HashSet<String>();
		String number;
		int total =0;
		for( WebElement element:myelements) {
			number = element.getText().substring(1);
			uniquePrice.add(number);
			int s=Integer.parseInt(number);
			total = total+s;
		}
		System.out.println(total);
		
		System.out.println(uniquePrice);
		
		
	}

}
