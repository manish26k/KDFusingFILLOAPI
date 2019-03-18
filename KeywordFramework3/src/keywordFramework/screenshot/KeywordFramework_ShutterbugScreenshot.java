package keywordFramework.screenshot;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

public class KeywordFramework_ShutterbugScreenshot {
	public static WebDriver driver;

	public static void ShutterbugScreenshot(String result){

		try
		{
			// Using shutter bug
			Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("./Screenshots/" + result + timestamp() + ".png");
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

}
