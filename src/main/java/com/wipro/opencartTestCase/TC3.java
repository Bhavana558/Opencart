package com.wipro.opencartTestCase;


	

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

	
	public class TC3 {


		WebDriver driver;
		Properties pro;

	  @BeforeClass
	  public void Intial() throws IOException 
	  {
	  System.setProperty("webdriver.chrome.driver","D://Selenium Jars//chromedriver.exe");
	  driver = new ChromeDriver();
	  File file = new File(System.getProperty("user.dir")+"/src/main/java/Objrepo.properties");	
	  FileInputStream fis = new FileInputStream(file);
	  pro = new Properties();
	  pro.load(fis);
	  
	  }
	  
	  @Test( dataProvider="address",priority=1)
	  public void logincred( String Fname, String Lname, String newadd, String City,String postc) throws IOException, InterruptedException
	  {
	 	 driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	 	 driver.get(pro.getProperty("Opencart.URL"));
	 	 driver.manage().window().maximize();
	 	 driver.findElement(By.linkText(pro.getProperty("login.linktext"))).click();
	 	 Thread.sleep(3000);
	 	 driver.findElement(By.name(pro.getProperty("email2.name"))).sendKeys(pro.getProperty("email2.value"));
	 	 driver.findElement(By.name(pro.getProperty("password.name"))).sendKeys(pro.getProperty("passowrd.value"));
	 	 driver.findElement(By.xpath(pro.getProperty("login.submit.xpath"))).click();
	 	 System.out.println("Login Successful");
	 				
	 
	  	   Assert.assertTrue(driver.findElement(By.xpath(pro.getProperty("validation.username"))).getText().contains(pro.getProperty("username.verify")),"Wrong user");
	  	   System.out.println("anusha logged in");	
	  	   
	 driver.findElement(By.linkText(pro.getProperty("Home.linktext"))).click();
	 driver.findElement(By.className(pro.getProperty("img.class"))).click();
	 driver.findElement(By.xpath(pro.getProperty("tab.img.xpath"))).click();
	
	 BufferedWriter buffwr= new BufferedWriter(new FileWriter("D:\\imgc.txt"));
	 String imagecount = driver.findElement(By.id(pro.getProperty("img.count.id"))).getText();
	 buffwr.write(imagecount);
	 buffwr.close();
	 
	 
	 for(int i=1;i<=7;i++)
	 {
		 Thread.sleep(3000);
	driver.findElement(By.xpath(pro.getProperty("next.img.click"))).click();
	System.out.println("loop");
	 }
	driver.findElement(By.xpath(pro.getProperty("close.sub.img"))).click();
	Thread.sleep(3000);
	driver.findElement(By.id(pro.getProperty("id.addtocart"))).click();
	driver.findElement(By.xpath(pro.getProperty("shoppingcart.ribbon.xpath"))).click();
	driver.findElement(By.id(pro.getProperty("estimate.button.id"))).click();
	driver.findElement(By.id(pro.getProperty("quotes.id"))).click();
	Thread.sleep(3000);
	driver.findElement(By.id(pro.getProperty("flatshipping.id"))).click();
	driver.findElement(By.id(pro.getProperty("applyshipping.id"))).click();
	String totalval=driver.findElement(By.xpath(pro.getProperty("value.file.xpath"))).getText();
	BufferedWriter buwr= new BufferedWriter(new FileWriter("D:\\totval.txt"));
	buwr.write(totalval);
	buwr.close();

	driver.findElement(By.id(pro.getProperty("coupon.id"))).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath(pro.getProperty("coupon.xpath"))).sendKeys(pro.getProperty("coupon.value"));
	driver.findElement(By.className(pro.getProperty("applycoupon.class"))).click();
	Thread.sleep(3000);

	String Sucsmsg= driver.findElement(By.xpath(pro.getProperty("msg.file"))).getText();
		BufferedWriter buscmg= new BufferedWriter(new FileWriter("D:\\successmsg.txt"));
		buscmg.write(Sucsmsg);
		buscmg.close();

	driver.findElement(By.xpath(pro.getProperty("checkout.xpath"))).click();
	Thread.sleep(3000);

	driver.findElement(By.id(pro.getProperty("newaddress.xpath"))).click();

	driver.findElement(By.name(pro.getProperty("firstname.name"))).sendKeys(Fname);
	driver.findElement(By.name(pro.getProperty("lastname.name"))).sendKeys(Lname);
	driver.findElement(By.name(pro.getProperty("address.name"))).sendKeys(newadd);
	driver.findElement(By.name(pro.getProperty("city.name"))).sendKeys(City);
	driver.findElement(By.name(pro.getProperty("post.name"))).sendKeys(postc);
	WebElement Conty = driver.findElement(By.name("country_id"));
	Select country = new Select(Conty);
	country.selectByVisibleText("India");
	Conty.sendKeys(Keys.ENTER);
	WebElement Region = driver.findElement(By.name("zone_id"));
	Select state = new Select(Region);
	state.selectByVisibleText("Andhra Pradesh");
	Region.sendKeys(Keys.ENTER);

	driver.findElement(By.id(pro.getProperty("continue.id"))).click();
	driver.findElement(By.xpath(pro.getProperty("newadd.xpath"))).click();
	Thread.sleep(3000);
	driver.findElement(By.id(pro.getProperty("delivery.continue.xpath"))).click();
	driver.findElement(By.name(pro.getProperty("add.comments.name"))).sendKeys(pro.getProperty("comments.send"));
	Thread.sleep(3000);
	driver.findElement(By.id(pro.getProperty("continue.shipping.id"))).click();
	Thread.sleep(3000);
	driver.findElement(By.name(pro.getProperty("terms.checkbox.name"))).click();
	driver.findElement(By.linkText(pro.getProperty("t&c.linktext"))).click();
	driver.findElement(By.xpath(pro.getProperty("close.sub.img"))).click();
	Thread.sleep(3000);
	driver.findElement(By.id(pro.getProperty("continue.payment.id"))).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath(pro.getProperty("billing.modify.xpath"))).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath(pro.getProperty("oldadd.xpath"))).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath(pro.getProperty("delivery.continue.xpath"))).click();
	Thread.sleep(3000);
	driver.findElement(By.id(pro.getProperty("continue.shipping.id"))).click();
	Thread.sleep(3000);
	driver.findElement(By.id(pro.getProperty("confirm.order.id"))).click();

	
	driver.findElement(By.partialLinkText(pro.getProperty("logout.partiallinktext"))).click();
	String Logout=driver.getTitle();
	Assert.assertEquals(Logout,"Account Logout");
	System.out.println(Logout);
	  }
	  
	  @DataProvider(name="address")
	  public static Object[][] ExcelData1() throws Exception
	  {
		   Object[][] obj1=ExcelData.reviewdata("address");
		return obj1;
	 
	    
		}

	
	  }


