package com.wipro.opencartTestCase;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelData {
	
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	
	//Reading the excel for Registration
	
	public static Object[][] reviewdata(String SheetName) throws Exception
	{
	// TODO Auto-generated method stub
	File fil = new File("D://testdata.xlsx");
	FileInputStream fis = new FileInputStream(fil);
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	XSSFSheet sh = wb.getSheet(SheetName);
	int rowcount = sh.getLastRowNum();
	Row fistrow = sh.getRow(0);
	int colcount = fistrow.getLastCellNum();
	Object[][] obj = new Object[rowcount][colcount];
	for(int i=1; i<=rowcount; i++)
	{
	Row row = sh.getRow(i);
	for(int j=0; j<row.getLastCellNum();j++)
	{
	Cell cell = row.getCell(j);
	System.out.print(cell+" ");
	obj[i-1][j]=cell.getStringCellValue();
	}
	System.out.print("\n");
	}
	System.out.println(obj);

	return obj;
	}
}
