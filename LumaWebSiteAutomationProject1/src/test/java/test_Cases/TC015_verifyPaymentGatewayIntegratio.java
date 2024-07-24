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

public class TC015_verifyPaymentGatewayIntegratio {
	
	WebDriver driver;
	Objects ob;
	
	@BeforeTest
	public void Browser_Setup() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
	}
	
	@Test
	public void verifyPaymentGatewayIntegration() {
		try {

			ob = new Objects(driver);
			ob.login();
			ob.addToCart();
			
			WebElement cartIcon = driver.findElement(By.xpath("//div[@class='minicart-wrapper']//a//span[@class='counter qty']//span[1]"));
			cartIcon.click();
			Thread.sleep(2000);

			//WebElement viewCartButton = driver.findElement(By.xpath("//span[normalize-space()='View and Edit Cart']"));
			//viewCartButton.click();
			//Thread.sleep(5000);
			
			WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@id='top-cart-btn-checkout']"));
			proceedToCheckoutButton.click();
			Thread.sleep(5000);	
			
			WebElement newAddress=driver.findElement(By.xpath("//button[@class='action action-show-popup']"));
			newAddress.click();	
			Thread.sleep(5000);

			ob.shippingDetails();
			
			WebElement saveAddress=driver.findElement(By.xpath("//input[@id='shipping-save-in-address-book']"));
			saveAddress.click();

			WebElement shipHere=driver.findElement(By.xpath("//button[@class='action primary action-save-address']"));
			shipHere.click();
			Thread.sleep(5000);
			
			WebElement radio_Button=driver.findElement(By.xpath("//input[@type='radio'][@value='tablerate_bestway']"));
			radio_Button.click();
			
			WebElement nextStepElement = driver.findElement(By.xpath("//button[@data-role='opc-continue']"));
			nextStepElement.click();
			Thread.sleep(5000);
			
			WebElement placeOrder=driver.findElement(By.xpath("//button[@class='action primary checkout']"));
			placeOrder.click();
			Thread.sleep(5000);
			
			String expectedUrl = "https://magento.softwaretestingboard.com/checkout/onepage/success/";
	        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Payment was not processed successfully.");
	        
			ob.logout();

						
		}
		catch (Exception e) 
		{
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
