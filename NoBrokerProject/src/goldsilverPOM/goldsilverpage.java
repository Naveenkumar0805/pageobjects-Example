package goldsilverPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class goldsilverpage {
	
	private WebDriver driver;

    @FindBy(name = "q")
    private WebElement searchInput;

    @FindBy(partialLinkText = "Todays Gold Rate")
    private WebElement todaysGoldRateLink;

    @FindBy(xpath = "(//table[@class='table-price'])[2]")
    private WebElement goldRateTable;

    @FindBy(xpath = "(//table[@class='table-price'])[4]")
    private WebElement silverRateTable;

    public goldsilverpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchForGoldRate(String searchTerm) {
        searchInput.sendKeys(searchTerm);
        searchInput.submit();
    }

    public void clickTodaysGoldRateLink() {
        todaysGoldRateLink.click();
    }

    public WebElement getGoldRateTable() {
        return goldRateTable;
    }

    public WebElement getSilverRateTable() {
        return silverRateTable;
    }
}


