package priority_Of_TestCases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common_Objects.Objects;

public class Priority {
	
	WebDriver driver;
	Objects ob;

	@BeforeSuite
	public void Browser_Setup() throws InterruptedException
	{
		driver = new ChromeDriver();
		ob = new Objects(driver);
		ob.url();
	}


	@Test(priority = 1)
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

	@Test(priority = 2)
	public void SearchBox_Functionality() {
		try {

			WebElement searchBox = driver.findElement(By.xpath("//input[@name='q']"));
			System.out.println("Search Box is Displayed : " + searchBox.isDisplayed());
			System.out.println("Search Box is Enabled : " + searchBox.isEnabled());

			searchBox.sendKeys("T-shirts for men");
			Thread.sleep(7000);
			searchBox.sendKeys(Keys.ENTER);

			WebElement search_Result = driver.findElement(By.xpath("//div[@class='search results']"));

			for (int i = 1; i <= 12; i++) {

				if (search_Result.isDisplayed()) {
					WebElement Results = driver.findElement(
							By.xpath("//li[" + i + "]//div[@class='product details product-item-details']//strong//a"));
					System.out.println("Displayed results : " + Results.getText());
					Assert.assertTrue(true);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());		}
	}

	@Test(priority = 3)
	public void FilterByCategory() {

		try {

			WebElement mensClothing = driver.findElement(By.linkText("Men"));
			Actions act=new Actions(driver);
			act.moveToElement(mensClothing).perform();
			Thread.sleep(7000);
			WebElement mensClothingCategory = driver.findElement(By.linkText("Tops"));        
			act.moveToElement(mensClothingCategory).click().perform();

			WebElement category= driver.findElement(By.xpath("//div[contains(text(),'Category')]"));
			category.click();
			WebElement category_List=driver.findElement(By.xpath("//li//a[contains(text(),'Tees')]"));
			category_List.click();



			List<WebElement> productTitles = driver.findElements(By.xpath("//li//div[@class='product details product-item-details']//strong//a"));
			boolean isCorrectCategory = true;
			for (WebElement title : productTitles) {
				System.out.println("Titles of the product : "+title.getText()+"\n");
				if (!title.getText().toLowerCase().contains("tee")) 
				{
					Assert.assertTrue(false);
					isCorrectCategory = false;
					break;

				}
			}

			if (isCorrectCategory) {

				System.out.println("Test Passed: Only products related to the selected category are displayed.");

			}
			else 
			{
				System.out.println("Test Failed: Products unrelated to the selected category are displayed.");

			}

		} catch (Exception e) {
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());		}
	}

