package com.dataproviders;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;

import com.utilities.Utilities;

public class CalculatorDataProvider {

	@DataProvider(name = "AddDP")
	public Iterator<String[]> addDataProviderMethod() {
		return Utilities.readCSVFile("calData.csv");
	}

	// DataProvider Methods:
	// 3 format----------------------> String[] /[][] , Iterator<>, Object[]/ [][]

	@DataProvider(name = "SubDP")
	public String[][] subDataProvider() throws InvalidFormatException, IOException {
		return Utilities.readExcel("calExcel.xlsx", "sub");
	}
}
