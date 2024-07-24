package test_Cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common_Objects.Objects;

public class TC010_UpdateCartTest {
	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void Browser_Setup() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
	}

	@Test
	public void UpdateCartTest()  
	{
		try 
		{
			ob = new Objects(driver);

			ob.addToCart();
			
			WebElement cartIcon = driver.findElement(By.xpath("//div[@class='minicart-wrapper']//a//span[@class='counter qty']//span[1]"));
			cartIcon.click();
			Thread.sleep(2000);

			WebElement viewCartButton = driver.findElement(By.xpath("//span[normalize-space()='View and Edit Cart']"));
			viewCartButton.click();
			Thread.sleep(3000);

			WebElement quantityInput = driver.findElement(By.xpath("//input[@title='Qty']"));
			quantityInput.clear();
			quantityInput.sendKeys("2");
		
			WebElement update_Cart=driver.findElement(By.xpath("//button[@title='Update Shopping Cart']"));
			update_Cart.click();
			Thread.sleep(3000);
			
			String updatedQuantity = driver.findElement(By.xpath("//div[1]//input[@title='Qty']")).getAttribute("value");
			String Price=driver.findElement(By.xpath("//td[@class='col price'][1]//span[@class='price']")).getText();
			String subTotal = driver.findElement(By.xpath("//td[@class='col subtotal'][1]//span[@class='price']")).getText();
			Price=Price.replace("$","");
			subTotal=subTotal.replace("$","");
			
			double doublePrice = Double.parseDouble(Price);	
			double updatedTotalPrice=Double.parseDouble(subTotal);
			double doubledPrice = doublePrice * 2;			
			
			if(updatedQuantity.equals("2")&&doubledPrice==updatedTotalPrice)
			{
				System.out.println("Quantity and SubTotal Price Successfully Updated"+"\n");
				Assert.assertTrue(true);
			}
			Assert.assertEquals(updatedQuantity, "2", "The quantity in the cart is not updated correctly.");
			Assert.assertTrue(updatedTotalPrice==doubledPrice, "The total price is not updated correctly.");
			Thread.sleep(3000);			
			
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
