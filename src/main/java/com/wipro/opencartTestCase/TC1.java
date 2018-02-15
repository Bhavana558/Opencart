package com.wipro.opencartTestCase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.BindException;
import java.sql.BatchUpdateException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
public class TC1{
	WebDriver driver;
	Properties pro;
	String Name;
	String emailc;
	XSSFSheet sheet;
	XSSFWorkbook workbook;
	
	
	
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
  public void opencart() throws IOException
  {
	  
	    driver.get(pro.getProperty("Opencart.URL"));
	    driver.findElement(By.xpath(pro.getProperty("CAccount"))).click();
	    driver.manage().window().maximize();		
  }
  
  
//Reading the excel for Registration
  @DataProvider(name="RegistrationData")
  public static Object[][] ExcelData1() throws Exception
  {
	   Object[][] obj1=ExcelData.reviewdata("Registration");
	return obj1;
	  
  }
//Reading the excel for Registration
  @DataProvider(name="ReviewData")
  public static Object[][] ExcelData2() throws Exception
  {
	   Object[][] obj1=ExcelData.reviewdata("Review");
	return obj1;
}
   
 @Test(dataProvider="RegistrationData",priority=2)
  public void Launchbrowser(String FirstName, String LastName,String EMail, String Telephone, String Company, String Address1,String City, String PostCode,String Password,String PasswordConfirm) throws IOException, BatchUpdateException
  {
    driver.findElement(By.xpath(pro.getProperty("fname"))).sendKeys(FirstName);
    Name= driver.findElement(By.xpath(pro.getProperty("fname"))).getAttribute("value");
    WebElement Lname = driver.findElement(By.xpath(pro.getProperty("Lname")));
    Lname.sendKeys(LastName); 
    driver.findElement(By.xpath(pro.getProperty("Email"))).sendKeys(EMail); 
    emailc=driver.findElement(By.xpath(pro.getProperty("Email"))).getAttribute("value");
    WebElement phonenumber = driver.findElement(By.xpath(pro.getProperty("phonenum")));
    phonenumber.sendKeys(Telephone); 
    WebElement companyname = driver.findElement(By.xpath(pro.getProperty("CompanyN")));
    companyname.sendKeys(Company); 
    WebElement addressad= driver.findElement(By.xpath(pro.getProperty("Address")));
    addressad.sendKeys(Address1);
    WebElement cityname= driver.findElement(By.xpath(pro.getProperty("cityna")));
    cityname.sendKeys(City);
    WebElement pinc= driver.findElement(By.xpath(pro.getProperty("pincode1")));
    pinc.sendKeys(PostCode);
    WebElement Conty = driver.findElement(By.name("country_id"));
    Select country = new Select(Conty);
    country.selectByVisibleText("India");
    Conty.sendKeys(Keys.ENTER);
    WebElement Region = driver.findElement(By.name("zone_id"));
    Select state = new Select(Region);
    state.selectByVisibleText("Andhra Pradesh");
   Region.sendKeys(Keys.ENTER); 
   WebElement pwd = driver.findElement(By.name("password"));
   pwd.sendKeys(Password);
   WebElement Cnfm = driver.findElement(By.name("confirm"));
		   Cnfm.sendKeys(PasswordConfirm);
   driver.findElement(By.xpath(pro.getProperty("Privcy"))).click();
   driver.findElement(By.xpath(pro.getProperty("Continue"))).click();
   String Title=driver.getTitle();
   System.out.println(Title);
  }
 
  
  
	  
@Test(priority=3)
	public void steps() throws IOException, InterruptedException{
	Thread.sleep(3000);
	driver.findElement(By.xpath(pro.getProperty("contact"))).click();
	String lognam=driver.findElement(By.name(pro.getProperty("Namelog"))).getAttribute("value");
	Assert.assertEquals(Name, lognam);
	System.out.println("Verified");
	
	String logmail=driver.findElement(By.name(pro.getProperty("emaillog"))).getAttribute("value");
	Assert.assertEquals(emailc,logmail );
	System.out.println("Verified");
	driver.findElement(By.name(pro.getProperty("Enquiry.name"))).sendKeys(pro.getProperty("Enquirydesc"));
	driver.findElement(By.name("captcha")).clear();
	System.out.println("Enter captcha: ");
	Scanner scan= new Scanner(System.in);
	String capname= scan.nextLine();
	driver.findElement(By.name("captcha")).sendKeys(capname);
	driver.findElement(By.xpath(pro.getProperty("contactContin"))).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath(pro.getProperty("NextCont"))).click();
	Thread.sleep(3000);
	driver.findElement(By.className(pro.getProperty("Homeimage"))).click();
	 driver.findElement(By.xpath(pro.getProperty("Reviewtab.xpath"))).click();
} 
@Test(dataProvider="ReviewData",priority=4)
public void ratingsteps(String revname, String revrew, String revrating) throws InterruptedException{
	int rate=Integer.parseInt(revrating);
driver.findElement(By.name(pro.getProperty("Reviewname"))).clear();
driver.findElement(By.name(pro.getProperty("Reviewname"))).sendKeys(revname);	 
driver.findElement(By.xpath(pro.getProperty("reviewdesc.xpath"))).clear();
driver.findElement(By.xpath(pro.getProperty("reviewdesc.xpath"))).sendKeys(revrew);
int revrewlen=revrew.length();
Thread.sleep(3000);
if (rate==1)
{
	driver.findElement(By.xpath(pro.getProperty("rating1.xpath"))).click();
}
else 
	if(rate==2)
{
	driver.findElement(By.xpath(pro.getProperty("rating2.xpath"))).click();
}
else 
	if(rate==3)
{
	driver.findElement(By.xpath(pro.getProperty("rating3.xpath"))).click();
}	
else 
	if(rate==4)
{
	driver.findElement(By.xpath(pro.getProperty("rating4.xpath"))).click();
}
else 
	if(rate==5)
{
	driver.findElement(By.xpath(pro.getProperty("rating5.xpath"))).click();
}
driver.findElement(By.name("captcha")).clear();
System.out.println("Enter captcha: ");
Scanner scan= new Scanner(System.in);
String captc= scan.nextLine();
driver.findElement(By.name("captcha")).sendKeys(captc);

driver.findElement(By.id(pro.getProperty("review.continue"))).click();
Thread.sleep(3000);
if (revrewlen<25){
Assert.assertTrue(driver.findElement(By.xpath(pro.getProperty("Warning.xpath"))).getText().contains("Warning"),"Incorrect warning msg displayed");
}
else{
Assert.assertTrue(driver.findElement(By.xpath(pro.getProperty("Successwarning.xpath"))).getText().contains("approval"),"Incorrect success msg displayed");

}
	
}


