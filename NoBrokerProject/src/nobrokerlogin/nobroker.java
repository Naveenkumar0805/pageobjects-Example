package nobrokerlogin;


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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class nobroker {

	static WebDriver driver;
	XSSFWorkbook workbook;
	ExtentReports extentReport;
	ExtentSparkReporter	sparkreporter;
	ExtentTest testcase;

	@BeforeSuite
	public void openwebsite() throws InterruptedException {
		
		extentReport = new ExtentReports();
		sparkreporter = new ExtentSparkReporter("ExtentReport.html");
		extentReport.attachReporter(sparkreporter);

		System.setProperty("webdriver.chrome.driver", "E:\\chrome driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.nobroker.in/");

	}

	@Test(priority = 0)
	public void location() throws InterruptedException{
		
		testcase = extentReport.createTest("entering location");

		WebElement locationDropdown = driver.findElement(By.xpath("(//div[@class='css-16pqwjk-indicatorContainer nb-select__indicator nb-select__dropdown-indicator'])[1]"));
		locationDropdown.click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//div[contains(text(),'Hyderabad')]")).click();
		Thread.sleep(1000);

		String[] locations = {"kuk", "gac", "app"}; // List of locations to search

		for (String location : locations) {

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

	@Test(dependsOnMethods = "location", priority = 1)
	public void search() throws InterruptedException {
		
		testcase = extentReport.createTest("entering search type");

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

		WebElement search = driver.findElement(By.xpath("//button[@class='prop-search-button flex items-center justify-center btn btn-primary btn-lg']"));
		search.click();	

		Thread.sleep(3000);

		//Handling Google Map
		driver.findElement(By.xpath("//*[contains(text(), 'Got it')]")).click();
		Thread.sleep(2000);

	}

	@Test(dependsOnMethods = "search", priority = 2)
	public void getValues() throws InterruptedException {
		
		testcase = extentReport.createTest("search Results");

		//print the first shown property

		WebElement houseinfo = driver.findElement(By.xpath("//div[@id='listCardContainer']//descendant::article[1]"));
		String RentDetails = houseinfo.getText();
		System.out.println(RentDetails);
		System.out.println("-------******-----------");

		if (workbook == null) {
			workbook = new XSSFWorkbook();
		}

		// Save the details to the Excel workbook
		saveToExcel(workbook, "PropertyDetails", RentDetails);

	}
	@Test(dependsOnMethods = "search", priority = 3) 
	public void sortbyoldestfirst() throws InterruptedException {
		
		testcase = extentReport.createTest("oldest first");

		// Sort By posted on oldest first in Drop down box
		driver.findElement(By.xpath("//div[@class='css-1wy0on6 nb-select__indicators']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[contains(text(),'Posted On (Oldest First)')]")).click();
		Thread.sleep(3000);

		WebElement houseinfo = driver.findElement(By.xpath("//div[@id='listCardContainer']//descendant::article[1]"));
		String RentDetails = houseinfo.getText();
		System.out.println(RentDetails);
		System.out.println("--------******----------");

		saveToExcel(workbook, "PropertyDetails2", RentDetails);
	}

	@Test(dependsOnMethods = "search", priority = 4) 
	public void sortbyrent() throws InterruptedException {
		
		testcase = extentReport.createTest("sort by rent price");

		// Sort By rent
		WebElement drag = driver.findElement(By.xpath("//div[@class='rc-slider-handle rc-slider-handle-2']"));
		Actions action = new Actions(driver);
		action.dragAndDropBy(drag, -220, 0).perform();

		Thread.sleep(3000);

		WebElement houseinfo = driver.findElement(By.xpath("//div[@id='listCardContainer']//descendant::article[1]"));
		String RentDetails = houseinfo.getText();
		System.out.println(RentDetails);
		System.out.println("--------******----------");

		saveToExcel(workbook, "PropertyDetails3", RentDetails);

	}

	@Test(dependsOnMethods = "search", priority = 5, enabled = false) 
	public void chatbox() throws InterruptedException {


		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@data-test-id='ChatWidgetWindow-iframe']")));
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[@class='meshim_widget_components_chatButton_Button ltr']"))).build().perform();
		
		WebElement chatbox = driver.findElement(By.xpath("//div[@class='meshim_widget_components_chatButton_Button ltr']"));
		chatbox.click();
		Thread.sleep(3000);
		
	}

	@AfterSuite
	public void closeBrowser() throws IOException {
		driver.quit();
		if (workbook != null) {
				FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Admin\\Desktop\\New folder\\PropertyInfo.xlsx");
				workbook.write(outputStream);
				workbook.close();
				System.out.println("Excel workbook saved successfully.");
		}
	
	}

	private void saveToExcel(XSSFWorkbook workbook, String sheetName, String rentDetails) {
		XSSFSheet sheet = workbook.createSheet(sheetName);
		String[] lines = rentDetails.split("\n");

		for (int i = 0; i < lines.length; i++) {
			Row row = sheet.createRow(i);
			Cell cell = row.createCell(0);
			cell.setCellValue(lines[i]);
		}
	}
}

