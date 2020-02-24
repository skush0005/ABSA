package resourceBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class WebBase {
	
	public static int WAITTIMEOUT = 5;	
	public static ExtentTest parentTest ;
	public static ExtentTest childTest;		
	public final static String ArtefactsFolder = System.getProperty("user.dir")+"\\ExtentReport";
	public static Properties properties; 
	public static WebDriver driver;
	public static String Reportingdirectorynew = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date()); // To save results
	public static String Reportingdirectory= Reportingdirectorynew; 
	public static ExtentHtmlReporter htmlReports;
	public static ExtentReports extent;
	public static String filename=System.getProperty("user.dir")+"\\artefacts\\";
	
	

	
	public static void reporting_Failure(String reportType, String text) {

		switch (reportType) {

		case "ObjectNotFound":
		   childTest.log(Status.FAIL, MarkupHelper.createLabel("Object Not Found" + text, ExtentColor.RED));
		break;
		case "validateText":
		   System.out.println("Reporting Failure for validateText");
		   break;
		case "Click":
		   System.out.println("Click failed");
		   break;
		case "input":
		   System.out.println("input failed");
		break;
		case "validateTextvalue":
		   System.out.println("Validation Failed");
		break;
		   case "switchtoFrame":
		   System.out.println("Frame not found");
		break;
		   case "isRadioButtonSelected":
		   System.out.println("Radio Button is not selected");
		break;
		   case "selectFromDropdown":
		   System.out.println("Radio Button is not selected");
		break;
		   case "validateValueFromDropDown":
		   childTest.log(Status.FAIL,MarkupHelper.createLabel("Expected value ==  " + text + "  does not match from any of actual values in drop down.",ExtentColor.GREEN));
		   break;
		case "validateTitle":
		   System.out.println("ValidateTitle Failed");
		break;

		}
		//After executing any of above case
		//1.Take screenshot
		String temp = WebBase.CaptureScreenshot("test");
		try {
		    childTest.addScreenCaptureFromPath(temp);
		} catch (IOException e1) {
		// TODO Auto-generated catch block
		    e1.printStackTrace();
		}
		// 2.Setting up component status as Fail so that can abort further steps from execution
          properties.setProperty("ComponentsStatus", "Fail"); 		
		extent.flush();
		}

	
	public static String CaptureScreenshot(String screenshotName) {
		try {
		String dateName = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "//ExtentReport//" +Reportingdirectory+ screenshotName + "_" + dateName+ ".png";
		FileUtils.copyFile(srcFile, new File(dest));
		return dest;
		} catch (Throwable t) {
		t.getMessage();
		}
		return "Pass";
		}


	public static String WaitForElement(By by,String objectName) throws Exception
   {
       System.out.println(objectName+" entered WaitForElement");
       boolean elementFound = false;
       int counter = 0;
       try
       {
           while (!elementFound && counter < WAITTIMEOUT)
           {
               try
               {
     
                   WebDriverWait wait = new WebDriverWait(getDriver(), 1);
                   wait.until(ExpectedConditions.presenceOfElementLocated(by));
                   if (wait.until(ExpectedConditions.presenceOfElementLocated(by)) != null)
                   {
                       elementFound = true;
                       System.out.println(" Found element by Xpath : " + by.toString());
                       return "Pass";
                   }
               } catch (Exception e)
               {

                   elementFound = false;
                   counter++;
                   System.out.println(counter);
                 
               }
               pause(1000);                
           }
           reporting_Failure("ObjectNotFound",objectName ); //Replace
                System.out.println("Element not found");
                 
           //return elementFound;
       } catch (Exception ex)
       {
           System.out.println("[ERROR] -in wait for Element " + ex.getMessage());                        
       }
    return "Fail";  
  }


	 public static WebDriver getDriver()
	    {
	        return driver;
	    }


	
	
	public static void pause(int millis)
{
    try
    {
    	Thread.sleep(millis);
    } catch (InterruptedException e)
    {
        e.printStackTrace();
    }
}
	 
	/*
	 * Method Name :: setup
	 * Description :: TO initialise the Extent report and update the system info.  
	 */
	
	public static void setup() {
		System.out.println(WebBase.Reportingdirectory);
		System.out.println(filename);
		System.out.println();
		htmlReports = new ExtentHtmlReporter(filename+WebBase.Reportingdirectory+"//Report.html");
		htmlReports.loadXMLConfig(System.getProperty("user.dir")+"//src//main//java//Config//ReportsConfig.xml");
		htmlReports.config().setAutoCreateRelativePathMedia(true);
		extent = new ExtentReports();
		extent.attachReporter(htmlReports);
		extent.setSystemInfo("User Name", "Automation User");
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Machine", "XYZ");
	}


}
