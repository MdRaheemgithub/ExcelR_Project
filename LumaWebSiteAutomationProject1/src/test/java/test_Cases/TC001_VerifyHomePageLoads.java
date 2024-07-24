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

public class TC001_VerifyHomePageLoads {
	
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
	public void VerifyHomePageLoads() {
		
		try {
			
			WebElement header_content = driver.findElement(By.xpath("//header[@class='page-header']"));
			if (header_content.isDisplayed()) {
				System.out.println("Header_Content is displayed.");
				Assert.assertTrue(true);
			} else {
				System.out.println("Header_Content is NOT displayed.");
				Assert.assertTrue(false);
			}

			WebElement logo = driver.findElement(By.xpath("//div[@class='header content']//a[@class='logo']"));
			if (logo.isDisplayed()) {
				System.out.println("Logo is Displayed.");
				Assert.assertTrue(true);
			} else {
				System.out.println("Logo is not displayed.");
				Assert.assertTrue(false);
			}

			WebElement main_Content = driver.findElement(By.xpath("//main[@id='maincontent']"));
			if (main_Content.isDisplayed()) {
				System.out.println("Main_Content is Displayed.");
				Assert.assertTrue(true);
			} else {
				System.out.println("Main_Content is not Displayed.");
				Assert.assertTrue(false);
			}

			WebElement footer_content = driver.findElement(By.xpath("//footer[@class='page-footer']"));
			if (footer_content.isDisplayed()) {
				System.out.println("Footer_Content is displayed.");
				Assert.assertTrue(true);
			} else {
				System.out.println("Footer_Content is NOT displayed.");
				Assert.assertTrue(false);
			}

			String page_Title = driver.getTitle();
			if (page_Title.equals("Home Page")) {
				System.out.println("Homepage loaded successfully with no errors.");
				Assert.assertTrue(true);
			} else {
				System.out.println("Homepage did not load as expected.");
				Assert.assertTrue(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());		}
		
	}

}
