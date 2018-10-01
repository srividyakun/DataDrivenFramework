package de.check24.PageObjectDataDriven.helper.browserConfiuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader implements ConfigReader{
	
	private static FileInputStream file;
	public static Properties prop;
	
	static {
		String filepath = System.getProperty("user.dir")+"//src//main//resources//configFiles//config.properties";
		try {
			file= new FileInputStream(filepath);
			prop=new Properties();
			try {
				prop.load(file);
			}catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public int getImpliciteWait() {
		// TODO Auto-generated method stub
		return Integer.parseInt(prop.getProperty("implicitwait"));
	}

	public int getExplicitWait() {
		// TODO Auto-generated method stub
		return Integer.parseInt(prop.getProperty("explicitwait"));
	}

	public int getPageLoadTime() {
		// TODO Auto-generated method stub
		return Integer.parseInt(prop.getProperty("pageloadtime"));
	}
	
	public int getPollingMillSeconds() {
		// TODO Auto-generated method stub
		return Integer.parseInt(prop.getProperty("pollinginmillisec"));
	}

	

	public String getUrl() {
		// TODO Auto-generated method stub
		if(System.getProperty("url")!=null){
			return System.getProperty("url");
		}
		return prop.getProperty("url");
	}

	public String getUserName() {
		// TODO Auto-generated method stub
		if(System.getProperty("userName")!=null){
			return System.getProperty("userName");
		}
		return prop.getProperty("userName");
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		if(System.getProperty("password")!=null){
			return System.getProperty("password");
		}
		return prop.getProperty("password");
	}

	public String browserType() {
		// TODO Auto-generated method stub
		if(System.getProperty("browser")!=null){
			return System.getProperty("browser");
		}
		return prop.getProperty("browser");
	}

	

}
