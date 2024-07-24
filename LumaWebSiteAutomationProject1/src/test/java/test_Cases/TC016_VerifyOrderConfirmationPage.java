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

public class TC016_VerifyOrderConfirmationPage {
	
	WebDriver driver;
	Objects ob;
	
	@BeforeTest
	public void Browser_Setup() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
	}
	
	@Test
	public void VerifyOrderConfirmationPage() {
		try {

			ob = new Objects(driver);
			ob.login();
			ob.addToCart();
			
			WebElement cartIcon = driver.findElement(By.xpath("//div[@class='minicart-wrapper']//a//span[@class='counter qty']//span[1]"));
			cartIcon.click();
			Thread.sleep(5000);

			//WebElement viewCartButton = driver.findElement(By.xpath("//span[normalize-space()='View and Edit Cart']"));
			//viewCartButton.click();
			//Thread.sleep(5000);
			
			WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@id='top-cart-btn-checkout']"));
			proceedToCheckoutButton.click();
			Thread.sleep(5000);			
						
			WebElement radio_Button=driver.findElement(By.xpath("//input[@type='radio'][@value='tablerate_bestway']"));
			radio_Button.click();
			
			WebElement nextStepElement = driver.findElement(By.xpath("//button[@data-role='opc-continue']"));
			nextStepElement.click();
			Thread.sleep(3000);
			
			WebElement placeOrder=driver.findElement(By.xpath("//button[@class='action primary checkout']"));
			placeOrder.click();
			Thread.sleep(5000);
			
			String expectedUrl = "https://magento.softwaretestingboard.com/checkout/onepage/success/";
	        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Payment was not processed successfully.");
			
			Thread.sleep(5000);
			WebElement msg=driver.findElement(By.xpath("//span[@class='base']"));
			System.out.println("Success msg : "+msg.getText());
			
			WebElement details=driver.findElement(By.xpath("//div[@class='checkout-success']//p//a"));
			details.click();
			
			WebElement orderNumber=driver.findElement(By.xpath("//span[@class='base']"));
			System.out.println("Order number of the Product is : "+orderNumber.getText()+"\n");
			
			WebElement productDetails=driver.findElement(By.xpath("//div[@class='order-details-items ordered']"));
			System.out.println("Details of the Product : "+productDetails.getText()+"\n");
			
			WebElement shippingDetails=driver.findElement(By.xpath("//div[@class='block block-order-details-view']//div[@class='block-content']"));
			System.out.println("Shipping Details : "+shippingDetails.getText()+"\n");
			
			Assert.assertTrue(orderNumber.isDisplayed(), "Order number is not displayed.");
			Assert.assertTrue(productDetails.isDisplayed(),"Product details are not displayed.");
			Assert.assertTrue(shippingDetails.isDisplayed(),  "Shipping information is not displayed.");
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
