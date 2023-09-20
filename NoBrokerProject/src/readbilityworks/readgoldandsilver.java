package readbilityworks;


import java.io.File;  

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class readgoldandsilver {

	public static void main(String[] args) throws IOException, InterruptedException{

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sh = wb.createSheet("Gold Rate");
		System.setProperty("webdriver.chrome.driver", "E:\\chrome driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.google.co.in");

		//enter search term
		//click the link


		WebElement liverate = driver.findElement(By.name("q"));
		liverate.sendKeys("live gold rates in chennai"+Keys.ENTER);

		WebElement todaysrate = driver.findElement(By.partialLinkText("Todays Gold Rate"));
		todaysrate.click();
		Thread.sleep(3000);

		WebElement table =	 driver.findElement(By.xpath("(//table[@class='table-price'])[2]"));
		List<WebElement> totalRows = table.findElements(By.tagName("tr"));
		for(int row=0; row<totalRows.size(); row++)
		{
			XSSFRow rowValue = sh.createRow(row);
			List<WebElement> totalColumns = totalRows.get(row).findElements(By.tagName("td"));
			for(int col=0; col<totalColumns.size(); col++)
			{
				String cellValue = totalColumns.get(col).getText();
				System.out.print(cellValue + "\t");
				rowValue.createCell(col).setCellValue(cellValue);
			}
			System.out.println();
		}


		XSSFSheet sh1 = wb.createSheet(" Silver Rate");
		WebElement table1 =	 driver.findElement(By.xpath("(//table[@class='table-price'])[4]"));
		List<WebElement> totalRows1 = table1.findElements(By.tagName("tr"));
		for(int rows=0; rows<totalRows1.size(); rows++)
		{
			XSSFRow rowValue1 = sh1.createRow(rows);
			List<WebElement> totalColumns1 = totalRows1.get(rows).findElements(By.tagName("td"));
			for(int cols=0; cols<totalColumns1.size(); cols++)
			{
				String cellValue1 = totalColumns1.get(cols).getText();
				System.out.print(cellValue1 + "\t");
				rowValue1.createCell(cols).setCellValue(cellValue1);
			}
			System.out.println();
		}
		FileOutputStream fos1 = new FileOutputStream("C:\\Users\\Admin\\Desktop\\New folder\\gold.xlsx");
		wb.write(fos1);
		wb.close(); 


	}

}

