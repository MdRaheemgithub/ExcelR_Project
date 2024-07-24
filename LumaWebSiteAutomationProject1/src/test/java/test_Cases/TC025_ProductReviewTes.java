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

public class TC025_ProductReviewTes {
	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void browser_Setup() throws InterruptedException
	{
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
	}

	@Test
	public void ProductReviewTest() 
	{
		try {
			ob = new Objects(driver);
			ob.login();

			Actions act = new Actions(driver);

			WebElement mensClothing = driver.findElement(By.linkText("Men"));
			act.moveToElement(mensClothing).perform();
			Thread.sleep(2000);

			WebElement mensClothingCategory = driver.findElement(By.linkText("Tops"));
			mensClothingCategory.click();

			WebElement category = driver.findElement(By.xpath("//div[contains(text(),'Category')]"));
			category.click();
			Thread.sleep(2000);

			WebElement tanks = driver.findElement(By.xpath("//li//a[contains(text(),'Tanks')]"));
			tanks.click();

			WebElement product = driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']//li[2]"));
			product.click();

			WebElement reviewsBtn=driver.findElement(By.xpath("//a[@id='tab-label-reviews-title']"));
			reviewsBtn.click();

			WebElement starRating=driver.findElement(By.xpath("//div[@class='control review-control-vote']//label[5]"));
			act.moveToElement(starRating).click().perform();

			WebElement nickName=driver.findElement(By.xpath("//input[@id='nickname_field']"));
			nickName.clear();
			nickName.sendKeys("HULK");

			WebElement summary=driver.findElement(By.xpath("//input[@id='summary_field']"));
			summary.sendKeys("About This Cronus Yoga Pant");

			WebElement review=driver.findElement(By.xpath("//textarea[@id='review_field']"));
			review.sendKeys("Love It ");

			WebElement submitBtn=driver.findElement(By.xpath("//button[@class='action submit primary']"));
			submitBtn.click();

			WebElement msg=driver.findElement(By.xpath("//div[@class='message-success success message']"));
			System.out.println(msg);

			Assert.assertTrue(msg.isDisplayed(),"Review should be submitted successfully and visible on the product page.");
			
			ob.logout();
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}
	
	@AfterTest
    public void close_Browser() throws InterruptedException {
        ob = new Objects(driver);
        ob.close_Browser();
    }

}
