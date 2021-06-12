package com.examples;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatePickerCode {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver wd = new ChromeDriver();
		wd.get("http://139.59.91.96:5001/selenium-workbook/datepicker.html");

		By inputTextBoxLocator = By.id("datepicker");
		WebElement dateTextBoxLocator = wd.findElement(inputTextBoxLocator);
		dateTextBoxLocator.click();

		By calendarLocator = By.className("ui-datepicker-calendar");
		WebElement calendar = wd.findElement(calendarLocator);
		String date= "10";
		Date d = new Date();
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		String systemDate =dateFormat.format(d);
		if(Integer.parseInt(systemDate)<10)
		{
			systemDate=	systemDate.substring(1);
		}
		System.out.println();
		
		
		By dateLocator = By.xpath("//a[text()='"+systemDate+"']");
		WebElement dateElement = calendar.findElement(dateLocator);
		dateElement.click();

	}

}
