package test_Cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common_Objects.Objects;

public class TC013_GuestCheckoutTest {
	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void Browser_Setup() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();

	}

	@Test()
	public void verifyGuestCheckout() {
		try {
			
			ob = new Objects(driver);
			
			ob.addToCart();

			WebElement cartIcon = driver.findElement(By.xpath("//div[@class='minicart-wrapper']//a//span[@class='counter qty']//span[1]"));
			cartIcon.click();
			Thread.sleep(2000);

			WebElement viewCartButton = driver.findElement(By.xpath("//span[normalize-space()='View and Edit Cart']"));
			viewCartButton.click();
			Thread.sleep(5000);

			WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']"));
			proceedToCheckoutButton.click();
			Thread.sleep(5000);
			
			String msg=driver.findElement(By.xpath("//span[contains(text(),'You can create an account after checkout.')]")).getText();
			System.out.println(msg);

			WebElement email=driver.findElement(By.xpath("//div[@class='control _with-tooltip']//input[@id='customer-email']"));
			WebElement firstName = driver.findElement(By.name("firstname"));
			WebElement lastName = driver.findElement(By.name("lastname"));
			WebElement company=driver.findElement(By.name("company"));
			WebElement address = driver.findElement(By.name("street[0]"));
			WebElement city = driver.findElement(By.name("city"));
			WebElement state = driver.findElement(By.name("region_id"));
			WebElement zipCode = driver.findElement(By.name("postcode"));
			WebElement country=driver.findElement(By.name("country_id"));
			WebElement phoneNumber = driver.findElement(By.name("telephone"));

			email.sendKeys("doejohn@gmail.com");
			firstName.sendKeys("John");
			lastName.sendKeys("Doe");
			company.sendKeys("Enterprise");
			address.sendKeys("123 Main St");
			city.sendKeys("Anytown");
			zipCode.sendKeys("12345");
			phoneNumber.sendKeys("1234567890");
			
			Select stateOption=new Select(state);
			stateOption.selectByIndex(1);
			Thread.sleep(3000);
			
			Select countryOption=new Select(country);
			countryOption.selectByIndex(1);
			Thread.sleep(3000);			

			WebElement nextStepElement = driver.findElement(By.xpath("//button[@data-role='opc-continue']"));
			nextStepElement.click();
			Thread.sleep(3000);
			
			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("#payment"), "User is not redirected to Payments page.");
			Thread.sleep(7000);
			
			driver.navigate().to("https://magento.softwaretestingboard.com/");
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
