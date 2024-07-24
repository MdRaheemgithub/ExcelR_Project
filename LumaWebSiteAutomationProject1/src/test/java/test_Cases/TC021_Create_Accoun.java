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

public class TC021_Create_Accoun {
	
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
	public void Create_Account() throws InterruptedException 
	{
		try {

			driver.findElement(By.xpath("//div[@class='panel header']//a[normalize-space()='Create an Account']")).click();
			Thread.sleep(3000);

			driver.findElement(By.name("firstname")).sendKeys("Zain");
			driver.findElement(By.name("lastname")).sendKeys("md");
			driver.findElement(By.name("email")).sendKeys("Zain12@gmail.com");
			driver.findElement(By.name("password")).sendKeys("Zain@123456");
			driver.findElement(By.name("password_confirmation")).sendKeys("Zain@123456");
			driver.findElement(By.xpath("//button[@title='Create an Account']//span[contains(text(),'Create an Account')]")).click();
			
			WebElement element=driver.findElement(By.xpath("//div[@class='message-error error message']"));
			if(element.isDisplayed())
			{
				System.out.println(element.getText());
			}
			else {
				String msg=driver.findElement(By.xpath("//div[contains(text(),'Thank you for registering with Main Website Store.')]")).getText();
				System.out.println("Message after Successful Registration : "+msg);
			}
		}
			 			
		catch (Exception e) {
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());		}

	}

	@AfterTest
	public void close_Browser() throws InterruptedException {
		Objects ob = new Objects(driver);
		ob.close_Browser();
	}

}
