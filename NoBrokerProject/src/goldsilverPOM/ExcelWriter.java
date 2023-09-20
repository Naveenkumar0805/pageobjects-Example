package goldsilverPOM;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class ExcelWriter {
	
    private XSSFWorkbook workbook;

    public ExcelWriter() {
        workbook = new XSSFWorkbook();
    }

    public XSSFSheet createSheet(String sheetName) {
        return workbook.createSheet(sheetName);
    }

    public void writeData(XSSFSheet sheet, List<List<String>> data) {
        int rowNum = 0;
        for (List<String> rowData : data) {
            XSSFRow excelRow = sheet.createRow(rowNum);
            int colNum = 0;
            for (String cellData : rowData) {
                XSSFCell excelCell = excelRow.createCell(colNum);
                excelCell.setCellValue(cellData);
                colNum++;
            }
            rowNum++;
        }
    }

    public void saveExcel(String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Admin\\Desktop\\New folder\\objectgoldandsilver.xlsx");
        workbook.write(fos);
        workbook.close();
        fos.close();
    }
}



