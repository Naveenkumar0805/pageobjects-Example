package flipkartPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class homedecor {
	
	public WebDriver driver;
	
	@FindBy(xpath = "//div[contains(text(),'Home')]")
	public WebElement homeMenu;

    @FindBy(xpath = "//*[contains(text(),'Home Decor')]")
    public WebElement homeDecorLink;
    
    @FindBy(xpath = "(//section//span[contains(text(),'Price')]/ancestor::section//select)[2]")
    public WebElement pricerange;
    
    public homedecor(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

    public void selectpricerange() {
    	Select rate = new Select(pricerange);
		rate.selectByValue("2000");
    	
    }
}
