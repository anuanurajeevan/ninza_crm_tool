package com.ninza.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	
	public String toReadTheDataFromExcel(String sheet, int rowNum, int cellNum) 
            throws EncryptedDocumentException, IOException {
        
        FileInputStream fs = new FileInputStream("./src/test/resources/Book1.xlsx");
        Workbook wb = WorkbookFactory.create(fs);
        String data = wb.getSheet(sheet).getRow(rowNum).getCell(cellNum).getStringCellValue();
        wb.close();
        return data;
    }
    
    public int toGetRowCount(String sheet) throws EncryptedDocumentException, IOException {
    	FileInputStream fs = new FileInputStream("./src/test/resources/Book1.xlsx");
		Workbook wb = WorkbookFactory.create(fs);
		 int lastRowNum = wb.getSheet(sheet).getLastRowNum();
    	return lastRowNum;
    }
    
    public List<String> toReadMultipleSetOfData(String sheet, int cellNum, int lastRowNum) throws EncryptedDocumentException, IOException {
    	List<String> ls = new ArrayList<String>();
    	FileInputStream fs = new FileInputStream("./src/test/resources/Book1.xlsx");
		Workbook wb = WorkbookFactory.create(fs);
		for (int i = 1; i <=lastRowNum; i++) {
			String data = wb.getSheet(sheet).getRow(i).getCell(cellNum).getStringCellValue();
			ls.add(data);	
		}
		return ls;
    }
//   public int toWriteBackToExcel() {
//	   FileOutputStream fos = new FileOutputStream("./resources/Book1.xlsx");
//	   Workbook wb = WorkbookFactory.create(fs);
//		Cell c = wb1.getSheet("CampProducts").getRow(1).createCell(4);
//		c.setCellType(CellType.STRING);
//		c.setCellValue(msg);
//		wb1.write(fos);
//   }

}
