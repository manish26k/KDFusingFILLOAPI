package keywordFramework.excelReader;

import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import keywordFramework.helper.KeywordFramework_Wait;



public class KeywordFramework_Excel
{
	public static String[] getLocatorExcel(String key)
	{
		Fillo fillo=new Fillo();
		String locator=null;
		String testvalue=null;
		String[] abc=new String[2];
		String timeValue=null;
		try {
	    //Connection connection=fillo.getConnection("C:\\Users\\gupta.manish\\Desktop\\TestSuite.xlsx");
	    
	    Connection connection=fillo.getConnection("./KeywordFrameworkTestData/TestSuite.xlsx");
    
	    String strQuery="Select * from TC01 where Keyword='"+key+"'";
	    //System.out.println(strQuery);
	    
	    Recordset recordset=connection.executeQuery(strQuery);
	   
	    while(recordset.next())
	    {
	    	locator=recordset.getField("WebElement");
	    	abc[0]=locator;
	    	if(key.equals("waitForLogin"))
	    	{
	    		timeValue=recordset.getField("TestDatavalue");
	    		int time= Integer.parseInt(timeValue);
	    		abc[1]=timeValue;
	    		System.out.println(time);
	    		//locator=recordset.getField("WebElement");
	    	}
	    	testvalue=recordset.getField("TestDatavalue");
	    	abc[1]=testvalue;
	    	System.out.println(locator+ " " + testvalue);
	    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return abc;
	}
	
	
	
	
	
	
	
	
	
	
	
	/*public static void main(String[] args)
	{
		getLocatorExcel("waitForLogin");
	}*/
	
	
}
