package de.check24.PageObjectDataDriven.testBase;

import java.io.File;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import de.check24.PageObjectDataDriven.helper.browserConfiuration.BrowserConfigurationHelper;
import de.check24.PageObjectDataDriven.helper.browserConfiuration.ObjectReader;
import de.check24.PageObjectDataDriven.helper.browserConfiuration.PropertyReader;
import de.check24.PageObjectDataDriven.helper.excel.ExcelHelper;
import de.check24.PageObjectDataDriven.helper.javaScript.JavaScriptHelper;
import de.check24.PageObjectDataDriven.helper.wait.WaitHelper;

public class TestBase {
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public static WebDriver driver;
	private Logger log = Logger.getLogger(TestBase.class);
	public static File reportDirectery;

	@BeforeSuite
	public void beforeSuite() throws Exception{
		//extent = ExtentManager.getInstance();
	}
	
	@BeforeTest
	public void beforeTest() throws Exception{
		ObjectReader.reader = new PropertyReader();
		reportDirectery = new File(System.getProperty("user.dir")+"//src//main//resources//screenshots");
		setupBrowser();
		//test = extent.createTest(getClass().getSimpleName());
	}
	
	
	@BeforeMethod
	public void beforeMethod(Method method){
		//test.log(Status.INFO, method.getName()+"**************test started***************");
		log.info("**************"+method.getName()+"Started***************");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException{
		if(result.getStatus() == ITestResult.FAILURE){
			//test.log(Status.FAIL, result.getThrowable());
			String imagePath = captureScreen(result.getName(),driver);
			//test.addScreenCaptureFromPath(imagePath);
			
		}
		else if(result.getStatus() == ITestResult.SUCCESS){
			//test.log(Status.PASS, result.getName()+" is pass");
			String imagePath = captureScreen(result.getName(),driver);
			//test.addScreenCaptureFromPath(imagePath);
		}
		else if(result.getStatus() == ITestResult.SKIP){
			test.log(Status.SKIP, result.getThrowable());
		}
		
		
		log.info("**************"+result.getName()+"Finished***************");


	}
	
	@AfterTest
	public void afterTest() throws Exception{
		if(driver!=null){
			driver.quit();
		}
	}
	public void setupBrowser() {
		try {
			
			BrowserConfigurationHelper browser = BrowserConfigurationHelper.class.newInstance();
			driver = browser.InitializeWebdriver();
			log.info("Initialized WebDriver" +driver.hashCode());
			WaitHelper wait = new WaitHelper(driver);
			wait.setImplicitWait(ObjectReader.reader.getImpliciteWait(),TimeUnit.SECONDS);
			wait.pageLoadTime(ObjectReader.reader.getExplicitWait(), TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
		
	}
	
	public String captureScreen(String fileName,WebDriver driver){
		if(driver == null){
			log.info("driver is null..");
			return null;
		}
		if(fileName==""){
			log.info("fileName is blank..");
			fileName = "blank";
		}
		Reporter.log("captureScreen method called");
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			destFile = new File(reportDirectery+"/"+fileName+"_"+formater.format(calendar.getTime())+".png");
			Files.copy(screFile.toPath(), destFile.toPath());
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100' width='100'/></a>");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return destFile.toString();
	}
	
	public void getNavigationScreen(WebDriver driver) {
		log.info("capturing ui navigation screen...");
		new JavaScriptHelper(driver).zoomInBy60Percentage();
		 String screen = captureScreen("",driver);
		 new JavaScriptHelper(driver).zoomInBy100Percentage();
//		 try {
//			test.addScreenCaptureFromPath(screen);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
/*	public static void logExtentReport(String s1){
		test.log(Status.INFO, s1);
	}*/
	
	public void getApplicationUrl(String url){
		driver.get(url);
		//logExtentReport("navigating to ..."+url);
	}
	
	public Object[][] getExcelData(String excelName, String sheetName){
		
		ExcelHelper excelHelper = new ExcelHelper();
		 String excelLocation = System.getProperty("user.dir")+"\\src\\main\\resources\\configFiles\\"+excelName;
		 log.info("excel location "+excelLocation);
		 Object[][] data = excelHelper.getExcelData(excelLocation, sheetName);
		 System.out.println(data);
		return data;
	}

	
}
