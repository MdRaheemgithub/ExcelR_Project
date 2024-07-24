package test_Cases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common_Objects.Objects;

public class TC017_VerifyOrderHistory {
	
	WebDriver driver;
	Objects ob;
	
	@BeforeTest
	public void Browser_Setup() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
	}
	
	@Test
	public void VerifyOrderHistory()
	{
		try {
			
			ob = new Objects(driver);
			
			ob.login();	
			
			 WebElement accountMenu=driver.findElement(By.xpath("//div[@class='panel header']//button[@type='button']"));
			 accountMenu.click();
			
			 WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='customer-menu']//a[contains(text(),'My Account')]"));
		     myAccountLink.click();
		     
		     WebElement orderHistoryLink = driver.findElement(By.xpath("//ul[@class='nav items']//a[contains(text(),'My Orders')]"));
		     orderHistoryLink.click();
		     
		     List<WebElement> orders = driver.findElements(By.xpath("//table[@id='my-orders-table']//tbody//tr"));
		     Assert.assertTrue(orders.size() > 0, "Order history is empty!");
		     
		     for (WebElement order : orders) {
		    	 
		    	 System.out.print("Order History Details : "+order.getText()+"\n");
		     }
		     
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
