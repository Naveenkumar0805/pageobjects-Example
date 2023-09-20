package pageobjects;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


	public class propertypage {
	
		public final WebDriver driver;
		static XSSFSheet sheet;
	
		@FindBy(xpath = ".//div[@id='listCardContainer']//descendant::article")
		public List<WebElement> articleElements;
	
		@FindBy(xpath = ".//div[@class='flex flex-col flex-2 w-pe mt-1.8px po:justify-center po:p-1p po:mt-0 w-70pe']//descendant::h2/a")
		public List<WebElement> propertyTypeElements;
	
		@FindBy(xpath = ".//div[@id='minimumRent']")
		public List<WebElement> rentElements;
	
		@FindBy(xpath = ".//div[@id='roomType']")
		public List<WebElement> depositElements;
	
		@FindBy(xpath = ".//div[@id='unitCode']")
		public List<WebElement> builtUpElements;
	
		@FindBy(xpath = ".//div[contains(text(),'Semi Furnished') or contains(text(),'Fully Furnished') or contains(text(),'Unfurnished')]")
		public List<WebElement> furnishingTypeElements;
	
		public propertypage(WebDriver driver) {
			this.driver = driver;
		}
	
		public void printPropertyDetails() {
			for (int i = 0; i < articleElements.size(); i++) {
				String propertyType = propertyTypeElements.get(i).getText();
				String rent = rentElements.get(i).getText();
				String deposit = depositElements.get(i).getText();
				String builtUp = builtUpElements.get(i).getText();
				String furnishingType = furnishingTypeElements.get(i).getText();
	
				System.out.println("Property Type: " + propertyType);
				System.out.println("Rent: " + rent);
				System.out.println("Deposit: " + deposit);
				System.out.println("Built-up Area: " + builtUp);
				System.out.println("Furnishing Type: " + furnishingType);
	
				System.out.println("-------******-----------");
	
			}
		}
	}
