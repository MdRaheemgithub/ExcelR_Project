package test_Cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common_Objects.Objects;

public class TC018_VerifyInvalidSearch {
	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void Browser_Setup() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
	}

	@DataProvider(name="searchQueries")
	public Object[][] search()
	{
		Object[][] data=new Object[3][2];

		data[0][0]="adas";
		data[0][1]="men";

		data[1][0]="sajhdb";
		data[1][1]="women";

		data[2][0]="asjhd";
		data[2][1]="gear";
		return data;
	}


	@Test(dataProvider = "searchQueries")
	public void verifyNoResultsForInvalidQueries(String query, String model) {

		try {
			WebElement searchBar = driver.findElement(By.xpath("//input[@id='search']"));
			searchBar.clear();
			searchBar.sendKeys(query+""+model);
			searchBar.submit();       

			WebElement noResultsMessage = driver.findElement(By.xpath("//div[contains(text(),'Your search returned no results.')]"));
			System.out.println(noResultsMessage.getText()+"\n");        
			Assert.assertTrue(noResultsMessage.isDisplayed(), "No results message is not displayed for query: " + query);
			driver.navigate().back();
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
