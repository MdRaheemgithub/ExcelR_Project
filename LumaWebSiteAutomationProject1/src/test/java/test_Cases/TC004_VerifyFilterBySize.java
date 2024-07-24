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

public class TC004_VerifyFilterBySize {
	
	WebDriver driver;
	Objects ob;
	
	@BeforeTest
	public void Browser_Setup() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
	}
	
	@Test
	public void VerifyFilterBySize() {
		try {
			
			Actions act=new Actions(driver);

			WebElement mensClothing = driver.findElement(By.linkText("Men"));
			act.moveToElement(mensClothing).perform();
			Thread.sleep(2000);

			WebElement mensClothingCategory = driver.findElement(By.linkText("Tops"));        
			mensClothingCategory.click();

			WebElement category= driver.findElement(By.xpath("//div[contains(text(),'Category')]"));
			category.click();

			WebElement jackets = driver.findElement(By.xpath("//li//a[contains(text(),'Jackets')]"));
			jackets.click();
			Thread.sleep(3000);

			WebElement size= driver.findElement(By.xpath("//div[contains(text(),'Size')]"));
			size.click();


			WebElement sizeFilter = driver.findElement(By.xpath("//div[@class='swatch-option text '][contains(text(),'M')]"));
			sizeFilter.click();
			Thread.sleep(3000);

			List<WebElement> productTitle = driver.findElements(By.xpath("//li//div[@class='product details product-item-details']//strong//a"));
			boolean allProductsMatchFilter = true;
			for (WebElement products : productTitle) {
				System.out.println("Title of the product : "+products.getText());
				products=driver.findElement(By.xpath("//div//div[@id='option-label-size-143-item-168'][contains(text(),'M')]"));
				System.out.println("Size of the Product : "+products.getAttribute("aria-label")+"\n");
				if (!products.isDisplayed()) {
					allProductsMatchFilter = false;
					Assert.assertTrue(false);
					break;
				}
			}

			if (allProductsMatchFilter) 
			{
				System.out.println("Test Case Passed: Only products available in the selected size are displayed.");
			} 
			else {
				System.out.println("Test Case Failed: Some products not available in the selected size are displayed.");
			}

		} catch (InterruptedException e) {
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());		} 
	}
	
	@AfterTest
	public void close_Browser() throws InterruptedException {
		
		Thread.sleep(3000);		
		ob.close_Browser();
	}

}
