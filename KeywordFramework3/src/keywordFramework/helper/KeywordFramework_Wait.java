package keywordFramework.helper;

import java.time.Duration;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;

public class KeywordFramework_Wait {

	WebDriver driver;
	 //ResourceBundle rb;

	public KeywordFramework_Wait(WebDriver driver) {
		this.driver = driver;
		//rb = ResourceBundle.getBundle("Config");
	}

	/**
	 * This is ImplicitWait method
	 * 
	 * @param timeout
	 * @param unit
	 */
	public void setImplicitWait(long timeout, TimeUnit unit) {
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}
	
	/**
	 * This is PageLoadTimeOut method
	 * 
	 * @param timeout
	 * @param unit
	 */
	public void setPageLoadTimeout(long timeout, TimeUnit unit) {
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
	}
	
	
	
	
	

	/**
	 * This will help us to get WebDriverWait object
	 * 
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */

	private WebDriverWait getWait(int timeOutInSeconds,
			int pollingEveryInMiliSec) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	/**
	 * This method will make sure element is visible
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public void WaitForElementVisibleWithPollingTime(WebElement element,
			int timeOutInSeconds, int pollingEveryInMiliSec) {

		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	/**
	 * This method will make sure elementToBeClickable
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void WaitForElementClickable(WebElement element, int timeOutInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	/**
	 * This method will make sure invisibilityOf element
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean waitForElementNotPresent(WebElement element,
			long timeOutInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));

		return status;
	}

	/**
	 * This method will wait for frameToBeAvailableAndSwitchToIt
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForframeToBeAvailableAndSwitchToIt(WebElement element,
			long timeOutInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));

	}

	/**
	 * This method will give is fluentWait object
	 * 
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	private Wait<WebDriver> getfluentWait(int timeOutInSeconds,
			int pollingEveryInMiliSec) {
		Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
				.withTimeout(pollingEveryInMiliSec, TimeUnit.MILLISECONDS)
				.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		return fWait;
	}

	/**
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public WebElement waitForElement(WebElement element, int timeOutInSeconds,
			int pollingEveryInMiliSec) {
		Wait<WebDriver> fwait = getfluentWait(timeOutInSeconds,
				pollingEveryInMiliSec);
		fwait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public void pageLoadTime(long timeout, TimeUnit unit) {

		driver.manage().timeouts().pageLoadTimeout(timeout, unit);

	}

	/**
	 * This method will make sure elementToBeClickable
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElement(WebElement element, int timeOutInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));

	}
	
	public void waitForElement(WebDriver driver, WebElement element, long timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}

	/*public void waitForElement(WebDriver driver,String css, long timeout){
        WebElement element= driver.findElement(By.cssSelector(css));
        
        //long time=Long.parseLong(explicitWait);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }*/


}
