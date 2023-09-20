package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import flipkartPOM.ExcelWriter;
import flipkartPOM.flipkarthomepage;
import flipkartPOM.homedecor;
import flipkartPOM.productdetails;

public class flipkarttestcases {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		
		System.setProperty("webdriver.chrome.driver", "E:\\chrome driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("http://www.google.co.in");

		
		flipkarthomepage flipkarthomepage = new flipkarthomepage(driver);
		
		flipkarthomepage.searchForProduct("flipkart");
		flipkarthomepage.clickSearchResultLink();
		flipkarthomepage.closeLoginPopup();
		
		homedecor homedecor = new homedecor(driver);
		
		homedecor.hoverOverHomeMenu();
		homedecor.clickHomeDecorLink();
		Thread.sleep(5000);
		homedecor.selectpricerange();
		Thread.sleep(2000);
		
		
		// Create an ExcelWriter instance and specify the Excel file path
        ExcelWriter excelWriter = new ExcelWriter("C:\\Users\\Admin\\Desktop\\New folder\\objectflipkart.xlsx");
        
        productdetails details = new productdetails(driver, excelWriter);
		details.productdetailsInfo();

        // Add headers to the Excel file
        excelWriter.addRow("Product Name", "Price", "Model Name", "Model Number");

        // Save the Excel file
        excelWriter.save();

	}

}
