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

public class TC023_TestWishlistFunctionalit {
	
	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void browser_Setup() throws InterruptedException
	{
		driver=new ChromeDriver();
		ob=new Objects(driver);
		ob.url();
	}

	@Test
	public void TestWishlistFunctionality() 
	{
		try {
			ob=new Objects(driver);

			ob.login();

			Actions act=new Actions(driver);

			WebElement mensClothing = driver.findElement(By.linkText("Men"));
			act.moveToElement(mensClothing).perform();
			Thread.sleep(2000);

			WebElement mensClothingCategory = driver.findElement(By.linkText("Tops"));        
			mensClothingCategory.click();

			WebElement category= driver.findElement(By.xpath("//div[contains(text(),'Category')]"));
			category.click();
			Thread.sleep(2000);

			WebElement tanks = driver.findElement(By.xpath("//li//a[contains(text(),'Tanks')]"));
			tanks.click();

			WebElement product = driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']//li[1]"));
			product.click();

			WebElement wishlistBtn=driver.findElement(By.xpath("//div[@class='product-addto-links']//a[1]"));
			wishlistBtn.click();

			String msg=driver.findElement(By.xpath("//div[@class='message-success success message']")).getText();
			System.out.println(msg);

			WebElement whishlistItem=driver.findElement(By.xpath("//ol[@class='product-items']//li"));
			Assert.assertTrue(whishlistItem.isDisplayed(), "Product is not visible in the wishlist.");

			ob.logout();

		} 
		catch (Exception e) {
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());			}
	}

	@AfterTest
	public void close_Browser() throws InterruptedException {
		Objects ob = new Objects(driver);
		ob.close_Browser();
	}

}
