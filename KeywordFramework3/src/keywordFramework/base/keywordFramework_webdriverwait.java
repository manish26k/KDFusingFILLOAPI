package keywordFramework.base;

import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;

import keywordFramework.base.keywordFramework_TestBase;

public class keywordFramework_webdriverwait extends keywordFramework_TestBase
{
	static ResourceBundle rb;
	WebDriver driver;
	
	public keywordFramework_webdriverwait()
	{
	  rb=ResourceBundle.getBundle("Config");
	}
	
	public int getPageLoadTimeOut()
	{
		return Integer.parseInt(rb.getString("PageLoadTimeOut"));
	}

	public int getImplicitWait()
	{
		return Integer.parseInt(rb.getString("ImplcitWait"));
	}

	public int getExplicitWait() 
	{
		return Integer.parseInt(rb.getString("ExplicitWait"));
	}
	
	
	
	
	
	
	
	
}
