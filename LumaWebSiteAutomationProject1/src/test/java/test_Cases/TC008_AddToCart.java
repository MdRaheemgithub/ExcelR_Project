package test_Cases;

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

public class TC008_AddToCart {
	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void Login() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
	}

	@Test
	public void AddToCart() 
	{
		try 
		{

			Actions act=new Actions(driver);

			WebElement mensClothing = driver.findElement(By.linkText("Men"));
			act.moveToElement(mensClothing).perform();

			WebElement mensClothingCategory = driver.findElement(By.linkText("Bottoms"));        
			mensClothingCategory.click();

			WebElement category= driver.findElement(By.xpath("//div[contains(text(),'Category')]"));
			category.click();

			WebElement pants = driver.findElement(By.xpath("//li//a[contains(text(),'Pants')]"));
			pants.click();
			Thread.sleep(3000);

			WebElement product = driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']//li[1]"));
			product.click();

			WebElement size = driver.findElement(By.xpath("//div[@class='swatch-attribute-options clearfix']//div[contains(text(),'32')]"));
			size.click();
			Thread.sleep(3000);

			WebElement color =driver.findElement(By.xpath("//div[@class='swatch-attribute-options clearfix']//div[@option-label='Black']"));
			color.click();
			Thread.sleep(3000);


			WebElement addToCart=driver.findElement(By.xpath("//button[@class='action primary tocart']"));
			addToCart.click();
			Thread.sleep(5000);


			WebElement  cartItem=driver.findElement(By.xpath("//div[@class='minicart-wrapper']//a//span[@class='counter qty']//span[1]"));
			String cartCount=cartItem.getText();

			if(cartCount.toString().equals(cartItem.getText())) {
				System.out.println("Number of items in the cart : "+cartItem.getText()+"\n");
				String Success_Msg=driver.findElement(By.xpath("//div[@class='messages']")).getText();
				System.out.println("Success Message : "+Success_Msg+"\n");
				Thread.sleep(2000);
				System.out.println("Test Passed: Product successfully added to cart.");
				Assert.assertTrue(true);

			} else {
				System.out.println("Test Failed: Product not added to cart.");
				Assert.assertTrue(false);
			}

		} 
		catch (Exception e) 
		{
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
