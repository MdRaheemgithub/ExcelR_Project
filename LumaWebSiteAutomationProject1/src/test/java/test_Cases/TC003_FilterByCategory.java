package test_Cases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common_Objects.Objects;

public class TC003_FilterByCategory {
	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void Browser_Setup() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();

	}

	@AfterTest
	public void close_Browser() throws InterruptedException {
		Objects ob = new Objects(driver);
		Thread.sleep(3000);		
		ob.close_Browser();
	}

	@Test
	public void FilterByCategory() {

		try {

			WebElement mensClothing = driver.findElement(By.linkText("Men"));
			Actions act=new Actions(driver);
			act.moveToElement(mensClothing).perform();
			Thread.sleep(3000);
			WebElement mensClothingCategory = driver.findElement(By.linkText("Tops"));        
			act.moveToElement(mensClothingCategory).click().perform();

			WebElement category= driver.findElement(By.xpath("//div[contains(text(),'Category')]"));
			category.click();
			WebElement category_List=driver.findElement(By.xpath("//li//a[contains(text(),'Tees')]"));
			category_List.click();



			List<WebElement> productTitles = driver.findElements(By.xpath("//li//div[@class='product details product-item-details']//strong//a"));
			boolean isCorrectCategory = true;
			for (WebElement title : productTitles) {
				System.out.println("Titles of the product : "+title.getText()+"\n");
				if (!title.getText().toLowerCase().contains("tee")) 
				{
					Assert.assertTrue(false);
					isCorrectCategory = false;
					break;

				}
			}

			if (isCorrectCategory) {

				System.out.println("Test Passed: Only products related to the selected category are displayed.");

			}
			else 
			{
				System.out.println("Test Failed: Products unrelated to the selected category are displayed.");

			}

		} catch (Exception e) {
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());		}

}
	
}
