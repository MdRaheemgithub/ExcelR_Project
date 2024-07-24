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

public class TC007_VerifyProductDetailsPage {
	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void Browser_Setup() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
	}
	@Test
	public void VerifyProductDetailsPage() 
	{
		try {

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

			WebElement productTitle=driver.findElement(By.xpath("//div[@class=\"page-title-wrapper product\"]"));
			WebElement productImages = driver.findElement(By.xpath("//div[@class='fotorama__stage']"));
			WebElement productDescription = driver.findElement(By.xpath("//div[@id='description']"));
			WebElement productPrice = driver.findElement(By.xpath("//span[@class='price']"));
			WebElement productAvailability = driver.findElement(By.xpath("//div[@class='stock available']"));

			if (productTitle.isDisplayed()&&productImages.isDisplayed() && productDescription.isDisplayed() &&
					productPrice.isDisplayed() && productAvailability.isDisplayed()) 
			{
				System.out.println("Title of the Product  : "+productTitle.getText()+"\n");
				System.out.println("Image is Displayed : "+productImages.isDisplayed()+"\n");
				System.out.println("Description of the Product : "+productDescription.getText()+"\n");
				System.out.println("Price of the Product : "+productPrice.getText()+"\n");
				System.out.println("Availability of Product : "+productAvailability.getText()+"\n");
				System.out.println("Test Case Passed: Product details page displays Product Title, product images, description, price, and availability.");
				Assert.assertTrue(true);
			} 
			else {
				System.out.println("Test Case Failed: Product details page is missing some elements.");
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
