package com.nuna.genlib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author Ruchika Sharma
 * 
 * class to read data from excel using Apache POI and storing Map of Map
 */
public class ReadExcelData {
	
	
	
	public static XSSFSheet getSheet() throws FileNotFoundException {
		XSSFWorkbook workbook=null;
		LinkedHashMap<String, String> hm= new LinkedHashMap<>();
	
	//Path of the excel file
	FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/testdata/testdata.xlsx");
	//Creating a workbook
	
	try {
		workbook = new XSSFWorkbook(fs);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	XSSFSheet sheet = workbook.getSheetAt(0);
		return sheet;
	
	}
	public static void main (String[] args) throws FileNotFoundException {
	//	XSSFWorkbook workbook=null;
		 Map<String, Map<String, String>> completeSheetData = new HashMap<String, Map<String, String>>();
	
		 try {
			completeSheetData= getExcelAsMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		for(String i: completeSheetData.keySet()) {
			System.out.println(i);
			System.out.println(completeSheetData.get(i).get("source language"));
			System.out.println(completeSheetData.get(i).get("translation language"));
			System.out.println(completeSheetData.get(i).get("initial text"));
			System.out.println(completeSheetData.get(i).get("translated text"));
		}
//		for(Map val: completeSheetData) {
//			System.out.println(val.get(val));
//		}


    }
    
    public static Map<String, Map<String, String>> getExcelAsMap() throws IOException {
    	
        XSSFSheet sheet = getSheet();
        Map<String, Map<String, String>> completeSheetData = new HashMap<String, Map<String, String>>();
        List<String> columnHeader = new ArrayList<>();
        Row row = sheet.getRow(0);
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            columnHeader.add(cellIterator.next().getStringCellValue());
        }
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        int columnCount = row.getLastCellNum();
        
        for (int i = 1; i <= rowCount; i++) {
            Map<String, String> singleRowData = new HashMap<>();
            Row row1 = sheet.getRow(i);
            for (int j = 0; j < columnCount; j++) {
                Cell cell = row1.getCell(j);
            //    System.out.println(cell.getCellType());
                
                if(cell.getCellType().equals(CellType.NUMERIC)) {
                	  singleRowData.put(columnHeader.get(j),String.valueOf(cell.getNumericCellValue()));
                }
                else {
                	 singleRowData.put(columnHeader.get(j),cell.getStringCellValue());
                }
            }
            completeSheetData.put(String.valueOf(i), singleRowData);
        }
        return completeSheetData;	
	}
}
