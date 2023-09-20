package readbilityworks;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class flipkart {
	public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "E:\\chrome driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://www.google.co.in");

        WebElement opensite = driver.findElement(By.id("APjFqb"));
        opensite.sendKeys("flipkart" + Keys.ENTER);

        WebElement flipkartsite = driver.findElement(By.className("H9lube"));
        flipkartsite.click();

        WebElement login = driver.findElement(By.xpath("//button[contains(text(),'✕')]"));
        login.click();

        WebElement homeappliances = driver.findElement(By.xpath("//div[contains(text(),'Home')]"));
        Actions homedecor = new Actions(driver);
        homedecor.moveToElement(homeappliances).build().perform();

        WebElement homeitems = driver.findElement(By.xpath("//*[contains(text(),'Home Decor')]"));
        Actions selectprice = new Actions(driver);
        selectprice.moveToElement(homeitems).click().build().perform();
        Thread.sleep(5000);

        WebElement price = driver.findElement(By.xpath("(//section//span[contains(text(),'Price')]/ancestor::section//select)[2]"));
        Select rate = new Select(price);
        rate.selectByValue("2000");

        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='_1AtVbE col-12-12']/div[@class='_13oc-S']"));

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Product Details");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Product Name");
        headerRow.createCell(1).setCellValue("Price");
        headerRow.createCell(2).setCellValue("Model Name");
        headerRow.createCell(3).setCellValue("Model Number");

        int rowIndex = 1;

        for (WebElement row : rows) {
            List<WebElement> boxes = row.findElements(By.xpath("./div"));

            for (WebElement box : boxes) {
                List<WebElement> name = box.findElements(By.xpath(".//a"));
                String productName = name.get(1).getText();

                WebElement priceElement = box.findElement(By.xpath(".//div[@class='_30jeq3 _16Jk6d']"));
                String productPrice = priceElement.getText();

                WebElement modelNameElement = box.findElement(By.xpath(".//tr[contains(@class, '_1s_Smc') and contains(., 'Model Name')]//li[@class='_21lJbe']"));
                String modelName = modelNameElement.getText();

                WebElement modelNumberElement = box.findElement(By.xpath(".//tr[contains(@class, '_1s_Smc') and contains(., 'Model Number')]//li[@class='_21lJbe']"));
                String modelNumber = modelNumberElement.getText();

                Row dataRow = sheet.createRow(rowIndex++);
                dataRow.createCell(0).setCellValue(productName);
                dataRow.createCell(1).setCellValue(productPrice);
                dataRow.createCell(2).setCellValue(modelName);
                dataRow.createCell(3).setCellValue(modelNumber);

                // Open the product in a new tab
                String openInNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
                name.get(1).sendKeys(openInNewTab);

                // Switch to the new tab
                Set<String> handles = driver.getWindowHandles();
                String originalHandle = driver.getWindowHandle();
                handles.remove(originalHandle);
                String newTab = handles.iterator().next();
                driver.switchTo().window(newTab);

                // Extract other details...
                WebElement productDetails = driver.findElement(By.xpath("//div[@class='_1AtVbE col-12-12']"));
                // Extract any other details you need here...

                // Close the new tab and switch back to the original tab
                driver.close();
                driver.switchTo().window(originalHandle);
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Admin\\Desktop\\New folder\\product.xlsx")) {
            workbook.write(outputStream);
        }

        driver.quit();
    }
}



