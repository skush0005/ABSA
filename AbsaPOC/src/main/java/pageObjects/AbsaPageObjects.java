package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resourceBase.WebBase;

public class AbsaPageObjects extends WebBase {
 
	public static WebDriver driver;
	
	public static WebElement lnk_Login_signout() {
		 String sLocator = "//span[contains(text(),'Log Out')]"; 
		 try {			 
			 WebBase.WaitForElement(By.xpath(sLocator),"lnk_Login_signout"); 
			 return driver.findElement(By.xpath(sLocator));		    	
	    }catch (Exception e) {
	    	 return null;	
	 	 }		
		 
	    }
	
	// Dynamic wait for the elements
	 public static WebDriver getDriver()
	    {
	        return driver;
	    }	
	
}
