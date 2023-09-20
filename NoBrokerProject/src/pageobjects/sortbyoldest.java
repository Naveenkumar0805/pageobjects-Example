package pageobjects;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class sortbyoldest {
	
    public WebDriver driver;
    static XSSFSheet sheet;

    @FindBy(xpath = "//div[@class='css-1wy0on6 nb-select__indicators']")
    public WebElement dropdown;

    @FindBy(xpath = "//*[contains(text(),'Posted On (Oldest First)')]")
    public WebElement oldestFirstOption;

    @FindBy(xpath = ".//div[@id='listCardContainer']//descendant::article")
    public List<WebElement> articleElements;

    public sortbyoldest(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickDropdown() {
        dropdown.click();
    }

    public void clickOldestFirstOption() {
        oldestFirstOption.click();
    }

    public List<WebElement> getArticleElements() {
        return articleElements;
    }

    // Add more methods as needed to interact with the page

    public void performActionOnEachArticle() {
        for (WebElement article : articleElements) {
            // Perform actions on each article element
            // For example, extract information and write to Excel
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
            
        }
    }

  
}