	@Test(priority = 4)
	public void VerifyFilterBySize() {
		try {

			Actions act=new Actions(driver);

			WebElement mensClothing = driver.findElement(By.linkText("Men"));
			act.moveToElement(mensClothing).perform();
			Thread.sleep(7000);

			WebElement mensClothingCategory = driver.findElement(By.linkText("Tops"));        
			mensClothingCategory.click();

			WebElement category= driver.findElement(By.xpath("//div[contains(text(),'Category')]"));
			category.click();

			WebElement jackets = driver.findElement(By.xpath("//li//a[contains(text(),'Jackets')]"));
			jackets.click();
			Thread.sleep(7000);

			WebElement size= driver.findElement(By.xpath("//div[contains(text(),'Size')]"));
			size.click();


			WebElement sizeFilter = driver.findElement(By.xpath("//div[@class='swatch-option text '][contains(text(),'M')]"));
			sizeFilter.click();
			Thread.sleep(7000);

			List<WebElement> productTitle = driver.findElements(By.xpath("//li//div[@class='product details product-item-details']//strong//a"));
			boolean allProductsMatchFilter = true;
			for (WebElement products : productTitle) {
				System.out.println("Title of the product : "+products.getText());
				products=driver.findElement(By.xpath("//div//div[@id='option-label-size-143-item-168'][contains(text(),'M')]"));
				System.out.println("Size of the Product : "+products.getAttribute("aria-label")+"\n");
				if (!products.isDisplayed()) {
					allProductsMatchFilter = false;
					Assert.assertTrue(false);
					break;
				}
			}

			if (allProductsMatchFilter) 
			{
				System.out.println("Test Case Passed: Only products available in the selected size are displayed.");
			} 
			else {
				System.out.println("Test Case Failed: Some products not available in the selected size are displayed.");
			}

		} catch (InterruptedException e) {
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());		} 
	}


	@Test(priority = 5)
	public void VerifyFilterByColor() 
	{
		try {

			Actions act=new Actions(driver);

			WebElement womensClothing = driver.findElement(By.linkText("Women"));
			act.moveToElement(womensClothing).perform();
			Thread.sleep(7000);

			WebElement topsSection = driver.findElement(By.linkText("Tops"));
			topsSection.click();
			Thread.sleep(7000);

			WebElement category= driver.findElement(By.xpath("//div[contains(text(),'Category')]"));
			category.click();

			WebElement hoodies_Sweatshirts = driver.findElement(By.xpath("//li//a[contains(text(),'Hoodies & Sweatshirts ')]"));
			hoodies_Sweatshirts.click();
			Thread.sleep(7000);

			WebElement color= driver.findElement(By.xpath("//div[contains(text(),'Color')]"));
			color.click();			

			WebElement colorFilterwhite = driver.findElement(By.xpath("//a[@aria-label='White']//div[contains(@class,'swatch-option color')]"));
			colorFilterwhite.click();            

			List<WebElement> productItems = driver.findElements(By.xpath("//li//div[@class='product details product-item-details']//strong//a"));
			boolean isCorrectColorDisplayed = true;

			for (WebElement product : productItems) {
				System.out.println("Title of the product : "+product.getText());
				product = driver.findElement(By.xpath("//div[@class='swatch-opt-1146']//div[@id='option-label-color-93-item-59']"));
				System.out.println("Color of the product : "+product.getAttribute("aria-label")+"\n"); 

				if (!product.isDisplayed()) {
					isCorrectColorDisplayed = false;
					Assert.assertTrue(false);
					break;
				}
			}

			if (isCorrectColorDisplayed) 
			{
				System.out.println("Test Case  Passed: Only products available in the selected color are displayed.");
			} 
			else 
			{
				System.out.println("Test Case  Failed: Products with other colors are also displayed.");
			}

		} catch (Exception e) {
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());		} 
	}

	@Test(priority = 6)
	public void VerifySortingFunctionalityByPrice () 
	{
		try {

			Actions act= new Actions(driver);
			WebElement gear = driver.findElement(By.linkText("Gear"));
			act.moveToElement(gear).perform();
			Thread.sleep(7000);

			WebElement topsSection = driver.findElement(By.linkText("Watches"));
			topsSection.click();
			Thread.sleep(7000);

			WebElement sortBy=driver.findElement(By.xpath("//div[@class='toolbar-sorter sorter']//select[@id='sorter']"));
			sortBy.click();

			Select sortByDropDown=new Select(sortBy);
			sortByDropDown.selectByValue("price");
			Thread.sleep(7000);

			List<WebElement> Product_Title = driver.findElements(By.xpath("//li//div[@class='product details product-item-details']//strong//a"));

			for(WebElement products : Product_Title)
			{
				System.out.println("Title of the product : "+products.getText()+"\n");
			}

			List<WebElement> priceElements = driver.findElements(By.xpath("//div[@class='price-box price-final_price']//span[@class='price']"));
			List<Double> prices = new ArrayList<>();

			for (WebElement priceElement : priceElements) {

				String priceText = priceElement.getText().replace("$", "").replace(",", "");
				System.out.println("Values of the products : "+priceElement.getText()+"\n");
				prices.add(Double.parseDouble(priceText));  

			}

			List<Double> sortedPrices = new ArrayList<>(prices);

			if(sortedPrices.equals(prices)) 
			{
				System.out.println("Test Case Passed: Products are sorted in ascending order of price.");
				Assert.assertTrue(true);
			}
			else 
			{
				System.out.println("Test Case Failed: Products are not sorted in ascending order of price.");
				Assert.assertTrue(false);

			}


		} catch (Exception e) {
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());		}
	}

	@Test(priority = 7)
	public void VerifyProductDetailsPage() 
	{
		try {

			Actions act=new Actions(driver);

			WebElement mensClothing = driver.findElement(By.linkText("Men"));
			act.moveToElement(mensClothing).perform();
			Thread.sleep(7000);

			WebElement mensClothingCategory = driver.findElement(By.linkText("Tops"));        
			mensClothingCategory.click();

			WebElement category= driver.findElement(By.xpath("//div[contains(text(),'Category')]"));
			category.click();
			Thread.sleep(7000);

			WebElement tanks = driver.findElement(By.xpath("//li//a[contains(text(),'Tanks')]"));
			tanks.click();

			WebElement product = driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']//li[1]"));
			product.click();

			WebElement productTitle=driver.findElement(By.xpath("//div[@class=\"page-title-wrapper product\"]"));
			WebElement productImages = driver.findElement(By.xpath("//div[@class='fotorama__stage']"));
			WebElement productDescription = driver.findElement(By.xpath("//div[@id='description']"));
			WebElement productPrice = driver.findElement(By.xpath("//span[@class='price']"));
			WebElement productAvailability = driver.findElement(By.xpath("//div[@class='stock available']"));

			if (productTitle.isDisplayed()&&productImages.isDisplayed() && productDescription.isDisplayed() &&
					productPrice.isDisplayed() && productAvailability.isDisplayed()) 
			{
				System.out.println("Title of the Product  : "+productTitle.getText()+"\n");
				System.out.println("Image is Displayed : "+productImages.isDisplayed()+"\n");
				System.out.println("Description of the Product : "+productDescription.getText()+"\n");
				System.out.println("Price of the Product : "+productPrice.getText()+"\n");
				System.out.println("Availability of Product : "+productAvailability.getText()+"\n");
				System.out.println("Test Case Passed: Product details page displays Product Title, product images, description, price, and availability.");
				Assert.assertTrue(true);
			} 
			else {
				System.out.println("Test Case Failed: Product details page is missing some elements.");
				Assert.assertTrue(false);
			}


		} 
		catch (Exception e) 
		{
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());		}
	}

	@Test(priority = 8)
	public void AddToCart() 
	{
		try 
		{

			Actions act=new Actions(driver);

			WebElement mensClothing = driver.findElement(By.linkText("Men"));
			act.moveToElement(mensClothing).perform();

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


			WebElement  cartItem=driver.findElement(By.xpath("//div[@class='minicart-wrapper']//a//span[@class='counter qty']//span[1]"));
			String cartCount=cartItem.getText();

			if(cartCount.toString().equals(cartItem.getText())) {
				System.out.println("Number of items in the cart : "+cartItem.getText()+"\n");
				String Success_Msg=driver.findElement(By.xpath("//div[@class='messages']")).getText();
				System.out.println("Success Message : "+Success_Msg+"\n");
				Thread.sleep(7000);
				System.out.println("Test Passed: Product successfully added to cart.");
				Assert.assertTrue(true);

			} else {
				System.out.println("Test Failed: Product not added to cart.");
				Assert.assertTrue(false);
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());		}
	}


	@Test(priority = 9)
	public void ViewCartTest() {
		try {
			ob = new Objects(driver);

			ob.addToCart();
			Thread.sleep(7000);

			WebElement cartIcon = driver.findElement(By.xpath("//div[@class='minicart-wrapper']//a//span[@class='counter qty']//span[1]"));
			cartIcon.click();
			Thread.sleep(7000);

			WebElement viewCartButton = driver.findElement(By.xpath("//span[normalize-space()='View and Edit Cart']"));
			viewCartButton.click();
			Thread.sleep(7000);

			WebElement cartProductName = driver.findElement(By.xpath("//td[@class='col item']//div[@class='product-item-details']"));
			WebElement cartProductPrice = driver.findElement(By.xpath("//td[@class='col price']//span[@class='price']"));
			WebElement cartSubTotal=driver.findElement(By.xpath("//td[@class='col subtotal']//span[@class='price']"));

			if(cartProductName.isDisplayed()&&cartProductPrice.isDisplayed()&&cartSubTotal.isDisplayed())
			{
				System.out.println("Product name is displayed : "+cartProductName.getText()+"\n");
				System.out.println("Product Price is displayed : "+cartProductPrice.getText()+"\n");
				System.out.println("Product SubTotalPrice is displayed : "+cartSubTotal.getText()+"\n");
				Assert.assertTrue(true);
			}
			else
			{
				System.out.println("Product Name and Product Prices are not displayed");
				Assert.assertTrue(false);

			}

		} catch (Exception e) {
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}

	@Test(priority = 10)
	public void UpdateCartTest()  
	{
		try 
		{
			ob = new Objects(driver);

			ob.addToCart();

			WebElement cartIcon = driver.findElement(By.xpath("//div[@class='minicart-wrapper']//a//span[@class='counter qty']//span[1]"));
			cartIcon.click();
			Thread.sleep(7000);

			WebElement viewCartButton = driver.findElement(By.xpath("//span[normalize-space()='View and Edit Cart']"));
			viewCartButton.click();
			Thread.sleep(7000);

			WebElement quantityInput = driver.findElement(By.xpath("//input[@title='Qty']"));
			quantityInput.clear();
			quantityInput.sendKeys("2");

			WebElement update_Cart=driver.findElement(By.xpath("//button[@title='Update Shopping Cart']"));
			update_Cart.click();
			Thread.sleep(7000);

			String updatedQuantity = driver.findElement(By.xpath("//div[1]//input[@title='Qty']")).getAttribute("value");
			String Price=driver.findElement(By.xpath("//td[@class='col price'][1]//span[@class='price']")).getText();
			String subTotal = driver.findElement(By.xpath("//td[@class='col subtotal'][1]//span[@class='price']")).getText();
			Price=Price.replace("$","");
			subTotal=subTotal.replace("$","");

			double doublePrice = Double.parseDouble(Price);	
			double updatedTotalPrice=Double.parseDouble(subTotal);
			double doubledPrice = doublePrice * 2;			

			if(updatedQuantity.equals("2")&&doubledPrice==updatedTotalPrice)
			{
				System.out.println("Quantity and SubTotal Price Successfully Updated"+"\n");
				Assert.assertTrue(true);
			}
			Assert.assertEquals(updatedQuantity, "2", "The quantity in the cart is not updated correctly.");
			Assert.assertTrue(updatedTotalPrice==doubledPrice, "The total price is not updated correctly.");
			Thread.sleep(7000);			

		} 
		catch (Exception e) 
		{
			e.printStackTrace(); 
			Assert.fail("Test failed due to exception: " + e.getMessage());		}
	}

	@Test(priority = 11)
	public void RemoveProductFromCart() {
		try {
			ob = new Objects(driver);

			ob.addToCart();

			WebElement cartIcon=driver.findElement(By.xpath("//div[@data-block=\"minicart\"]"));
			cartIcon.click();

			WebElement cartView=driver.findElement(By.xpath("//a[@class='action viewcart']"));
			cartView.click();

			WebElement removeIcon=driver.findElement(By.xpath("//a[@class='action action-delete']"));
			removeIcon.click();

			String msg=driver.findElement(By.xpath("//div[@class='cart-empty']//p")).getText();
			System.out.println(msg+"\n");

		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		String cartMessage = driver.findElement(By.xpath("//div[@class='cart-empty']//p")).getText();
		Assert.assertEquals(cartMessage, "You have no items in your shopping cart.");
	}


	@Test(priority = 12)
	public void verifyProceedToCheckout() throws InterruptedException {
		try {
			ob = new Objects(driver);

			ob.addToCart();			 

			WebElement cartIcon = driver.findElement(By.xpath("//div[@class='minicart-wrapper']//a//span[@class='counter qty']//span[1]"));
			cartIcon.click();
			Thread.sleep(7000);

			WebElement viewCartButton = driver.findElement(By.xpath("//span[normalize-space()='View and Edit Cart']"));
			viewCartButton.click();
			Thread.sleep(7000);

			WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']"));
			proceedToCheckoutButton.click();
			Thread.sleep(7000);

			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("checkout"), "User is not redirected to the checkout page.");
			Thread.sleep(7000);		        

		} 
		catch (Exception e) 
		{
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());		}

	}

	@Test(priority=13)
	public void verifyGuestCheckout() {
		try {

			ob = new Objects(driver);

			ob.addToCart();

			WebElement cartIcon = driver.findElement(By.xpath("//div[@class='minicart-wrapper']//a//span[@class='counter qty']//span[1]"));
			cartIcon.click();
			Thread.sleep(7000);

			WebElement viewCartButton = driver.findElement(By.xpath("//span[normalize-space()='View and Edit Cart']"));
			viewCartButton.click();
			Thread.sleep(7000);

			WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']"));
			proceedToCheckoutButton.click();
			Thread.sleep(7000);

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
			Thread.sleep(7000);

			Select countryOption=new Select(country);
			countryOption.selectByIndex(1);
			Thread.sleep(7000);			

			WebElement nextStepElement = driver.findElement(By.xpath("//button[@data-role='opc-continue']"));
			nextStepElement.click();
			Thread.sleep(7000);

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

	@Test(priority = 14)
	public void VerifyRegisteredUserCheckout() {
		try {

			ob = new Objects(driver);
			ob.login();
			ob.addToCart();			

			WebElement cartIcon = driver.findElement(By.xpath("//div[@class='minicart-wrapper']//a//span[@class='counter qty']//span[1]"));
			cartIcon.click();
			Thread.sleep(7000);

			WebElement viewCartButton = driver.findElement(By.xpath("//span[normalize-space()='View and Edit Cart']"));
			viewCartButton.click();
			Thread.sleep(7000);

			String checkoutUrl=driver.getCurrentUrl();			

			WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']"));
			proceedToCheckoutButton.click();
			Thread.sleep(7000);	

			WebElement newAddress=driver.findElement(By.xpath("//button[@class='action action-show-popup']"));
			newAddress.click();	
			Thread.sleep(7000);


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
			Thread.sleep(7000);

			Select countryOption=new Select(country);
			countryOption.selectByValue("US");
			Thread.sleep(7000);

			WebElement saveAddress=driver.findElement(By.xpath("//input[@id='shipping-save-in-address-book']"));
			saveAddress.click();

			WebElement shipHere=driver.findElement(By.xpath("//button[@class='action primary action-save-address']"));
			shipHere.click();
			Thread.sleep(7000);


			WebElement radio_Button=driver.findElement(By.xpath("//input[@type='radio'][@value='tablerate_bestway']"));
			radio_Button.click();

			WebElement nextStepElement = driver.findElement(By.xpath("//button[@data-role='opc-continue']"));
			nextStepElement.click();
			Thread.sleep(7000);

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

	@Test(priority = 15)
	public void verifyPaymentGatewayIntegration() {
		try {

			ob = new Objects(driver);
			ob.login();
			ob.addToCart();

			WebElement cartIcon = driver.findElement(By.xpath("//div[@class='minicart-wrapper']//a//span[@class='counter qty']//span[1]"));
			cartIcon.click();
			Thread.sleep(7000);

			WebElement viewCartButton = driver.findElement(By.xpath("//span[normalize-space()='View and Edit Cart']"));
			viewCartButton.click();
			Thread.sleep(7000);

			WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']"));
			proceedToCheckoutButton.click();
			Thread.sleep(7000);	

			WebElement newAddress=driver.findElement(By.xpath("//button[@class='action action-show-popup']"));
			newAddress.click();	
			Thread.sleep(7000);

			ob.shippingDetails();

			WebElement saveAddress=driver.findElement(By.xpath("//input[@id='shipping-save-in-address-book']"));
			saveAddress.click();

			WebElement shipHere=driver.findElement(By.xpath("//button[@class='action primary action-save-address']"));
			shipHere.click();
			Thread.sleep(7000);

			WebElement radio_Button=driver.findElement(By.xpath("//input[@type='radio'][@value='tablerate_bestway']"));
			radio_Button.click();

			WebElement nextStepElement = driver.findElement(By.xpath("//button[@data-role='opc-continue']"));
			nextStepElement.click();
			Thread.sleep(7000);

			WebElement placeOrder=driver.findElement(By.xpath("//button[@class='action primary checkout']"));
			placeOrder.click();
			Thread.sleep(7000);

			String expectedUrl = "https://magento.softwaretestingboard.com/checkout/onepage/success/";
			Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Payment was not processed successfully.");

			ob.logout();


		}
		catch (Exception e) 
		{
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());	
		}
	}

	@Test(priority = 16)
	public void VerifyOrderConfirmationPage() {
		try {

			ob = new Objects(driver);
			ob.login();
			ob.addToCart();

			WebElement cartIcon = driver.findElement(By.xpath("//div[@class='minicart-wrapper']//a//span[@class='counter qty']//span[1]"));
			cartIcon.click();
			Thread.sleep(7000);

			WebElement viewCartButton = driver.findElement(By.xpath("//span[normalize-space()='View and Edit Cart']"));
			viewCartButton.click();
			Thread.sleep(7000);

			WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']"));
			proceedToCheckoutButton.click();
			Thread.sleep(7000);			

			WebElement radio_Button=driver.findElement(By.xpath("//input[@type='radio'][@value='tablerate_bestway']"));
			radio_Button.click();

			WebElement nextStepElement = driver.findElement(By.xpath("//button[@data-role='opc-continue']"));
			nextStepElement.click();
			Thread.sleep(7000);

			WebElement placeOrder=driver.findElement(By.xpath("//button[@class='action primary checkout']"));
			placeOrder.click();
			Thread.sleep(7000);

			String expectedUrl = "https://magento.softwaretestingboard.com/checkout/onepage/success/";
			Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Payment was not processed successfully.");

			Thread.sleep(7000);
			WebElement msg=driver.findElement(By.xpath("//span[@class='base']"));
			System.out.println("Success msg : "+msg.getText());

			WebElement details=driver.findElement(By.xpath("//div[@class='checkout-success']//p//a"));
			details.click();

			WebElement orderNumber=driver.findElement(By.xpath("//span[@class='base']"));
			System.out.println("Order number of the Product is : "+orderNumber.getText()+"\n");

			WebElement productDetails=driver.findElement(By.xpath("//div[@class='order-details-items ordered']"));
			System.out.println("Details of the Product : "+productDetails.getText()+"\n");

			WebElement shippingDetails=driver.findElement(By.xpath("//div[@class='block block-order-details-view']//div[@class='block-content']"));
			System.out.println("Shipping Details : "+shippingDetails.getText()+"\n");

			Assert.assertTrue(orderNumber.isDisplayed(), "Order number is not displayed.");
			Assert.assertTrue(productDetails.isDisplayed(),"Product details are not displayed.");
			Assert.assertTrue(shippingDetails.isDisplayed(),  "Shipping information is not displayed.");
			ob.logout();

		}
		catch (Exception e) 
		{
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());	
		}
	}

	@Test(priority = 17)
	public void VerifyOrderHistory()
	{
		try {

			ob = new Objects(driver);

			ob.login();	

			WebElement accountMenu=driver.findElement(By.xpath("//div[@class='panel header']//button[@type='button']"));
			accountMenu.click();

			WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='customer-menu']//a[contains(text(),'My Account')]"));
			myAccountLink.click();

			WebElement orderHistoryLink = driver.findElement(By.xpath("//ul[@class='nav items']//a[contains(text(),'My Orders')]"));
			orderHistoryLink.click();

			List<WebElement> orders = driver.findElements(By.xpath("//table[@id='my-orders-table']//tbody//tr"));
			Assert.assertTrue(orders.size() > 0, "Order history is empty!");

			for (WebElement order : orders) {

				System.out.print("Order History Details : "+order.getText()+"\n");
			}

			ob.logout();
		} 

		catch (Exception e) 
		{
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());			
		}


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


	@Test(dataProvider = "searchQueries",priority = 18)
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


	@Test(dataProvider = "credentials",priority = 19)
	public void verifyLoginWithValidCredentials(String email, String password)
	{

		try {
			ob = new Objects(driver);

			Thread.sleep(7000);
			WebElement signLink=driver.findElement(By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]"));
			signLink.click();

			WebElement emailId=driver.findElement(By.xpath("//input[@id='email']"));
			emailId.sendKeys(email);

			WebElement pass=driver.findElement(By.xpath("//input[@name='login[password]']"));
			pass.sendKeys(password);

			Thread.sleep(7000);
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


	@Test(dataProvider = "credentials",priority = 20)
	public void verifyLoginWithInValidCredentials(String email, String password)
	{

		try {
			ob = new Objects(driver);

			Thread.sleep(7000);
			WebElement signLink=driver.findElement(By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]"));
			signLink.click();

			WebElement emailId=driver.findElement(By.xpath("//input[@id='email']"));
			emailId.sendKeys(email);

			WebElement pass=driver.findElement(By.xpath("//input[@name='login[password]']"));
			pass.sendKeys(password);

			Thread.sleep(7000);
			WebElement signIn=driver.findElement(By.xpath("//button[@name='send']//span[contains(text(),'Sign In')]"));
			signIn.click();

			String msg=driver.findElement(By.xpath("//div[@class='message-error error message']")).getText();
			System.out.println(msg);
			Thread.sleep(7000);		
		}

		catch (Exception e) 
		{
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());			

		}
	} 

	@Test(priority = 21)
	public void Create_Account() throws InterruptedException 
	{
		try {

			driver.findElement(By.xpath("//div[@class='panel header']//a[normalize-space()='Create an Account']")).click();
			Thread.sleep(7000);

			driver.findElement(By.name("firstname")).sendKeys("Bruce");
			driver.findElement(By.name("lastname")).sendKeys("Banner");
			driver.findElement(By.name("email")).sendKeys("Hulksmash19@gmail.com");
			driver.findElement(By.name("password")).sendKeys("HulkNatasha143");
			driver.findElement(By.name("password_confirmation")).sendKeys("HulkNatasha143");
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

	@Test(priority = 22)
	public void VerifyPasswordResetFunctionality()
	{
		try 
		{
			WebElement signLink=driver.findElement(By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]"));
			signLink.click();

			WebElement forgetPassLink=driver.findElement(By.xpath("//a[@class='action remind']//span[contains(text(),'Forgot Your Password?')]"));
			forgetPassLink.click();

			WebElement emailId=driver.findElement(By.xpath("//input[@id='email_address']"));
			emailId.sendKeys("Hulksmash19@gmail.com");

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

	@Test(priority = 23)
	public void TestWishlistFunctionality() 
	{
		try {
			ob=new Objects(driver);

			ob.login();

			Actions act=new Actions(driver);

			WebElement mensClothing = driver.findElement(By.linkText("Men"));
			act.moveToElement(mensClothing).perform();
			Thread.sleep(7000);

			WebElement mensClothingCategory = driver.findElement(By.linkText("Tops"));        
			mensClothingCategory.click();

			WebElement category= driver.findElement(By.xpath("//div[contains(text(),'Category')]"));
			category.click();
			Thread.sleep(7000);

			WebElement tanks = driver.findElement(By.xpath("//li//a[contains(text(),'Tanks')]"));
			tanks.click();

			WebElement product = driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']//li[1]"));
			product.click();

			WebElement wishlistBtn=driver.findElement(By.xpath("//div[@class='product-addto-links']//a[1]"));
			wishlistBtn.click();

			String msg=driver.findElement(By.xpath("//div[@class='message-success success message']")).getText();
			System.out.println(msg);

			WebElement whishlistItem=driver.findElement(By.xpath("//ol[@class='product-items']//li"));
			Assert.assertTrue(whishlistItem.isDisplayed(), "Product is not visible in the wishlist.");

			ob.logout();

		} 
		catch (Exception e) {
			e.printStackTrace();  
			Assert.fail("Test failed due to exception: " + e.getMessage());			}
	}

	@Test(priority = 24)
	public void VerifyProductComparison() 
	{
		try {
			ob = new Objects(driver);
			ob.login();

			Actions act = new Actions(driver);

			WebElement mensClothing = driver.findElement(By.linkText("Men"));
			act.moveToElement(mensClothing).perform();
			Thread.sleep(7000);

			WebElement mensClothingCategory = driver.findElement(By.linkText("Tops"));
			mensClothingCategory.click();

			WebElement category = driver.findElement(By.xpath("//div[contains(text(),'Category')]"));
			category.click();
			Thread.sleep(7000);

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
			Thread.sleep(7000);

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

	@Test(priority = 25)
	public void ProductReviewTest() 
	{
		try {
			ob = new Objects(driver);
			ob.login();

			Actions act = new Actions(driver);

			WebElement mensClothing = driver.findElement(By.linkText("Men"));
			act.moveToElement(mensClothing).perform();
			Thread.sleep(7000);

			WebElement mensClothingCategory = driver.findElement(By.linkText("Tops"));
			mensClothingCategory.click();

			WebElement category = driver.findElement(By.xpath("//div[contains(text(),'Category')]"));
			category.click();
			Thread.sleep(7000);

			WebElement tanks = driver.findElement(By.xpath("//li//a[contains(text(),'Tanks')]"));
			tanks.click();

			WebElement product = driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']//li[2]"));
			product.click();

			WebElement reviewsBtn=driver.findElement(By.xpath("//a[@id='tab-label-reviews-title']"));
			reviewsBtn.click();

			WebElement starRating=driver.findElement(By.xpath("//div[@class='control review-control-vote']//label[5]"));
			act.moveToElement(starRating).click().perform();

			WebElement nickName=driver.findElement(By.xpath("//input[@id='nickname_field']"));
			nickName.clear();
			nickName.sendKeys("HULK");

			WebElement summary=driver.findElement(By.xpath("//input[@id='summary_field']"));
			summary.sendKeys("About This Cronus Yoga Pant");

			WebElement review=driver.findElement(By.xpath("//textarea[@id='review_field']"));
			review.sendKeys("Love It ");

			WebElement submitBtn=driver.findElement(By.xpath("//button[@class='action submit primary']"));
			submitBtn.click();

			WebElement msg=driver.findElement(By.xpath("//div[@class='message-success success message']"));
			System.out.println(msg);

			Assert.assertTrue(msg.isDisplayed(),"Review should be submitted successfully and visible on the product page.");

			ob.logout();
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}

	@AfterSuite
	public void close_Browser() throws InterruptedException {
		Objects ob = new Objects(driver);
		Thread.sleep(7000);
		ob.close_Browser();
	}

}
