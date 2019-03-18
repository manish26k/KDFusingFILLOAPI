package keywordFramework.screenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class KeywordFramework_Screenshot
{
   public static WebDriver driver;
   
	public static String takeSnapShot(String result) throws IOException
	{
		File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		String actualImagename= "./Screenshots/" + result + timestamp()+ ".png";

		File destfile = new File(actualImagename);

		FileUtils.copyFile(scrfile, destfile);
		
		return actualImagename;

	}

	public static String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

}
