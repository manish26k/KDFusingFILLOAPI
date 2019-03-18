package keywordFramework.Testscases;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.relevantcodes.extentreports.LogStatus;

import keywordFramework.base.keywordFramework_TestBase;
import keywordFramework.excelReader.KeywordFramework_Excel;

public class KeywordFrameworkLOGINTest extends keywordFramework_TestBase
{
	
	KeywordFramework_Excel login;
	
	@Test
	 public void testLoginToAppViaKeyword() {
		 String URL= login.getLocatorExcel("navigate")[0];
		 driver.get(URL);
		 
		 
		 
		 String clickSignOn=login.getLocatorExcel("ClickSignONLink")[0];
		 driver.findElement(By.cssSelector(clickSignOn)).click();
		 
		
		 String data[]=login.getLocatorExcel("EmailInputText");
		 System.out.println(data[0]+"--->"+data[1]);
		 driver.findElement(By.cssSelector(data[0])).sendKeys(data[1]);
	 }
	 
	
	
	
}
