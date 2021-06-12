package com.examples;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class VerifyIfLinksAreWorking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver wd = new ChromeDriver();
		wd.get("http://139.59.91.96:5001/selenium-workbook/");
		By allLinkslocator = By.xpath("//a[@href]");
		List<WebElement> allLinksElements = wd.findElements(allLinkslocator);
		for (WebElement element : allLinksElements) {
			//System.out.println(element.getAttribute("href"));
			RestAssured.baseURI = element.getAttribute("href");
			RequestSpecification request = RestAssured.given();
			Response response = request.get();
			if (response.getStatusCode() == 200) {
				System.out.println(element.getAttribute("href") + "works fine!!");
			} else {
				System.out.println(element.getAttribute("href") +"Link is broken");
			}
		}
	}

}
