package de.check24.PageObjectDataDriven.helper.browserConfiuration;


import java.io.IOException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserConfigurationHelper {

		
		private WebDriver driver;
		private Logger log = Logger.getLogger(BrowserConfigurationHelper.class);
		
		public WebDriver InitializeWebdriver() throws IOException {
		
		// FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//configFiles//config.properties");	
		// Properties prop = new Properties();	
		 //String browsername = prop.getProperty("browser");
		 String browsername = ObjectReader.reader.browserType();	
	     if(browsername.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//main//resources//drivers//chromedriver.exe");
				driver = new ChromeDriver();
				
			}else if (browsername.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//src//main//resources//drivers//geckodriver.exe");
				driver=new FirefoxDriver();
				
			}else if(browsername.equalsIgnoreCase("InternetExplorer")) {
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//src//main//resources//drivers//IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
	     
	    	return driver;
	}
}
