package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class homepage {

    public WebDriver driver;

    // Constructor to initialize the driver and elements
    public homepage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

  
    @FindBy(xpath = "(//div[@class='css-16pqwjk-indicatorContainer nb-select__indicator nb-select__dropdown-indicator'])[2]")
    public WebElement bhkTypeDropdown;

    @FindBy(xpath = "//*[contains(text(),'2 BHK')]")
    public WebElement bhk2Option;

    @FindBy(xpath = "//*[contains(text(),'1 BHK')]")
    public WebElement bhk1Option; 

    @FindBy(tagName = "body")
    public WebElement body;

    @FindBy(xpath = "(//div[@class='css-16pqwjk-indicatorContainer nb-select__indicator nb-select__dropdown-indicator'])[3]")
    public WebElement availabilityDropdown;

    @FindBy(xpath = "//*[contains(text(),'After 30 Days')]")
    public WebElement after30DaysOption;

    @FindBy(xpath = "//button[@class='prop-search-button flex items-center justify-center btn btn-primary btn-lg']")
    public WebElement searchButton;

    @FindBy(xpath = "//*[contains(text(), 'Got it')]")
    public WebElement gotItButton;

   
    // Methods to interact with the elements
   public void selectBhkOptions() {
        bhkTypeDropdown.click();
        bhk2Option.click();
        bhk1Option.click();
        body.click();
    } 

    public void selectAvailability() {
        availabilityDropdown.click();
        after30DaysOption.click();
    }

    public void performSearch() {
        searchButton.click();
    }

    public void handlePopup() {
        gotItButton.click();
    } 

}
