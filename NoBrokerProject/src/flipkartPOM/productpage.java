package flipkartPOM;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class productpage {

	public WebDriver driver;

    @FindBy(xpath = "//div[@class='_30jeq3 _16Jk6d']")
    public WebElement priceElement;

    @FindBy(xpath = "//tr[contains(@class, '_1s_Smc') and contains(., 'Model Name')]//li[@class='_21lJbe']")
    public WebElement modelNameElement;
   

    @FindBy(xpath = "//tr[contains(@class, '_1s_Smc') and contains(., 'Model Number')]//li[@class='_21lJbe']")
    public WebElement modelNumberElement;

    public productpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductPrice() {
        return priceElement.getText();
    }

    public String getModelName() {
    	
    	try {
            return modelNameElement.getText();
        } catch (Exception e) {
            return "N/A";
        }
    }

    public String getModelNumber() {
    	  try {
              return modelNumberElement.getText();
          } catch (Exception e) {
              return "N/A";
          }
    }
    
}

