package flipkartPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class productdetails {

	public WebDriver driver;
	public ExcelWriter excelWriter;

	public productdetails(WebDriver driver, ExcelWriter excelWriter) {
		this.driver = driver;
		this.excelWriter = excelWriter;
	}

	public void productdetailsInfo() throws InterruptedException {
		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='_1AtVbE col-12-12']/div[@class='_13oc-S']"));
		System.out.println(rows.size());
		Thread.sleep(2000);

		for (WebElement row : rows) {
			System.out.println("************");
			List<WebElement> boxes = row.findElements(By.xpath("./div"));

			for (WebElement box : boxes) {
				System.out.println("----------------");
				List<WebElement> name = box.findElements(By.xpath(".//a"));
				String nametag = name.get(1).getText();
				System.out.println(nametag);

				// Open the product in a new tab
				String openInNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
				name.get(1).sendKeys(openInNewTab);
				Thread.sleep(1000); // Adjust this delay as needed

				// Switch to the new tab
				String originalHandle = driver.getWindowHandle();
				for (String handle : driver.getWindowHandles()) {
					if (!handle.equals(originalHandle)) {
						driver.switchTo().window(handle);
						break;
					}
				}

				Thread.sleep(1000);

				productpage productPage = new productpage(driver);

				String priceofp = productPage.getProductPrice();
				System.out.println("Price: " + priceofp);

				String modelName = productPage.getModelName();
				String modelNumber = productPage.getModelNumber();

				System.out.println("Model Name: " + modelName);
				System.out.println("Model Number: " + modelNumber);

				// Write the details to the Excel file
				excelWriter.addRow(nametag, priceofp, modelName, modelNumber);

				driver.close();
				driver.switchTo().window(originalHandle);
			}
		}
	}
}


