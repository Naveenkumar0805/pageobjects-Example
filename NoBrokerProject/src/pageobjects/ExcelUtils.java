package pageobjects;



import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;


import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static int rowNum = 1; // Start from row 1 (0 is for header)

    public static void initializeExcel(String filePath, String sheetName) {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Rental Properties");

        // Create header row with column names
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Test");
        headerRow.createCell(1).setCellValue("Property Type");
        headerRow.createCell(2).setCellValue("Rent");
        headerRow.createCell(3).setCellValue("Deposit");
        headerRow.createCell(4).setCellValue("Built-up Area");
        headerRow.createCell(5).setCellValue("Furnishing Type");

        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\Admin\\Desktop\\New folder\\objectnobroker.xlsx")) {
            workbook.write(fos);
            workbook.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToExcel(String testName, String propertyType, String rent, String deposit, String builtUp, String furnishingType) {
        XSSFRow row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(testName);
        row.createCell(1).setCellValue(propertyType);
        row.createCell(2).setCellValue(rent);
        row.createCell(3).setCellValue(deposit);
        row.createCell(4).setCellValue(builtUp);
        row.createCell(5).setCellValue(furnishingType);
    }

    public static void saveExcelChanges(String filePath) {
        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\Admin\\Desktop\\New folder\\objectnobroker.xlsx")) {
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


