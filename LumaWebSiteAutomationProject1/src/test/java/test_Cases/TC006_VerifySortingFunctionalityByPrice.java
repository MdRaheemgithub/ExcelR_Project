package test_Cases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common_Objects.Objects;

public class TC006_VerifySortingFunctionalityByPrice {
	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void Browser_Setup() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
		ob.login();
	}
	@Test
	public void VerifySortingFunctionalityByPrice () 
	{
		try {
			
			Actions act= new Actions(driver);
			WebElement gear = driver.findElement(By.linkText("Gear"));
			act.moveToElement(gear).perform();
			Thread.sleep(3000);

			WebElement topsSection = driver.findElement(By.linkText("Watches"));
			topsSection.click();
			Thread.sleep(2000);

			WebElement sortBy=driver.findElement(By.xpath("//div[@class='toolbar-sorter sorter']//select[@id='sorter']"));
			sortBy.click();

			Select sortByDropDown=new Select(sortBy);
			sortByDropDown.selectByValue("price");
			Thread.sleep(3000);

			List<WebElement> Product_Title = driver.findElements(By.xpath("//li//div[@class='product details product-item-details']//strong//a"));

			for(WebElement products : Product_Title)
			{
				System.out.println("Title of the product : "+products.getText()+"\n");
			}

			List<WebElement> priceElements = driver.findElements(By.xpath("//div[@class='price-box price-final_price']//span[@class='price']"));
			List<Double> prices = new ArrayList<>();

			for (WebElement priceElement : priceElements) {

				String priceText = priceElement.getText().replace("$", "").replace(",", "");
				System.out.println("Values of the products : "+priceElement.getText()+"\n");
				prices.add(Double.parseDouble(priceText));  

			}

			List<Double> sortedPrices = new ArrayList<>(prices);

			if(sortedPrices.equals(prices)) 
			{
				System.out.println("Test Case Passed: Products are sorted in ascending order of price.");
				Assert.assertTrue(true);
			}
			else 
			{
				System.out.println("Test Case Failed: Products are not sorted in ascending order of price.");
				Assert.assertTrue(false);

			}


		} catch (Exception e) {
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
