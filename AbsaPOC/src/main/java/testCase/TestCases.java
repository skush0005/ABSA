package testCase;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import components.Components;
import resourceBase.WebBase;


public class TestCases extends Components {

	@BeforeTest
	public static void setupEnv() {
//		/WebBase.initialiseConfig();
		WebBase.setup();		
	}
	
	@Test
	public void LogIn_TC() { 
		parentTest = extent.createTest("Test Case  - " + "LogIn_TC"); // For Extent Report
		Components.launchBrowser("https://www.absa.co.za/personal/");	    
	}

	
	
	@Test
	public void LogIn_TC2() {
		//Components.launchBrowser2("https://www.absa.co.za/personal/");	    
	}
	
	
}
