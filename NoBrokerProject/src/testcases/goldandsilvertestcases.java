package testcases;

import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import goldsilverPOM.ExcelWriter;
import goldsilverPOM.goldratetable;
import goldsilverPOM.goldsilverpage;
import goldsilverPOM.silverratetable;

public class goldandsilvertestcases {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		
		System.setProperty("webdriver.chrome.driver", "E:\\chrome driver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://www.google.co.in");

        goldsilverpage goldSilver = new goldsilverpage(driver);
        
        goldSilver.searchForGoldRate("live gold rates in Chennai");
        goldSilver.clickTodaysGoldRateLink();
        Thread.sleep(3000);
        
        // Gold Rate Table
        goldratetable goldRateTable = new goldratetable(goldSilver.getGoldRateTable());
        List<List<String>> goldData = goldRateTable.extractTableData();

        // Silver Rate Table
        silverratetable silverRateTable = new silverratetable(goldSilver.getSilverRateTable());
        List<List<String>> silverData = silverRateTable.extractTableData();

        // Save data to Excel
        ExcelWriter excelWriter = new ExcelWriter();
        XSSFSheet goldSheet = excelWriter.createSheet("Gold Rate");
        excelWriter.writeData(goldSheet, goldData);
        XSSFSheet silverSheet = excelWriter.createSheet("Silver Rate");
        excelWriter.writeData(silverSheet, silverData);
        excelWriter.saveExcel("C:\\Users\\Admin\\Desktop\\New folder\\objectgoldandsilver.xlsx");


	}

}
