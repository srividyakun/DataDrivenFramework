package de.check24.PageObjectDataDriven.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import de.check24.PageObjectDataDriven.helper.assertions.AssertionHelper;
import de.check24.PageObjectDataDriven.helper.assertions.VerificationHelper;
import de.check24.PageObjectDataDriven.helper.browserConfiuration.ObjectReader;
import de.check24.PageObjectDataDriven.helper.logger.LoggerHelper;
import de.check24.PageObjectDataDriven.helper.wait.WaitHelper;
import de.check24.PageObjectDataDriven.testBase.TestBase;

public class LoginPage {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(LandingPage.class);
	WaitHelper waitHelper;
	

	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(siesindhieranmelden,ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
		//TestBase.logExtentReport("LoginPage Object Created");
	}
	
	@FindBy(xpath = "//*[@id='c24-meinkonto']/span/span[2]")
	WebElement meinKonto;
	@FindBy(xpath="//*[@id='email']")
	WebElement meineemailadresse;
	@FindBy(xpath="//*[@id='password']")
	WebElement meincheck24password;
	@FindBy(xpath="//*[@id='pw_reset_btn']")
	WebElement passwordvergessen;
	@FindBy(xpath="//*[@id='new_customer']")
	WebElement startensiehier;
	@FindBy(xpath="//*[@id='c24-kb-register-btn']")
	WebElement anmeldentaste;
	@FindBy(xpath="//*[@id='oauth-container']/div/div[3]/div/div[1]/button")
	WebElement facebookanmelden;
	@FindBy(xpath="//*[@id='oauth-container']/div/div[3]/div/div[2]/button")
	WebElement googleanmelden;
	@FindBy(xpath="//*[@id='c24-breadcrumb']/div/ul/li/a")
	WebElement siesindhieranmelden;
	@FindBy(xpath="//*[@id='c24-meinkonto']/div/div[2]/a")
	WebElement abmelden;
	@FindBy(xpath="//*[@id='c24-kb-container']/div/div[1]/div/div/div/p[contains(text(),'Sie sind angemeldet')]")
	WebElement anmeldebestätigen;
	@FindBy(xpath = "//*[@id='c24-customer-salutation']/a")
	WebElement anmeldenlink;
	
	public void enterEmail(String emailaddress){
		log.info("Entering email address...");
		meineemailadresse.clear();
		meineemailadresse.sendKeys(emailaddress);
	}
	
	public void enterPassword(String password){
		log.info("Entering password...");
		meincheck24password.clear();
		meincheck24password.sendKeys(password);
	}
	
	public HomePage loginButton(){
		log.info("Clicking on login...");
		anmeldentaste.click();
		return new HomePage(driver);
	}
	
	public boolean verifySuccessLoginMsg(){
		log.info("Verifying successfully logged in or not ");
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(anmeldebestätigen,ObjectReader.reader.getExplicitWait());
		return new VerificationHelper(driver).isDisplayed(anmeldebestätigen);
	}
	
	
	public void loginToApplication(String emailAddress, String password) {
		
		boolean verifyloginpage = new VerificationHelper(driver).isDisplayed(siesindhieranmelden);
		// TODO Auto-generated method stub
		  		  
		if(verifyloginpage==true) {
			   enterEmail(emailAddress);
			   enterPassword(password);
			   loginButton(); 
		}else {
			AssertionHelper.updateTestStatusWithMessage(verifyloginpage, "Login page didnot launch successfully");
		}
		   
		  
	}
	public void clickOnMeinKonto(){
		log.info("clicking on My Account link...");
		meinKonto.click();
	}
	
	public void signoutLink(){
		log.info("clicking on My Account link...");
		
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(abmelden,ObjectReader.reader.getExplicitWait());
		abmelden.click();
	}

		
	  public void clickOnSignoutLink(){
		    log.info("clicking on SignoutLink...");
			clickOnMeinKonto();
			signoutLink();
		    }
	  
	  public boolean verifySuccessLogout() {
		  log.info("validating logout...");
		  waitHelper = new WaitHelper(driver);
		  waitHelper.waitForElement(anmeldenlink,ObjectReader.reader.getExplicitWait());
		  return new VerificationHelper(driver).isDisplayed(anmeldenlink);
		}

	
	
}
