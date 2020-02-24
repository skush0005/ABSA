package components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import resourceBase.WebBase;
import utility.PropertiesCache;


public class Components extends WebBase {
	
	
	public final static String propertyFilePath= System.getProperty("user.dir")+"\\src\\main\\java\\Config\\Configuration.properties";

	public static void initialiseConfig() {
		try  {
			PropertiesCache cache = PropertiesCache.getInstance();
			System.out.println(cache.getProperty("firsstName"));
			cache.setProperty("ComponentStatus", "Pass");
			System.out.println(cache.getProperty("ComponentStatus"));
		}catch (Exception e) {
			System.out.println("Please set the properties file");
		}

	}	
	
	
	public static void launchBrowser(String URL) {
		initialiseConfig();
		childTest = parentTest.createNode("Component -" + "launchBrowser");
		extent.flush();
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\main\\java\\resources\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[contains(text(),'Search')]")).click();
		
	}
	

	
	
}