@DataProvider(name="reviewdata")
public static Object[][] reviewrate() throws Exception
{
Object[][] obj = ExcelData1();
return obj;
}

@Test(priority=5)
public void wishlist() throws InterruptedException, IOException {
driver.findElement(By.xpath(pro.getProperty("wishlink.xpath"))).click();
System.out.println("added to wishlist");
Thread.sleep(3000);
driver.findElement(By.xpath(pro.getProperty("closlink.wishlnk.xpath"))).click();
Thread.sleep(3000);
driver.findElement(By.id(pro.getProperty("wishlist.link.id"))).click();
String TestFile = "D:\\temp.txt";
File FC = new File(TestFile);
FC.createNewFile();
WebElement pound=driver.findElement(By.xpath(pro.getProperty("pound.xpath")));
pound.click();
String poundvalue=driver.findElement(By.xpath(pro.getProperty("poundcurrval.xpath"))).getText();
WebElement euro=driver.findElement(By.xpath(pro.getProperty("euro.xpath")));
euro.click();
String euroval=driver.findElement(By.xpath(pro.getProperty("eurocurrval.xpath"))).getText();
WebElement dollar=driver.findElement(By.xpath(pro.getProperty("dollar.xpath")));
dollar.click();
String dollarval=driver.findElement(By.xpath(pro.getProperty("dollarcurrval.xpath"))).getText();
FileWriter FW = new FileWriter(TestFile);
BufferedWriter BW = new BufferedWriter(FW);
BW.write(poundvalue);
BW.newLine();
BW.write(euroval);
BW.newLine();
BW.write(dollarval);
BW.close();
driver.findElement(By.xpath(pro.getProperty("addtocart.xpath"))).click();
Thread.sleep(3000);
driver.findElement(By.xpath(pro.getProperty("closesucess.xpath"))).click();
Thread.sleep(3000);
driver.findElement(By.className("button")).click();
driver.findElement(By.partialLinkText(pro.getProperty("logout.partiallinktext"))).click();
String Logout=driver.getTitle();
Assert.assertEquals(Logout,"Account Logout");
System.out.println(Logout);
}

}

       

         
         
  
  
  
 

