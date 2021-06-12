package com.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import io.restassured.path.json.JsonPath;

public final class Utilities {

	public static String readConfigFile(String key) {
		/*
		 * File Reference Locate the file:
		 * System.getProperty("user.dir")-------------->Complete Path of the project
		 * FileReader Reference Properties Reference
		 * 
		 * 
		 * 
		 */

		File file = new File(System.getProperty("user.dir") + "//config//config.properties");
		FileReader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String value = prop.getProperty(key);

		return value;

	}

	public static void readFile(String fileName) {
		// TODO Auto-generated method stub
		// Step1 ---> File Path??? Where the file is
		// C:\Users\jatin\Desktop\batches\at_batch_nov_20\AtBatchOctFileUtility\testData\demo.txt

		String filePath = System.getProperty("user.dir") + "\\testData\\" + fileName;

//		String autoPath = System.getProperty("user.dir");/ 	C:\Users\jatin\Desktop\batches\at_batch_nov_20\AtBatchOctFileUtility
		File myFile = new File(filePath); // Reference of File Class
		Scanner scanner;// Declaring
		String data = "";
		try {
			scanner = new Scanner(myFile);// Intializing
			while (scanner.hasNext()) {
//				System.out.println("Line ->" + scanner.nextLine());
				data = data + scanner.nextLine();
				data = data + "\n"; // \n ---->Enter
			}
			System.out.println(data);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Invalid File Path!! or file Not found");
		}

	}

	public static void createFolder(String folderName) {
		// TODO Auto-generated method stub
		File myFolderPath = new File(System.getProperty("user.dir") + "\\" + folderName);

		if (myFolderPath.exists()) {
			System.out.println("Folder Present");
			if (myFolderPath.delete()) {
				System.out.println("Folder Deleted Successfully!!");
			} else {
				System.err.println("Cannot delete the folder!!");
			}
		}

		myFolderPath.mkdir();

	}

	public static void readJsonFile(String fileName, String key) {
		String filePath = System.getProperty("user.dir") + "\\testData\\" + fileName;

		File myFile = new File(filePath); // Reference of File Class

		JsonPath myJsonPath = new JsonPath(myFile);
		String value = myJsonPath.getString(key);
		System.out.println(value);

	}

	public static Iterator<String[]> readCSVFile(String fileName) {

		File myFile = new File(System.getProperty("user.dir") + "\\testData\\" + fileName);
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(myFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CSVReader csvReader = new CSVReader(fileReader);
		List<String[]> myCSVData = null;
		try {
			myCSVData = csvReader.readAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator<String[]> myCSVDataIterator = myCSVData.iterator();
//		while(myCSVDataIterator.hasNext()) {
//			//Reading'
//			String [] myDataArray=myCSVDataIterator.next();//Important Statement
//			for (String data:myDataArray) {
//				System.out.print(data+"\t");
//			}
//			System.out.println("");
//		}
		myCSVDataIterator.next();// 0-->1
		return myCSVDataIterator;
	}

	public static String[][] readExcel(String fileName, String sheetName) throws InvalidFormatException, IOException {

		File myFile = new File(System.getProperty("user.dir") + "\\testData\\" + fileName);
		XSSFWorkbook myWorkbook = new XSSFWorkbook(myFile);

		XSSFSheet mySheet = myWorkbook.getSheet(sheetName);
		int lastRowIndex = mySheet.getLastRowNum();
		System.out.println(lastRowIndex); //0 1 2 3

		XSSFRow rowHeader = mySheet.getRow(0);
		int totalColNumber = rowHeader.getLastCellNum();
		System.out.println(totalColNumber);
		String myData[][] = new String[lastRowIndex][totalColNumber];
		XSSFRow myRow;
		XSSFCell myCell;
		for (int rowIndex = 1; rowIndex <= lastRowIndex; rowIndex++) {
			for (int colIndex = 0; colIndex < totalColNumber; colIndex++) {
				myRow = mySheet.getRow(rowIndex);
				myCell = myRow.getCell(colIndex);
				System.out.print(myCell.toString() + "\t");
				myData[rowIndex-1][colIndex] =myCell.toString();
			}
			System.out.println("");
		}
		return myData;
	}

}
