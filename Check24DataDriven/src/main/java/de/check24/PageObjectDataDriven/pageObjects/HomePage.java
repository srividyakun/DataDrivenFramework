package de.check24.PageObjectDataDriven.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import de.check24.PageObjectDataDriven.helper.browserConfiuration.ObjectReader;
import de.check24.PageObjectDataDriven.helper.logger.LoggerHelper;
import de.check24.PageObjectDataDriven.helper.wait.WaitHelper;
import de.check24.PageObjectDataDriven.testBase.TestBase;

public class HomePage {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(LandingPage.class);
	WaitHelper waitHelper;
	

	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(anmeldebestätigen,ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
		//TestBase.logExtentReport("LandingPage Object Created");
	}
	
	@FindBy(xpath="//*[@id='c24-kb-container']/div/div[1]/div/div/div/p[contains(text(),'Sie sind angemeldet')]")
	WebElement anmeldebestätigen;
	@FindBy(xpath="//*[@id='c24-meinkonto']/div/div[2]/a")
	WebElement abmelden;
	@FindBy(xpath="//*[@id='c24-header-bottom']/div/nav/div/div[2]/ul/li[2]/a")
	WebElement kontokreditlink;
	@FindBy(xpath="//*[@id='c24-fin']/li[2]/ul/li[2]/a")
	WebElement festgeldlink;
	@FindBy(xpath="//*[@id='tm-invAmount']")
	WebElement geplanteranlagebtrag;
	@FindBy(xpath="//*[@id='tm-invDuration']")
	WebElement geplanteranladauer;
	@FindBy(xpath="//*[@id='form']/div/div[3]/input")
	WebElement festgeldvergleich;
    

}
