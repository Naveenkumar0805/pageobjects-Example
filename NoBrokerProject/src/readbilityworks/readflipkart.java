package readbilityworks;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class readflipkart {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "E:\\chrome driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("http://www.google.co.in");

		WebElement opensite = driver.findElement(By.id("APjFqb"));
		opensite.sendKeys("flipkart"+Keys.ENTER);

		WebElement flipkartsite = driver.findElement(By.className("H9lube"));
		flipkartsite.click();


		WebElement login = driver.findElement(By.xpath("//button[contains(text(),'✕')]"));
		login.click();

		WebElement homeappliances = driver.findElement(By.xpath("//div[contains(text(),'Home')]"));	    
		Actions homedecor = new Actions(driver);
		homedecor.moveToElement(homeappliances).build().perform();

		WebElement homeitems = driver.findElement(By.xpath("//*[contains(text(),'Home Decor')]"));	 
		Actions selectprice = new Actions(driver);
		selectprice.moveToElement(homeitems).click().build().perform();
		Thread.sleep(5000);

		WebElement price = driver.findElement(By.xpath("(//section//span[contains(text(),'Price')]/ancestor::section//select)[2]"));
		Select rate = new Select(price);
		rate.selectByValue("2000");
		Thread.sleep(2000);

		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='_1AtVbE col-12-12']/div[@class='_13oc-S']"));
		System.out.println(rows.size());
		Thread.sleep(2000);
		
		// Create a new workbook and sheet
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Product Details");
		
		// Create a bold font for the header cells
		XSSFFont boldFont = workbook.createFont();
		boldFont.setBold(true);
		
		// Create a cell style with the bold font, centered alignment, and text wrapping
		XSSFCellStyle boldCellStyle = workbook.createCellStyle();
		boldCellStyle.setFont(boldFont);
		boldCellStyle.setAlignment(HorizontalAlignment.CENTER);
		boldCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		boldCellStyle.setWrapText(true); // Text wrapping to fit in the cell


		/*
		 * // Create a header row Row headerRow = sheet.createRow(0);
		 * headerRow.createCell(0).setCellValue("Product Name");
		 * headerRow.createCell(1).setCellValue("Price");
		 * headerRow.createCell(2).setCellValue("Model Name");
		 * headerRow.createCell(3).setCellValue("Model Number");
		 */
		
		// Create a header row
		Row headerRow = sheet.createRow(0);
		Cell cellProductName = headerRow.createCell(0);
		Cell cellProductPrice = headerRow.createCell(1);
		Cell cellModelName = headerRow.createCell(2);
		Cell cellModelNumber = headerRow.createCell(3);

		cellProductName.setCellValue("Product Name");
		cellProductPrice.setCellValue("Price");
		cellModelName.setCellValue("Model Name");
		cellModelNumber.setCellValue("Model Number");
		
		// Apply the bold cell style to header cells
		cellProductName.setCellStyle(boldCellStyle);
		cellProductPrice.setCellStyle(boldCellStyle);
		cellModelName.setCellStyle(boldCellStyle);
		cellModelNumber.setCellStyle(boldCellStyle);

		int rowNum = 1;


		for (WebElement row : rows) {

			System.out.println("************");

			List<WebElement> boxes = row.findElements(By.xpath("./div"));

			for (WebElement box : boxes) {

				System.out.println("----------------");

				List <WebElement> name = box.findElements(By.xpath(".//a"));
				String nametag = name.get(1).getText();
				System.out.println(nametag);			

				// Open the product in a new tab
				String openInNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
				name.get(1).sendKeys(openInNewTab);
				Thread.sleep(2000); // Adjust this delay as needed

				// Switch to the new tab
				String originalHandle = driver.getWindowHandle();
				for (String handle : driver.getWindowHandles()) {
					if (!handle.equals(originalHandle)) {
						driver.switchTo().window(handle);
						break;
					}
				}

				WebElement priceElement = driver.findElement(By.xpath("//div[@class='_30jeq3 _16Jk6d']"));
				String priceofp = priceElement.getText();
				System.out.println("Price: " + priceofp);
			
				String productName = nametag;
		        String productPrice = priceofp;
		        String modelName = "N/A";
		        String modelNumber = "N/A";
				
				try {
					WebElement modelNameElement = driver.findElement(By.xpath("//tr[contains(@class, '_1s_Smc') and contains(., 'Model Name')]//li[@class='_21lJbe']"));
					modelName = modelNameElement.getText();
					System.out.println("Model Name: " + modelName);
				} catch (Exception e) {
					System.out.println("Model name information not available.");
				}

				try {
					WebElement modelNumberElement = driver.findElement(By.xpath("//tr[contains(@class, '_1s_Smc') and contains(., 'Model Number')]//li[@class='_21lJbe']"));
					modelNumber = modelNumberElement.getText();
					System.out.println("Model Number: " + modelNumber);
				} catch (Exception e) {
					System.out.println("Model number information not available.");
				}

				Row excelRow = sheet.createRow(rowNum++);
		        excelRow.createCell(0).setCellValue(productName);
		        excelRow.createCell(1).setCellValue(productPrice);
		        excelRow.createCell(2).setCellValue(modelName);
		        excelRow.createCell(3).setCellValue(modelNumber);
				
				// Close the current tab and switch back to the original tab
				driver.close();
				driver.switchTo().window(originalHandle);
			}
		}
			
			// Write the workbook content to a file
			try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Admin\\Desktop\\New folder\\product.xlsx")) {
			    workbook.write(outputStream);
			}

			// Close the workbook
			workbook.close();


	}


}



