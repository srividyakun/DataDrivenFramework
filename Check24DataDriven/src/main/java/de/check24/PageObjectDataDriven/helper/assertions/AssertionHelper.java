package de.check24.PageObjectDataDriven.helper.assertions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import de.check24.PageObjectDataDriven.helper.logger.LoggerHelper;
import junit.framework.Assert;

public class AssertionHelper {
	
	private WebDriver driver;
	private static Logger log= LoggerHelper.getLogger(AssertionHelper.class);
	
	public AssertionHelper(WebDriver driver) {
		this.driver=driver;
	}

	public static void verifyText(String s1,String s2) {
		log.info("Verifying test" +s1 + " with "+s2);
		Assert.assertEquals(s1, s2);
	}
	
	public static void makeTrue() {
		log.info("Making script PASS..");
		Assert.assertTrue(true);
	}
	public static void makeTrue(String message) {
		log.info("Making script PASS..");
		Assert.assertTrue(message, true);
	}
	public static void makeFalse() {
		log.info("Making script FAIL.." );
		Assert.assertTrue(false);
	}
	public static void makeFalse(String message) {
		log.info("Making script FAIL.." +message);
		Assert.assertTrue(message, false);
	}
	public static void verifyNull(String s1) {
		log.info("Verify Object is Null.." );
		Assert.assertNull(s1);
	}
	public static void verifyNotNull(String s1) {
		log.info("Verify Object is not Null..");
		Assert.assertNotNull(s1);
	}
	public static void fail(){
		Assert.assertTrue(false);
		
		log.info("validation with Invalid data.");
	}
	
	public static void pass(){
		
		Assert.assertTrue(true);
	
	}
	
	public static void updateTestStatusWithMessage(boolean status, String message){
		if(status){
			makeTrue(message);
		}
		else{
			makeFalse(message);
		}
	}
	
	
	public static void updateTestStatus(boolean status){
		if(status){
			pass();
		}
		else{
			fail();
		}
	}
}