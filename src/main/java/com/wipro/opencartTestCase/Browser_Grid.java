package com.wipro.opencartTestCase;

import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Browser_Grid {
	WebDriver driver;
	DesiredCapabilities capabilities;
	
    String url="http://10.159.34.105:4444/wd/hub";
	
	public void BrowserType(String Browser,WebDriver driver ){
		
 try {
    capabilities = new DesiredCapabilities();
   capabilities.setBrowserName(Browser);
   capabilities.setPlatform(Platform.WINDOWS);
   driver=new RemoteWebDriver(new URL(url),capabilities);
   this.driver=driver;
}catch(Exception e){
   e.printStackTrace();
}

}

}
