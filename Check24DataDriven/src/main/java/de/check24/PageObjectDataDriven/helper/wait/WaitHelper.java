package de.check24.PageObjectDataDriven.helper.wait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.check24.PageObjectDataDriven.helper.logger.LoggerHelper;

public class WaitHelper {
	
	
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);
	private WebDriver driver;
	
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
	}
	
	

	/**
	 * This is implicitWait method
	 * @param timeout
	 * @param unit
	 */
	
	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info("Implicit wait has been set to " +timeout);
	 driver.manage().timeouts().implicitlyWait(timeout, unit);
	
	}
	
	/**
	 * This will help us to get webdriver wait object
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	
	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		WebDriverWait wait  = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}
	/**
	 * This method make sure element is visible now.
	 * @param element
	 * @param TimeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	
	public void waitForElementVisible(WebElement element,int TimeOutInSeconds, int pollingEveryInMiliSec) {
		log.info("waiting for :" +element +toString() + "for:" + TimeOutInSeconds +"seconds" );
		WebDriverWait wait = getWait(TimeOutInSeconds,pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
		
	}
	
	/**
	 * This method make sure element is clickable 
	 * @param element
	 * @param timeOutInSeconds
	 */
	
	public void waitForElementClickable(WebElement element,int timeOutInSeconds) {
		log.info("waiting for :" +element +toString() + "for:" + timeOutInSeconds +"seconds" );
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is clickable now");
		
	}
	
	/**
	 * This method make sure element is invisible
	 * @param element
	 * @param timeOutInSeconds
	 */
	
	public void waitForElementNotPresent(WebElement element,int timeOutInSeconds) {
		log.info("waiting for :" +element +toString() + "for:" + timeOutInSeconds +"seconds" );
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("element is invisible now");
		
	}
	
	/**
	 * This Method will make sure frame is available and switch to it.
	 * @param element
	 * @param timeOutInSeconds
	 */
	
	public void waitForElementNotPresent1(WebElement element,int timeOutInSeconds) {
		log.info("waiting for :" +element +toString() + "for:" + timeOutInSeconds +"seconds" );
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("frame is available and switched now");
		
	}
	/**
	 * this method will help to get fulentwait object
	 * @param timeOutInSeconds
	 * @return
	 */
	
	private Wait<WebDriver> getFluentWait(int timeOutInSeconds) {
	
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.ignoring(NoSuchElementException.class);
		log.info("returning fwait");
		return fwait;
		
	}
	
	/**
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	
	public WebElement waitForElement(WebElement element,int timeOutInSeconds) {
		log.info("waiting for :" +element +toString() + "for:" + timeOutInSeconds +"seconds" );
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now by using by WebDriverWait");
		return element;
	}
	
	public void pageLoadTime(long timeout , TimeUnit unit) {
		log.info("waiting for page to load for :" + unit + "seconds" );
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
		log.info("page is loaded");
	}
}


