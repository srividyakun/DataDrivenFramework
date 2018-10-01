package de.check24.PageObjectDataDriven.selenium.frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import de.check24.PageObjectDataDriven.helper.logger.LoggerHelper;
import de.check24.PageObjectDataDriven.helper.wait.WaitHelper;

public class FrameHelper {

	private Logger log = LoggerHelper.getLogger(FrameHelper.class);
	private WebDriver driver;
	
	/**
	 * This is a constructor
	 * @param driver
	 */
	
	public FrameHelper(WebDriver driver) {
		this.driver=driver;
	}
	
	/**
	 * This method will switch to frame based on frame index
	 * @param index
	 */
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
		log.info("switched to " + index + "frame");
		
	}
	
	/**
	 *  This method will switch to frame based on frameName
	 * @param frameName
	 */
	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
		log.info("switched to " + frameName + "frame");
		
	}
	
	/**
	 * This method will switch to frame based on frame WebElement
	 * @param element
	 */
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
		log.info("switched to " + element.toString());
		
	}
}

