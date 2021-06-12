package com.dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.utilities.Utilities;

public class UserLoginDataProvider {
	
	@DataProvider(name="login data provider")
	public Iterator<String[]> loginTestDataProvider() {
		return Utilities.readCSVFile("loginData.csv");
	}

	
	
}
