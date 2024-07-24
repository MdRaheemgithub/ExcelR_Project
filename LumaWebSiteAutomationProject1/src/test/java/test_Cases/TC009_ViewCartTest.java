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

public class TC009_ViewCartTest {
	
	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void Login() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
	}

	@Test
	public void ViewCartTest() {
		try {
			ob = new Objects(driver);
			
			ob.addToCart();
			
			WebElement cartIcon = driver.findElement(By.xpath("//div[@class='minicart-wrapper']//a//span[@class='counter qty']//span[1]"));
			cartIcon.click();
			Thread.sleep(2000);

			WebElement viewCartButton = driver.findElement(By.xpath("//span[normalize-space()='View and Edit Cart']"));
			viewCartButton.click();
			Thread.sleep(5000);
			
			WebElement cartProductName = driver.findElement(By.xpath("//td[@class='col item']//div[@class='product-item-details']"));
			WebElement cartProductPrice = driver.findElement(By.xpath("//td[@class='col price']//span[@class='price']"));
			WebElement cartSubTotal=driver.findElement(By.xpath("//td[@class='col subtotal']//span[@class='price']"));
			
			if(cartProductName.isDisplayed()&&cartProductPrice.isDisplayed()&&cartSubTotal.isDisplayed())
			{
				System.out.println("Product name is displayed : "+cartProductName.getText()+"\n");
				System.out.println("Product Price is displayed : "+cartProductPrice.getText()+"\n");
				System.out.println("Product SubTotalPrice is displayed : "+cartSubTotal.getText()+"\n");
				Assert.assertTrue(true);
			}
			else
			{
				System.out.println("Product Name and Product Prices are not displayed");
				Assert.assertTrue(false);

			}
									
		} catch (Exception e) {
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}



	@AfterTest
	public void close_Browser() throws InterruptedException {
		Objects ob = new Objects(driver);
		Thread.sleep(3000);		
		ob.close_Browser();
	}

}
