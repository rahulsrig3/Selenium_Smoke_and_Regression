package com.crm.GenericLibraries;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * this class contains generic methods that will read and write data on to the excel sheet
 * @author Rahul Srivastava
 *
 */

public class ExcelUtility {

	/**
	 * This method will read data from excel sheet and return the cell value
	 * @param sheet
	 * @param row
	 * @param cell
	 * @return
	 * @throws Throwable
	 * @throws IOException
	 */
	public String getDataFromExcel(String sheet, int row, int cell) throws Throwable, IOException
	{
		FileInputStream fis = new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheet);
		Row rw = sh.getRow(row);
		Cell ce = rw.getCell(cell);
		String value = ce.getStringCellValue();
		return value;
	}
	
	/**
	 * This method will return the total row count
	 * @param sheet
	 * @return
	 * @throws Throwable
	 */
	public int getRowCount(String sheetName) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		wb.close();
		int rc = sh.getLastRowNum();
		return rc;
	}
	
	/**
	 * This method will write the data onto the excel sheet
	 * @param sheet
	 * @param rowNo
	 * @param cellNo
	 * @param data
	 * @throws Throwable
	 */
	
	public void writeDataToExcel(String sheet, int rowNo, int cellNo, String data) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheet);
		Row r = sh.getRow(rowNo);
		Cell c = r.getCell(cellNo);
		c.setCellValue(data);
		
		FileOutputStream fos = new FileOutputStream(IPathConstants.FilePath);
		wb.write(fos);
		wb.close();
	}

}
