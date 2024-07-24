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

public class TC005_VerifyFilterByColor {
	
	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void Login() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
	}

	@Test
	public void VerifyFilterByColor() 
	{
		try {

			Actions act=new Actions(driver);

			WebElement womensClothing = driver.findElement(By.linkText("Women"));
			act.moveToElement(womensClothing).perform();
			Thread.sleep(2000);

			WebElement topsSection = driver.findElement(By.linkText("Tops"));
			topsSection.click();
			Thread.sleep(2000);

			WebElement category= driver.findElement(By.xpath("//div[contains(text(),'Category')]"));
			category.click();

			WebElement hoodies_Sweatshirts = driver.findElement(By.xpath("//li//a[contains(text(),'Hoodies & Sweatshirts ')]"));
			hoodies_Sweatshirts.click();
			Thread.sleep(3000);

			WebElement color= driver.findElement(By.xpath("//div[contains(text(),'Color')]"));
			color.click();			

			WebElement colorFilterwhite = driver.findElement(By.xpath("//a[@aria-label='White']//div[contains(@class,'swatch-option color')]"));
			colorFilterwhite.click();            

			List<WebElement> productItems = driver.findElements(By.xpath("//li//div[@class='product details product-item-details']//strong//a"));
			boolean isCorrectColorDisplayed = true;

			for (WebElement product : productItems) {
				System.out.println("Title of the product : "+product.getText());
				product = driver.findElement(By.xpath("//div[@class='swatch-opt-1146']//div[@id='option-label-color-93-item-59']"));
				System.out.println("Color of the product : "+product.getAttribute("aria-label")+"\n"); 

				if (!product.isDisplayed()) {
					isCorrectColorDisplayed = false;
					Assert.assertTrue(false);
					break;
				}
			}

			if (isCorrectColorDisplayed) 
			{
				System.out.println("Test Case  Passed: Only products available in the selected color are displayed.");
			} 
			else 
			{
				System.out.println("Test Case  Failed: Products with other colors are also displayed.");
			}

		} catch (Exception e) {
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());		} 
	}

	@AfterTest
	public void close_Browser() throws InterruptedException {
		Objects ob = new Objects(driver);
		Thread.sleep(3000);		
		ob.close_Browser();
	}
}


