package test_Cases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common_Objects.Objects;

public class TC002_SearchBox_Functionality {
	
	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void Browser_Setup() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();

	}

	@AfterTest
	public void close_Browser() throws InterruptedException {
		Objects ob = new Objects(driver);
		ob.close_Browser();
	}
	
	@Test
	public void SearchBox_Functionality() {
		try {

			WebElement searchBox = driver.findElement(By.xpath("//input[@name='q']"));
			System.out.println("Search Box is Displayed : " + searchBox.isDisplayed());
			System.out.println("Search Box is Enabled : " + searchBox.isEnabled());

			searchBox.sendKeys("T-shirts for men");
			Thread.sleep(3000);
			searchBox.sendKeys(Keys.ENTER);

			WebElement search_Result = driver.findElement(By.xpath("//div[@class='search results']"));

			for (int i = 1; i <= 12; i++) {

				if (search_Result.isDisplayed()) {
					WebElement Results = driver.findElement(
					By.xpath("//li[" + i + "]//div[@class='product details product-item-details']//strong//a"));
					System.out.println("Displayed results : " + Results.getText());
					Assert.assertTrue(true);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());		}

}
	
}
