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

public class TC020_VerifyLoginWIthInValidCredentials {
	
	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void Browser_Setup() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
	}

	@DataProvider(name="credentials")
	public Object[][] Invalidcredentials()
	{
		Object[][] data=new Object[3][2];

		data[0][0]="cdsghd@gmail.com";
		data[0][1]="America143";

		data[1][0]="sbdcjdc@gmail.com";
		data[1][1]="Jessica143";

		data[2][0]="bvsdbvhsd@gmail.com";
		data[2][1]="Hellqueen43";
		
		return data;
	}


	@Test(dataProvider = "credentials")
	public void verifyLoginWithInValidCredentials(String email, String password)
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
			
			String msg=driver.findElement(By.xpath("//div[@class='message-error error message']")).getText();
			System.out.println(msg);
			Thread.sleep(5000);		
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
