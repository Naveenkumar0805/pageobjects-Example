package flipkartPOM;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriter {

	public Workbook workbook;
	public Sheet sheet;
	public int currentRow;

	public ExcelWriter(String filePath) {
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Results");
		currentRow = 0;
		
		// Create headers for the columns
        Row headerRow = sheet.createRow(currentRow++);
        String[] headers = {"Product Name", "Product Price", "Model Name", "Model Number"};
        int cellNum = 0;
        for (String header : headers) {
            Cell cell = headerRow.createCell(cellNum++);
            cell.setCellValue(header);
        }
	}

        public void addRow(String productName, String productPrice, String modelName, String modelNumber) {
            Row row = sheet.createRow(currentRow++);
            int cellNum = 0;
            
            // Add data to cells
            Cell productNameCell = row.createCell(cellNum++);
            productNameCell.setCellValue(productName);
            
            Cell productPriceCell = row.createCell(cellNum++);
            productPriceCell.setCellValue(productPrice);
            
            Cell modelNameCell = row.createCell(cellNum++);
            modelNameCell.setCellValue(modelName);
            
            Cell modelNumberCell = row.createCell(cellNum);
            modelNumberCell.setCellValue(modelNumber);
        }
		
	

	public void save() throws IOException {
		try (FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Admin\\Desktop\\New folder\\objectflipkart.xlsx")) {
			workbook.write(fileOut);
		}
	}
}


