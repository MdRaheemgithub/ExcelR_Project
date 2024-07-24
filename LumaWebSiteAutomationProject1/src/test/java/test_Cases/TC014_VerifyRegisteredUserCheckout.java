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

public class TC014_VerifyRegisteredUserCheckout {

	
	WebDriver driver;
	Objects ob;

	@BeforeTest
	public void Browser_Setup() throws InterruptedException {
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
	}

	@Test
	public void VerifyRegisteredUserCheckout() {
		try {

			ob = new Objects(driver);
			ob.login();
			ob.addToCart();			

			WebElement cartIcon = driver.findElement(By.xpath("//div[@class='minicart-wrapper']//a//span[@class='counter qty']//span[1]"));
			cartIcon.click();
			Thread.sleep(2000);

			WebElement viewCartButton = driver.findElement(By.xpath("//span[normalize-space()='View and Edit Cart']"));
			viewCartButton.click();
			Thread.sleep(5000);

			String checkoutUrl=driver.getCurrentUrl();			

			WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//li[@class='item']/child::button[1]"));
			proceedToCheckoutButton.click();
			Thread.sleep(5000);	

			WebElement newAddress=driver.findElement(By.xpath("//button[@class='action action-show-popup']"));
			newAddress.click();	
			Thread.sleep(5000);


			WebElement firstName = driver.findElement(By.xpath("//div[@class='control']//input[@name='firstname'][@id]"));
			WebElement lastName = driver.findElement(By.xpath("//div[@class='control']//input[@name='lastname'][@id]"));
			WebElement company=driver.findElement(By.xpath("//div[@class='control']//input[@name='company'][@id]"));
			WebElement address = driver.findElement(By.xpath("//div[@class='control']//input[@name='street[0]'][@id]"));
			WebElement city = driver.findElement(By.xpath("//div[@class='control']//input[@name='city'][@id]"));
			WebElement state = driver.findElement(By.xpath("//div[@class='control']//select[@name='region_id'][@id]"));
			WebElement zipCode = driver.findElement(By.xpath("//div[@class='control']//input[@name='postcode'][@id]"));
			WebElement country=driver.findElement(By.xpath("//div[@class='control']//select[@name='country_id'][@id]"));
			WebElement phoneNumber = driver.findElement(By.xpath("//div[@class='control _with-tooltip']//input[@name='telephone'][@id]"));

			firstName.clear();
			firstName.sendKeys("Bruce");
			lastName.clear();
			lastName.sendKeys("Banner");
			company.sendKeys("Stark Enterprise");
			address.sendKeys("123 Main St");
			city.sendKeys("Anytown");
			zipCode.sendKeys("12345");
			phoneNumber.sendKeys("1234567892");

			Select stateOption=new Select(state);
			stateOption.selectByValue("43");
			Thread.sleep(3000);

			Select countryOption=new Select(country);
			countryOption.selectByValue("US");
			Thread.sleep(3000);

			WebElement saveAddress=driver.findElement(By.xpath("//input[@id='shipping-save-in-address-book']"));
			saveAddress.click();

			WebElement shipHere=driver.findElement(By.xpath("//button[@class='action primary action-save-address']"));
			shipHere.click();
			Thread.sleep(5000);
			

			WebElement radio_Button=driver.findElement(By.xpath("//input[@type='radio'][@value='tablerate_bestway']"));
			radio_Button.click();

			WebElement nextStepElement = driver.findElement(By.xpath("//button[@data-role='opc-continue']"));
			nextStepElement.click();
			Thread.sleep(3000);

			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("#payment"), "User is not redirected to Payments page.");

			driver.navigate().to(checkoutUrl);

			ob.removeFromCart();
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
