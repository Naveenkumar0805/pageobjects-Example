package goldsilverPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;


public class silverratetable {
	
    private WebElement table;

    public silverratetable(WebElement table) {
        this.table = table;
    }
    
    public void printTableData() {
        List<List<String>> data = extractTableData();
        for (List<String> rowData : data) {
            for (String cellData : rowData) {
                System.out.print(cellData + "\t"); // Print each cell's data with a tab separator
            }
            System.out.println(); // Move to the next line for the next row
        }
    }

    public List<List<String>> extractTableData() {
        List<List<String>> data = new ArrayList<>();
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<String> rowData = new ArrayList<>();
            List<WebElement> columns = row.findElements(By.tagName("td"));
            for (WebElement column : columns) {
                rowData.add(column.getText());
            }
            data.add(rowData);
        }
        return data;
    }
}

