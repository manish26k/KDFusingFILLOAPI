package keywordFramework.base;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import keywordFramework.excelReader.KeywordFramework_Excel;
import keywordFramework.helper.KeywordFramework_Wait;
import keywordFramework.screenshot.KeywordFramework_Screenshot;

import com.codoid.products.exception.FilloException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class keywordFramework_TestBase

{
	public WebDriver driver;
	
	public static  ResourceBundle rb;
	public static ResourceBundle rbelement;
	
	public ExtentReports extentReport;
	public ExtentTest extentTest;
	
	public  KeywordFramework_Excel excel;
	
	public keywordFramework_webdriverwait webDriverWait;
	
	public KeywordFramework_Wait wait;
	
	public static Logger log=LogManager.getLogger(keywordFramework_TestBase.class.getName());
	
	
	@BeforeTest
	public void launchBrowser()
	{
		extentReport= new ExtentReports("./Report/report.html", true);
		extentReport.addSystemInfo("Name", "Hybrid Framework");
		
		
		rb = ResourceBundle.getBundle("Config");
		log.info("Config properties files gets loaded");
		
		webDriverWait= new keywordFramework_webdriverwait();
		log.info("Implicit/Explicit/PageLoadtimeOut wait gets intialiazed");
		
		getBrowser(rb.getString("Browser"));
		log.info(" browser gets intialiazed");
		
		//driver.get(rb.getString("URL"));
		log.info("URL get loaded");
		driver.manage().window().maximize();
		
		wait= new KeywordFramework_Wait(driver);
		
		wait.setImplicitWait(webDriverWait.getImplicitWait(),TimeUnit.SECONDS);
		log.info("setImplicitWait");
		
		wait.setPageLoadTimeout(webDriverWait.getPageLoadTimeOut(),TimeUnit.SECONDS);
		log.info("setPageLoadTimeout");

		
	}
	

	public void getBrowser(String Browser)
	{
		
		extentReport= new ExtentReports("./Report/report.html", true);
		extentReport.addSystemInfo("Name", "HybridFramework");
		
		
		rb = ResourceBundle.getBundle("Config");
		if (System.getProperty("os.name").contains("Window")) {
			System.out.println(System.getProperty("os.name"));
			if (rb.getString("Browser").equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				this.driver = new ChromeDriver();
			} else if (rb.getString("Browser").equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				this.driver = new FirefoxDriver();
			}

			else {
				WebDriverManager.chromedriver().setup();
				this.driver = new ChromeDriver();
			}

			/*driver.get(rb.getString("URL"));
			driver.manage().window().maximize();*/
		}

	}

	
	public void getresult(ITestResult result) throws IOException
	{
		if (result.getStatus() == ITestResult.SUCCESS)
		{
		  extentTest.log(LogStatus.PASS, result.getName() + " test is pass");
		}
		else if (result.getStatus() == ITestResult.SKIP)
		{
		  extentTest.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
		}
		else if (result.getStatus() == ITestResult.FAILURE)
		{
		  extentTest.log(LogStatus.FAIL, result.getName() + " test is failed" + result.getThrowable());
		  String screen =  KeywordFramework_Screenshot.takeSnapShot("");
		  extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screen));
		}
		else if (result.getStatus() == ITestResult.STARTED)
		{
		  extentTest.log(LogStatus.INFO, result.getName() + " test is started");
		}
	}
	
	
	//@AfterMethod()
	public void afterMethod(ITestResult result) throws IOException
	{
		getresult(result);
	}
	
	//@BeforeMethod()
	public void beforeMethod(Method result)
	{
		extentTest = extentReport.startTest(result.getName());
		extentTest.log(LogStatus.INFO, result.getName() + " test Started");
	}
	
	
		
	
	public WebElement getLocator(String locator) throws Exception {
		
		rbelement = ResourceBundle.getBundle("Element");
		
		System.out.println(locator);
        String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];
		
		System.out.println("locatorType:-"+locatorType);
		System.out.println("locatorValue:-"+locatorValue);
		
		
		
		if (locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname"))|| (locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname"))|| (locatorType.toLowerCase().equals("tag")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext"))|| (locatorType.toLowerCase().equals("link")))
			return driver.findElement(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector"))|| (locatorType.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}
	
public List<WebElement> getLocators(String locator) throws Exception {
		
		
		
		//System.out.println(locator);
        String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];
		
		System.out.println("locatorType:-"+locatorType);
		System.out.println("locatorValue:-"+locatorValue);
		
		if (locatorType.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname"))|| (locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname"))|| (locatorType.toLowerCase().equals("tag")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext"))|| (locatorType.toLowerCase().equals("link")))
			return driver.findElements(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector"))|| (locatorType.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}


      public WebElement getWebElement(String locator) throws Exception
      {
    	  rbelement=ResourceBundle.getBundle("Element");  
    	  return getLocator(rbelement.getString(locator));
    	  
      }

      public List<WebElement> getWebElements(String locator) throws Exception
      {
    	  
    	  return getLocators(rbelement.getString(locator));
      }
	
      
      public WebElement waitForElement(WebDriver driver,long time,WebElement element){
  		WebDriverWait wait = new WebDriverWait(driver, time);
  		return wait.until(ExpectedConditions.elementToBeClickable(element));
  	}
  	
  	public WebElement waitForElementWithPollingInterval(WebDriver driver,long time,WebElement element){
  		WebDriverWait wait = new WebDriverWait(driver, time);
  		wait.pollingEvery(5, TimeUnit.SECONDS);
  		wait.ignoring(NoSuchElementException.class);
  		return wait.until(ExpectedConditions.elementToBeClickable(element));
  	}
  	
  	public void impliciteWait(long time){
  		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
  	}
	  
      
     
      
     /*public String[][] getData(String excelName, String sheetName) throws FilloException
	  {
    	  String excellocation = "./HybridFrameworkTestData/"+ excelName;
    	 excel= new HybridFramework_Excel();
    	 return excel.getExcelData(excellocation,sheetName);
	  }*/
      
	
	
	
	
	
	
	
	/*public static void main(String[] args) throws Exception
	{
		HybridFramework_TestBase HBB= new HybridFramework_TestBase();
		//HBB.getBrowser();
		
		rbelement = ResourceBundle.getBundle("Element");
    	System.out.println(HBB.getWebElement("SignIncss"));
		System.out.println(HBB.getWebElements("Password"));
	
	}*/
	
	
	@AfterTest
	public void quitBrowser()
	{
		extentReport.endTest(extentTest);
		extentReport.flush();
		driver.quit();
	}
	
	
	
	
	

}
