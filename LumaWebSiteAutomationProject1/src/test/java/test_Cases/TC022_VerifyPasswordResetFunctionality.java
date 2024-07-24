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

public class TC022_VerifyPasswordResetFunctionality {
	
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
	public void VerifyPasswordResetFunctionality()
	{
		try 
		{
			WebElement signLink=driver.findElement(By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]"));
			signLink.click();

			WebElement forgetPassLink=driver.findElement(By.xpath("//a[@class='action remind']//span[contains(text(),'Forgot Your Password?')]"));
			forgetPassLink.click();

			WebElement emailId=driver.findElement(By.xpath("//input[@id='email_address']"));
			emailId.sendKeys("Zain12@gmail.com");

			WebElement resetBtn=driver.findElement(By.xpath("//button[@class='action submit primary']"));
			resetBtn.click();

			WebElement confirmationMessage = driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
			System.out.println(confirmationMessage.getText());
			Assert.assertTrue(confirmationMessage.isDisplayed(), "Password reset message did not match");

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
		ob.close_Browser();
	}

}
