package test_Cases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common_Objects.Objects;

public class TC024_VerifyProductComparison {
	
	 WebDriver driver;
	    Objects ob;

	    @BeforeTest
	    public void browser_Setup() throws InterruptedException
	    {
	        driver = new ChromeDriver();
	        ob = new Objects(driver);
	        ob.url();
	    }

	    @Test
	    public void VerifyProductComparison() 
	    {
	        try {
	            ob = new Objects(driver);
	            ob.login();

	            Actions act = new Actions(driver);

	            WebElement mensClothing = driver.findElement(By.linkText("Men"));
	            act.moveToElement(mensClothing).perform();
	            Thread.sleep(2000);

	            WebElement mensClothingCategory = driver.findElement(By.linkText("Tops"));
	            mensClothingCategory.click();

	            WebElement category = driver.findElement(By.xpath("//div[contains(text(),'Category')]"));
	            category.click();
	            Thread.sleep(2000);

	            WebElement tanks = driver.findElement(By.xpath("//li//a[contains(text(),'Tanks')]"));
	            tanks.click();

	            WebElement product = driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']//li[2]"));
	            product.click();

	            WebElement compareBtn = driver.findElement(By.xpath("//div[@class='product-addto-links']//a[2]"));
	            compareBtn.click();

	            driver.navigate().to("https://magento.softwaretestingboard.com/men/tops-men.html?cat=17");

	            WebElement product1 = driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']//li[3]"));
	            product1.click();

	            WebElement compareBtn1 = driver.findElement(By.xpath("//div[@class='product-addto-links']//a[2]"));
	            compareBtn1.click();

	            WebElement compareLink = driver.findElement(By.xpath("//a[@title='Compare Products']"));
	            compareLink.click();
	            Thread.sleep(2000);

	            List<WebElement> comparedProducts = driver.findElements(By.xpath("//div[@class='column main']//tbody//tr//td"));
	            Assert.assertTrue(comparedProducts.size() >= 2, "Comparison page does not display the selected products side-by-side");

	            for (WebElement productDetails : comparedProducts) {
	                System.out.println("Details of compared products: " + productDetails.getText()+"\n");
	            }      

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
	        ob = new Objects(driver);
	        ob.close_Browser();
	    }

}
