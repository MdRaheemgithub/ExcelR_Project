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

public class TC019_verifyLoginWithValidCredentials {
	
	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void Browser_Setup() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
	}

	@DataProvider(name="credentials")
	public Object[][] validcredentials()
	{
		Object[][] data=new Object[3][2];

		data[0][0]="captain@gmail.com";
		data[0][1]="America143";

		data[1][0]="supersoilder@gmail.com";
		data[1][1]="Jessica143";

		data[2][0]="loki@gmail.com";
		data[2][1]="Hellqueen43";
		
		return data;
	}


	@Test(dataProvider = "credentials")
	public void verifyLoginWithValidCredentials(String email, String password)
	{

		try {
			ob = new Objects(driver);

			Thread.sleep(5000);
			WebElement signLink=driver.findElement(By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]"));
			signLink.click();

			WebElement emailId=driver.findElement(By.xpath("//input[@id='email']"));
			emailId.sendKeys(email);

			WebElement pass=driver.findElement(By.xpath("//input[@name='login[password]']"));
			pass.sendKeys(password);

			Thread.sleep(5000);
			WebElement signIn=driver.findElement(By.xpath("//button[@name='send']//span[contains(text(),'Sign In')]"));
			signIn.click();
			
			String expectedTitle=driver.getTitle();
			System.out.println(expectedTitle);
			Assert.assertTrue(expectedTitle.equals("Home Page"),"User did not logged in successfully and not redirected to the account dashboard");

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
