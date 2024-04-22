package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

public static void write(String testScenoriaNumber, String sheetName, int rowNo, int col, String data) throws IOException {
		
		
		String filePath = "./excelOutput/TS_" + testScenoriaNumber + "_Outputs.xlsx";
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook wbook = new XSSFWorkbook(file);
		
		//Creating sheet if doesn't exist
		if(wbook.getSheetIndex(sheetName)==-1) {
			wbook.createSheet(sheetName);
		}
		
		//Creating row
		XSSFSheet sheet = wbook.getSheet(sheetName);
		if(sheet.getRow(rowNo)==null) {
			sheet.createRow(rowNo);
		}
		
		//Creating cell
		XSSFRow row = sheet.getRow(rowNo);
		XSSFCell cell = row.createCell(col);
		
		//Setting the data to the cell
 		cell.setCellValue(data);
		
 		//Writing the data through FileOutputStream
		FileOutputStream fos = new FileOutputStream(filePath);
		wbook.write(fos);
		wbook.close();
	}
}

