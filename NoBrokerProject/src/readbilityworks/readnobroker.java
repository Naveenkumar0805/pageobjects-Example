package readbilityworks;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class readnobroker {

	static WebDriver driver;
	XSSFWorkbook workbook;


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
	public void location() throws InterruptedException{

		WebElement locationDropdown = driver.findElement(By.xpath("(//div[@class='css-16pqwjk-indicatorContainer nb-select__indicator nb-select__dropdown-indicator'])[1]"));
		locationDropdown.click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//div[contains(text(),'Hyderabad')]")).click();
		Thread.sleep(1000);

		String[] locations = {"sec", "gac", "app"}; // List of locations to search

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

		List<WebElement> articleElements = driver.findElements(By.xpath(".//div[@id='listCardContainer']//descendant::article"));
		for (WebElement article : articleElements) {
			String propertyType = article.findElement(By.xpath(".//div[@class='flex flex-col flex-2 w-pe mt-1.8px po:justify-center po:p-1p po:mt-0 w-70pe']//descendant::h2/a")).getText();
			String rent = article.findElement(By.xpath(".//div[@id='minimumRent']")).getText();
			String deposit = article.findElement(By.xpath(".//div[@id='roomType']")).getText();
			String builtUp = article.findElement(By.xpath(".//div[@id='unitCode']")).getText();
			String furnishingType = article.findElement(By.xpath(".//div[contains(text(),'Semi Furnished') or contains(text(),'Fully Furnished') or contains(text(),'Unfurnished')]")).getText();


			System.out.println("Property Type: " + propertyType);
			System.out.println("Rent: " + rent);
			System.out.println("Deposit: " + deposit);
			System.out.println("Built-up Area: " + builtUp);
			System.out.println("Furnishing Type: " + furnishingType);

			System.out.println("-------******-----------");
			//Thread.sleep(3000);
		}

	}
	
	@Test(dependsOnMethods = "search", priority = 3) 
	public void sortbyoldestfirst() throws InterruptedException {
		
		// Sort By posted on oldest first in Drop down box
		driver.findElement(By.xpath("//div[@class='css-1wy0on6 nb-select__indicators']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[contains(text(),'Posted On (Oldest First)')]")).click();
		Thread.sleep(3000);

		List<WebElement> articleElements = driver.findElements(By.xpath(".//div[@id='listCardContainer']//descendant::article"));
		for (WebElement article : articleElements) {
			String propertyType = article.findElement(By.xpath(".//div[@class='flex flex-col flex-2 w-pe mt-1.8px po:justify-center po:p-1p po:mt-0 w-70pe']//descendant::h2/a")).getText();
			String rent = article.findElement(By.xpath(".//div[@id='minimumRent']")).getText();
			String deposit = article.findElement(By.xpath(".//div[@id='roomType']")).getText();
			String builtUp = article.findElement(By.xpath(".//div[@id='unitCode']")).getText();
			String furnishingType = article.findElement(By.xpath(".//div[contains(text(),'Semi Furnished') or contains(text(),'Fully Furnished') or contains(text(),'Unfurnished')]")).getText();


			System.out.println("Property Type: " + propertyType);
			System.out.println("Rent: " + rent);
			System.out.println("Deposit: " + deposit);
			System.out.println("Built-up Area: " + builtUp);
			System.out.println("Furnishing Type: " + furnishingType);

			System.out.println("-------******-----------");
			//Thread.sleep(3000);
		}

		}

		@Test(dependsOnMethods = "search", priority = 4) 
		public void sortbyrent() throws InterruptedException {
			
			// Sort By rent
			WebElement drag = driver.findElement(By.xpath("//div[@class='rc-slider-handle rc-slider-handle-2']"));
			Actions action = new Actions(driver);
			action.dragAndDropBy(drag, -220, 0).perform();

			Thread.sleep(3000);

			List<WebElement> articleElements = driver.findElements(By.xpath(".//div[@id='listCardContainer']//descendant::article"));
			for (WebElement article : articleElements) {
				String propertyType = article.findElement(By.xpath(".//div[@class='flex flex-col flex-2 w-pe mt-1.8px po:justify-center po:p-1p po:mt-0 w-70pe']//descendant::h2/a")).getText();
				String rent = article.findElement(By.xpath(".//div[@id='minimumRent']")).getText();
				String deposit = article.findElement(By.xpath(".//div[@id='roomType']")).getText();
				String builtUp = article.findElement(By.xpath(".//div[@id='unitCode']")).getText();
				String furnishingType = article.findElement(By.xpath(".//div[contains(text(),'Semi Furnished') or contains(text(),'Fully Furnished') or contains(text(),'Unfurnished')]")).getText();


				System.out.println("Property Type: " + propertyType);
				System.out.println("Rent: " + rent);
				System.out.println("Deposit: " + deposit);
				System.out.println("Built-up Area: " + builtUp);
				System.out.println("Furnishing Type: " + furnishingType);

				System.out.println("-------******-----------");
				//Thread.sleep(3000);
			}

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
			
		
			
			}
