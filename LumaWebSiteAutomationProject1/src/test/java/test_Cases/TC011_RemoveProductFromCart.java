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

public class TC011_RemoveProductFromCart {
	
	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void Browser_Setup() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
	}
	
	@Test
    public void RemoveProductFromCart() {
        try {
        	ob = new Objects(driver);
        	
        	ob.addToCart();
        	
        	WebElement cartIcon=driver.findElement(By.xpath("//div[@data-block=\"minicart\"]"));
        	cartIcon.click();
        	
        	WebElement cartView=driver.findElement(By.xpath("//a[@class='action viewcart']"));
        	cartView.click();
        	
        	WebElement removeIcon=driver.findElement(By.xpath("//a[@class='action action-delete']"));
        	removeIcon.click();
        	
        	String msg=driver.findElement(By.xpath("//div[@class='cart-empty']//p")).getText();
        	System.out.println(msg+"\n");
        	
        	} 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        String cartMessage = driver.findElement(By.xpath("//div[@class='cart-empty']//p")).getText();
        Assert.assertEquals(cartMessage, "You have no items in your shopping cart.");
    }

	
	@AfterTest
	public void close_Browser() throws InterruptedException {
		Objects ob = new Objects(driver);
		Thread.sleep(3000);		
		ob.close_Browser();
	}

}
