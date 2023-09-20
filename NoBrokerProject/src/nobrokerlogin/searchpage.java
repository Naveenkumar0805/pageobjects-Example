package nobrokerlogin;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class searchpage {

	static WebDriver driver;

	@BeforeSuite
	public void openwebsite() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "E:\\chrome driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.nobroker.in/");

	}

	@Test(priority = 0)
	public void searchlocation() throws InterruptedException{

String[] locations = {"kuk", "gac", "app"}; // List of locations to search
        
        for (String location : locations) {
            WebElement locationDropdown = driver.findElement(By.xpath("(//div[@class='css-16pqwjk-indicatorContainer nb-select__indicator nb-select__dropdown-indicator'])[1]"));
            locationDropdown.click();
            Thread.sleep(1000);
            
            driver.findElement(By.xpath("//div[contains(text(),'Hyderabad')]")).click();
            Thread.sleep(1000);

            WebElement locationField = driver.findElement(By.id("listPageSearchLocality"));
            locationField.sendKeys(location);
            Thread.sleep(1000);

            locationField.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);

            locationField.sendKeys(Keys.ENTER);
            Thread.sleep(1000);

            WebElement locationPlaceholder = driver.findElement(By.id("listPageSearchLocality"));
            String placeholder = locationPlaceholder.getAttribute("placeholder");
            
            if (placeholder.equalsIgnoreCase("Add more...")) {
                System.out.println("Can add more Locations: " + placeholder);
            } else {
                System.out.println("No Space to enter");
            }
            
         
        }
	}


	@Test(dependsOnMethods = "searchlocation", priority = 1)
	public void search() throws InterruptedException {

		WebElement bhktype = driver.findElement(By.xpath("(//div[@class='css-16pqwjk-indicatorContainer nb-select__indicator nb-select__dropdown-indicator'])[2]"));
		bhktype.click();
		
		driver.findElement(By.xpath("//*[contains(text(),'2 BHK')]")).click();
		driver.findElement(By.xpath("//*[contains(text(),'1 BHK')]")).click();
		
		WebElement body = driver.findElement(By.tagName("body"));
        body.click();
		
		Thread.sleep(3000);
		
		WebElement availability = driver.findElement(By.xpath("(//div[@class='css-16pqwjk-indicatorContainer nb-select__indicator nb-select__dropdown-indicator'])[3]"));
		availability.click();
		
		driver.findElement(By.xpath("//*[contains(text(),'After 30 Days')]")).click();
		Thread.sleep(3000);
	
		WebElement search = driver.findElement(By.xpath("//button[@class='prop-search-button btn btn-primary btn-lg']"));
		search.click();
		Thread.sleep(3000);

		//Handling Google Map
		driver.findElement(By.xpath("//*[contains(text(), 'Got it')]")).click();
		Thread.sleep(2000);

	}

	@Test(dependsOnMethods = "search", priority = 2)
	public void getValues() throws InterruptedException, FileNotFoundException, IOException {

		//print the first shown property
		
		WebElement houseinfo = driver.findElement(By.xpath("//div[@id='listCardContainer']//descendant::article[1]"));
		String RentDetails = houseinfo.getText();
		System.out.println(RentDetails);
		System.out.println("-------******-----------");
		
		// Create Excel workbook and sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Property Details");
		
		// Split details into separate lines
	    String[] lines = RentDetails.split("\n");

	    // Add each line as a new row in the Excel sheet
	    for (int i = 0; i < lines.length; i++) {
	        Row row = sheet.createRow(i);
	        Cell cell = row.createCell(0);
	        cell.setCellValue(lines[i]);
	    }

        // Save the workbook to a file
        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Admin\\Desktop\\New folder\\PropertyDetails.xlsx")) {
            workbook.write(outputStream);
        }
        System.out.println("Property details written to Excel.");
        
	}
	
	
	 @Test(dependsOnMethods = "search", priority = 3) 
	 public void sortbyoldestfirst() throws InterruptedException, FileNotFoundException, IOException {
	 
		// Sort By posted on oldest first in Drop down box
			driver.findElement(By.xpath("//div[@class='css-1wy0on6 nb-select__indicators']")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("//*[contains(text(),'Posted On (Oldest First)')]")).click();
			Thread.sleep(3000);
			
			WebElement houseinfo = driver.findElement(By.xpath("//div[@id='listCardContainer']//descendant::article[1]"));
			String RentDetails = houseinfo.getText();
			System.out.println(RentDetails);
			System.out.println("--------******----------");
			
			// Create Excel workbook and sheet
	        XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet("Property Details1");
			
			// Split details into separate lines
		    String[] lines = RentDetails.split("\n");

		    // Add each line as a new row in the Excel sheet
		    for (int i = 0; i < lines.length; i++) {
		        Row row = sheet.createRow(i);
		        Cell cell = row.createCell(0);
		        cell.setCellValue(lines[i]);
		    }

	        // Save the workbook to a file
	        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Admin\\Desktop\\New folder\\PropertyDetails1.xlsx")) {
	            workbook.write(outputStream);
	        }
	        System.out.println("Property details written to Excel.");
			
	 }
	 
	 @Test(dependsOnMethods = "search", priority = 4) 
	 public void sortbyrent() throws InterruptedException, FileNotFoundException, IOException {
	 
		// Sort By rent
		 WebElement drag = driver.findElement(By.xpath("//div[@class='rc-slider-handle rc-slider-handle-2']"));
		 Actions action = new Actions(driver);
		 action.dragAndDropBy(drag, -220, 0).perform();

		 Thread.sleep(3000);

		 WebElement houseinfo = driver.findElement(By.xpath("//div[@id='listCardContainer']//descendant::article[1]"));
		 String RentDetails = houseinfo.getText();
		 System.out.println(RentDetails);
		 System.out.println("--------******----------");
		 
		// Create Excel workbook and sheet
	        XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet("Property Details2");
			
			// Split details into separate lines
		    String[] lines = RentDetails.split("\n");

		    // Add each line as a new row in the Excel sheet
		    for (int i = 0; i < lines.length; i++) {
		        Row row = sheet.createRow(i);
		        Cell cell = row.createCell(0);
		        cell.setCellValue(lines[i]);
		    }

	        // Save the workbook to a file
	        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Admin\\Desktop\\New folder\\PropertyDetails2.xlsx")) {
	            workbook.write(outputStream);
	        }
	        System.out.println("Property details written to Excel.");
	
	 }
	 
	 @Test(dependsOnMethods = "search", priority = 5) 
	 public void chatbox() throws InterruptedException {
		 
		
		 driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@data-test-id='ChatWidgetWindow-iframe']")));
		
		 WebElement chatbox = driver.findElement(By.xpath("//label[@class='greeting jx_ui_Label']"));
		 chatbox.click();

		 Thread.sleep(3000);
	 
	 
	 }
}

