package common_Objects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Objects {
	
	WebDriver driver;

    public Objects(WebDriver driver) {
        this.driver = driver;
    }

    public void url() throws InterruptedException {
        driver.get("https://magento.softwaretestingboard.com/");
        Thread.sleep(7000);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void login() {
        driver.findElement(By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]")).click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("md460580@gmail.com");
        driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("May@1234");
        driver.findElement(By.xpath("//button[@name='send']//span[contains(text(),'Sign In')]")).click();
    }

    public void logout() throws InterruptedException {
    	Thread.sleep(7000);
        driver.findElement(By.xpath("//div[@class='panel header']//button[@type='button']")).click();
        driver.findElement(By.xpath("//div[@aria-hidden='false']//a[normalize-space()='Sign Out']")).click();
    }

    public void close_Browser() throws InterruptedException {
        Thread.sleep(7000);
        driver.quit();
    }
    
    public void addToCart() throws InterruptedException {
    	Actions act=new Actions(driver);

		WebElement mensClothing = driver.findElement(By.linkText("Men"));
		act.moveToElement(mensClothing).perform();
		Thread.sleep(7000);

		WebElement mensClothingCategory = driver.findElement(By.linkText("Bottoms"));        
		mensClothingCategory.click();

		WebElement category= driver.findElement(By.xpath("//div[contains(text(),'Category')]"));
		category.click();

		WebElement pants = driver.findElement(By.xpath("//li//a[contains(text(),'Pants')]"));
		pants.click();
		Thread.sleep(7000);

		WebElement product = driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']//li[1]"));
		product.click();

		WebElement size = driver.findElement(By.xpath("//div[@class='swatch-attribute-options clearfix']//div[contains(text(),'32')]"));
		size.click();
		Thread.sleep(7000);

		WebElement color =driver.findElement(By.xpath("//div[@class='swatch-attribute-options clearfix']//div[@option-label='Black']"));
		color.click();
		Thread.sleep(7000);
		
		WebElement addToCart=driver.findElement(By.xpath("//button[@class='action primary tocart']"));
		addToCart.click();
		Thread.sleep(7000);
		
		String Success_Msg=driver.findElement(By.xpath("//div[@class='messages']")).getText();
		System.out.println("Success Message : "+Success_Msg+"\n");
    }
    
    
    public void removeFromCart() 
    {
    	WebElement cartIcon=driver.findElement(By.xpath("//div[@data-block=\"minicart\"]"));
    	cartIcon.click();
    	
    	WebElement cartView=driver.findElement(By.xpath("//a[@class='action viewcart']"));
    	cartView.click();
    	
    	WebElement removeIcon=driver.findElement(By.xpath("//a[@class='action action-delete']"));
    	removeIcon.click();
    	
    	String msg=driver.findElement(By.xpath("//div[@class='cart-empty']//p")).getText();
    	System.out.println(msg+"\n");
    }
    
    public void shippingDetails() throws InterruptedException {
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
		phoneNumber.sendKeys("1234567893");

		Select stateOption=new Select(state);
		stateOption.selectByValue("43");
		Thread.sleep(7000);

		Select countryOption=new Select(country);
		countryOption.selectByValue("US");
		Thread.sleep(7000);		
    }

}
