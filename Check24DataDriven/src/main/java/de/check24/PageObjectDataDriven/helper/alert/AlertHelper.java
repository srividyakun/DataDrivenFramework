package de.check24.PageObjectDataDriven.helper.alert;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import de.check24.PageObjectDataDriven.helper.logger.LoggerHelper;

public class AlertHelper {


	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(AlertHelper.class);
	
    public AlertHelper(WebDriver driver){
		// TODO Auto-generated constructor stub
		this.driver=driver;
		log.info("AlertHelper object is created");
	}
    
    public Alert getAlert() {
    	log.info("alert test:" +driver.switchTo().alert().getText());
		return driver.switchTo().alert();
    }
    public void acceptAlert() {
    	log.info("Accept alert is done...");
    	getAlert().accept();
    }
    public void dismissAlert() {
    	log.info("Dismiss alert is done...");
    	getAlert().dismiss();
    }
    public String getAlertText() {
    	String text = getAlert().getText();
    	log.info("Accept text" +text);
    	return text;
    }
    public boolean isAlertPresent() {
    	try {
    		driver.switchTo().alert();
    		log.info("alert is present");
    		return true;
    	}catch(NoAlertPresentException e){
    		log.info(e.getCause());
    		return false;
    	}
    }
    
    public void acceptAlertIfPresent() {
    	if(isAlertPresent()) {
    		acceptAlert();
    	}else {
    		log.info("Alert is not present");
    	}
    }
    public void dismissAlertIfPresent() {
    	if(isAlertPresent()) {
    		dismissAlert();
    	}else {
    		log.info("Alert is not present");
    	}
    }
    public void acceptPrompt(String text) {
    	if(isAlertPresent()) {
    		Alert alert = getAlert();
    		alert.sendKeys(text);
    		alert.accept();
    		log.info("alert text "+text);
    		
    	}
    }
}

