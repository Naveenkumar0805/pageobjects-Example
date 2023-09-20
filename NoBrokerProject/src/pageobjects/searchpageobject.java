package pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class searchpageobject {
	
    public WebDriver driver;

    @FindBy(xpath = "(//div[@class='css-16pqwjk-indicatorContainer nb-select__indicator nb-select__dropdown-indicator'])[1]")
    public static WebElement locationDropdown;

    @FindBy(xpath = "//div[contains(text(),'Hyderabad')]")
    public static WebElement hyderabadOption;

    @FindBy(id = "listPageSearchLocality")
    public static WebElement locationField;

    public searchpageobject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectlocation(String location) throws InterruptedException {
        locationDropdown.click();
        Thread.sleep(1000);

        hyderabadOption.click();
        Thread.sleep(1000);

        locationField.sendKeys(location);
        Thread.sleep(1000);

        locationField.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);

        locationField.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        String placeholder = locationField.getAttribute("placeholder");
        if (placeholder.equalsIgnoreCase("Add more...")) {
            System.out.println("Can add more Locations: " + placeholder);
        } else {
            System.out.println("No Space to enter");
        }
    }

	
	

}
