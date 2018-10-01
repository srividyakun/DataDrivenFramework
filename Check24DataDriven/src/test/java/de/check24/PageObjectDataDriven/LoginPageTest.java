package de.check24.PageObjectDataDriven;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver.Navigation;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import de.check24.PageObjectDataDriven.helper.assertions.AssertionHelper;
import de.check24.PageObjectDataDriven.helper.browserConfiuration.ObjectReader;
import de.check24.PageObjectDataDriven.helper.logger.LoggerHelper;
import de.check24.PageObjectDataDriven.pageObjects.LandingPage;
import de.check24.PageObjectDataDriven.pageObjects.LoginPage;
import de.check24.PageObjectDataDriven.testBase.TestBase;

public class LoginPageTest extends TestBase{
	
	
			private final Logger log = LoggerHelper.getLogger(LoginPageTest.class);
			
			LoginPage login;
			LandingPage landing;
			//Navigation navigationMenu;
			
			
			@DataProvider(name="testData")
			public Object[][] testData(){
				Object[][] data = getExcelData("testData.xlsx", "LoginData");
				return data;
			}
			
			@BeforeClass
			public void beforeClass() {
				getApplicationUrl(ObjectReader.reader.getUrl());
				landing = new LandingPage(driver);
				landing.navigateToAnmelden();
			}
			
			@Test(dataProvider="testData")
			public void loginTest(String testDataText, String userName, String password, String runMode){
				
							
				if(runMode.equalsIgnoreCase("n")){
					throw new SkipException("Run mode for this set of data is marked N");
				}
				login = new LoginPage(driver);
				login.loginToApplication(userName, password);
				boolean status = login.verifySuccessLoginMsg();
				if(status==true) {
					AssertionHelper.updateTestStatusWithMessage(status, "User is able to Login successfully");
					login.clickOnSignoutLink();
					boolean status1 = login.verifySuccessLogout();
					AssertionHelper.updateTestStatus(status1);
				}else {
					AssertionHelper.updateTestStatusWithMessage(status, "User is not able to Login successfully");
				}
					
							
				
			
				
			}
}
