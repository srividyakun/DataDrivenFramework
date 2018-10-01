package de.check24.PageObjectDataDriven.selenium.actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import de.check24.PageObjectDataDriven.helper.logger.LoggerHelper;

public class ActionHelper {

	private Logger log = LoggerHelper.getLogger(ActionHelper.class);
	private WebDriver driver;
	
	/**
	 * This is a constructor
	 * @param driver
	 */
	
	public ActionHelper(WebDriver driver){
		
		this.driver=driver;
	}
	
	/**
	 * This method will switch to frame based on frame index
	 * @param index
	 */
	public void mouseOver(WebElement element) {
				Actions actions = new Actions(driver);
				actions.moveToElement(element).build().perform();
	}
}
