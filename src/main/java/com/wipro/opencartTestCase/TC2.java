package com.wipro.opencartTestCase;


	
	import java.awt.RenderingHints.Key;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

	public class TC2 {

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
	  
	 	
	 @Test(priority=1)
	 public void logincred() throws IOException, InterruptedException
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
	 }
	 @DataProvider(name="searchfld")
	  public static Object[][] ExcelData1() throws Exception
	  {
		   Object[][] obj1=ExcelData.reviewdata("searchfld");
		return obj1;
	  }
	 	   
	@Test(dataProvider="searchfld" ,priority=2)	  
	public void searchtest(String searchvalue) throws IOException, InterruptedException{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	 	 WebElement search= driver.findElement(By.name(pro.getProperty("searchfield.name")));
	 	 search.sendKeys(searchvalue);
	  	 search.sendKeys(Keys.ENTER);
	  	 
	  	 String Actual=driver.findElement(By.className(pro.getProperty("classname.count"))).getText().substring(18,20);
	  	 System.out.println(Actual);
	  	 
	
	  	String countFile = "D:\\Data\\count.txt";
	  	File CF = new File(countFile);
	  	CF.createNewFile();
	  	FileWriter FW = new FileWriter(countFile);
	  	BufferedWriter BW = new BufferedWriter(FW);
	  	BW.write(Actual);
	  	BW.newLine();
	  	BW.close();	  	
	  	Thread.sleep(3000);
	  	WebElement down= driver.findElement(By.name(pro.getProperty("category.dropdown.name")));
	   	Select dropdwn=new Select(down);
	  	dropdwn.selectByValue(pro.getProperty("valuetext.dropdown"));
	  	String dropdowncount=driver.findElement(By.name(pro.getProperty("category.dropdown.name"))).getText();
	  	System.out.println(dropdowncount);
	  	String dropfile = "D:\\Data\\dropcount.txt";
	  	File counfile = new File(dropfile);
	  	counfile .createNewFile();
	  	FileWriter Filedown = new FileWriter(dropfile);
	  	BufferedWriter downbuffer = new BufferedWriter(Filedown);
	  	downbuffer.write(dropdowncount);
	  	downbuffer.newLine();
	  	downbuffer.close();	
	  	driver.findElement(By.id(pro.getProperty("searchclick.id"))).click();
	  	driver.findElement(By.linkText(pro.getProperty("linktext.phoneslink"))).click();
	  	List<WebElement> Phonesv=driver.findElements(By.xpath("//div[@class='product-list']/div"));
	  	System.out.println("div data is "+Phonesv.size());
	  	int itemcount=Phonesv.size();
	  	String numberAsString = Integer.toString(itemcount); 
	  	String phonesval = "D:\\Data\\phoneval.txt";
	  	File phnfile = new File(phonesval);
	  	phnfile .createNewFile();
	  	FileWriter Fileph = new FileWriter(phonesval);
	  	BufferedWriter phnbuffer = new BufferedWriter(Fileph);
	  	phnbuffer.write(numberAsString);
	  	phnbuffer.newLine();
	  	phnbuffer.close();	 	
	  	WebElement sort=driver.findElement(By.xpath(pro.getProperty("xpath.sortBy")));
	  	sort.click();
	  	Select sortsel = new Select(sort);
	  	sortsel.selectByVisibleText(pro.getProperty("sortselect.text"));
	  	String campare = "D:\\Data\\phonecomp.txt";
	  	File compareph = new File(campare);
	  	compareph .createNewFile();
	  	FileWriter cmpph = new FileWriter(compareph);
	  	BufferedWriter phonebuffer = new BufferedWriter(cmpph);
	  	for(int i=1;i<=3;i++){
	  		Thread.sleep(2000);
	  		driver.findElement(By.xpath("(//div[@class='product-list']//div[@class='compare']/a)["+i+"]")).click();	
	  String phonename=null;
	  phonename=driver.findElement(By.xpath("(//div[@class='name']/a)["+i+"]")).getText();
	   		phonebuffer.write(phonename);
	  		phonebuffer.newLine();
	  		phonebuffer.flush();
	  	}
	  	phonebuffer.close();
	  	Thread.sleep(3000);
	driver.findElement(By.xpath(pro.getProperty("close.compare.xpath"))).click();
	driver.findElement(By.xpath(pro.getProperty("productcompare.xpath"))).click();
	driver.findElement(By.xpath(pro.getProperty("click.item.xpath"))).click();
	BufferedWriter buwr= new BufferedWriter(new FileWriter("D:\\Filename.txt"));
	String desc=driver.findElement(By.xpath(pro.getProperty("5thdesc.xapth"))).getText();
	buwr.write(desc);
	buwr.close();
	driver.findElement(By.id(pro.getProperty("id.addtocart"))).click();
	driver.findElement(By.xpath(pro.getProperty("shoppingcart.ribbon.xpath"))).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath(pro.getProperty("xpath.checkout"))).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath(pro.getProperty("continue1.xpath"))).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath(pro.getProperty("continue.shipping.xpath"))).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath(pro.getProperty("continue.shipping.method.xpath"))).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath(pro.getProperty("agree.check.xpath"))).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath(pro.getProperty("final.continue.xpath"))).click();
	Thread.sleep(3000);
	driver.findElement(By.id(pro.getProperty("confirmorder.id"))).click();
	Thread.sleep(3000);
	driver.navigate().back();
	String valibck=driver.findElement(By.xpath(pro.getProperty("validate.back.xpath"))).getText();
	String validaexpec="Your shopping cart is empty!";
	Assert.assertEquals(valibck, validaexpec);
	driver.findElement(By.partialLinkText(pro.getProperty("Order.History.partial"))).click();
	driver.findElement(By.xpath(pro.getProperty("newsletter.subs.xpath"))).click();
	driver.findElement(By.partialLinkText(pro.getProperty("special.partial.text"))).click();
	driver.findElement(By.partialLinkText(pro.getProperty("logout.partial"))).click();
	 }

	
	}
