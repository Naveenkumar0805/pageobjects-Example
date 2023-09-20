package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pageobjects.ExcelUtils;
import pageobjects.homepage;
import pageobjects.propertypage;
import pageobjects.searchpageobject;
import pageobjects.sortbyoldest;
import pageobjects.sortbyrent;


public class nobrokertestcases {

	private WebDriver driver;
	private searchpageobject locationPage;
	private propertypage propertypage;	


	@BeforeSuite
	public void openWebsite() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "E:\\chrome driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.nobroker.in/");


	}

	@Test(priority = 0)
	public void location() throws InterruptedException {

		locationPage = new searchpageobject(driver);

		locationPage.selectlocation("sec");
		locationPage.selectlocation("gac");
		locationPage.selectlocation("app");
		Thread.sleep(3000);

	}

	@Test(dependsOnMethods = "location", priority = 1)
	public void search() throws InterruptedException {

		homepage homepage = new homepage(driver);
		homepage.selectBhkOptions();
		Thread.sleep(3000);
		homepage.selectAvailability();
		Thread.sleep(3000);
		homepage.performSearch();
		Thread.sleep(3000);
		homepage.handlePopup();
		Thread.sleep(2000);
	}


	@Test(dependsOnMethods = "search", priority = 2)
	public void getValues() throws InterruptedException {
		// Perform actions on the PropertyPage

		propertypage = PageFactory.initElements(driver, propertypage.class);
		propertypage.printPropertyDetails();


	}

	@Test(dependsOnMethods = "search", priority = 3) 
	public void sortByOldestFirst() throws InterruptedException {

		sortbyoldest sortbyoldest = new sortbyoldest(driver);

		// Perform actions on the sortbyoldest using the Page Object Model
		sortbyoldest.clickDropdown();
		Thread.sleep(1000);
		sortbyoldest.clickOldestFirstOption();
		Thread.sleep(3000);

		// Iterate through the list of article elements and perform actions
		sortbyoldest.performActionOnEachArticle();

	}

	@Test(dependsOnMethods = "search", priority = 4)
	public void sortbyrent() throws InterruptedException {

		sortbyrent sortbyrent = new sortbyrent(driver);

		// Perform sorting by rent on the homepage using the Page Object Model
		sortbyrent.dragRentSlider(-220, 0);
		Thread.sleep(3000);

		// Iterate through the list of article elements and perform actions
		sortbyrent.performActionOnEachArticle();


	}
	
	@AfterSuite
	public void saveExcelChanges() {
	ExcelUtils.saveExcelChanges("C:\\Users\\Admin\\Desktop\\New folder\\objectnobroker.xlsx");
	
	}
}
