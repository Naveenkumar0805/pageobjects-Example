package flipkartPOM;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class flipkarthomepage {
	

	public WebDriver driver;

    @FindBy(id = "APjFqb")
    public WebElement searchInput;

    @FindBy(className = "H9lube")
    public WebElement searchResultLink;

    @FindBy(xpath = "//button[contains(text(),'✕')]")
    public WebElement closeButton;

    @FindBy(xpath = "//div[contains(text(),'Home')]")
    public WebElement homeMenu;

    @FindBy(xpath = "//*[contains(text(),'Home Decor')]")
    public WebElement homeDecorLink;

    public flipkarthomepage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchForProduct(String product) {
        searchInput.sendKeys(product);
        searchInput.submit();
    }

    public void clickSearchResultLink() {
        searchResultLink.click();
    }

    public void closeLoginPopup() {
        closeButton.click();
    }

    public void hoverOverHomeMenu() {
        // Implement hover action 
    	Actions homedecor = new Actions(driver);
		homedecor.moveToElement(homeMenu).build().perform();
    }

    public void clickHomeDecorLink() {
        // Implement click action
    	Actions selectprice = new Actions(driver);
		selectprice.moveToElement(homeDecorLink).click().build().perform();
    }
}


