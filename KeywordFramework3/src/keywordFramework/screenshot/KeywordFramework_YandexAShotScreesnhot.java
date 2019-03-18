package keywordFramework.screenshot;

import java.io.File;




import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class KeywordFramework_YandexAShotScreesnhot
{
   public static WebDriver driver;
   
   public static void YandexAShotScreesnhot(String result) throws IOException
   {
	      File getScreenShotAshot = new File("./Screenshots/" + result + timestamp()+ ".png");;
		 //Take Screenshot of entire page by AShot
		 Screenshot entirePageScreenShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
		 //Write Screenshot to a file
		 ImageIO.write(entirePageScreenShot.getImage(),"PNG", getScreenShotAshot); 
   }
   
   public static String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
   
	

}
