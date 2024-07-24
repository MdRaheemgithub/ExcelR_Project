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

public class TC012_VerifyProceedToCheckout {
	
	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void Browser_Setup() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
			
	}
	
	 @Test
	    public void verifyProceedToCheckout() throws InterruptedException {
		 try {
			 		ob = new Objects(driver);
			 		
			 		ob.addToCart();			 
				
				WebElement cartIcon = driver.findElement(By.xpath("//div[@class='minicart-wrapper']//a//span[@class='counter qty']//span[1]"));
				cartIcon.click();
				Thread.sleep(2000);

				WebElement viewCartButton = driver.findElement(By.xpath("//span[normalize-space()='View and Edit Cart']"));
				viewCartButton.click();
				Thread.sleep(5000);

		        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']"));
		        proceedToCheckoutButton.click();
		        Thread.sleep(5000);

		        String currentUrl = driver.getCurrentUrl();
		        Assert.assertTrue(currentUrl.contains("checkout"), "User is not redirected to the checkout page.");
		        Thread.sleep(5000);		        
		        		        
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
