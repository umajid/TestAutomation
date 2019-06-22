package com.stc.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;




public class TestBase {
	public static Properties Data=null;
	public static Properties OR=null;
	public static Properties Env=null;
	public static WebDriver driver =null;
	public boolean isInitalized = false;
	FileInputStream ip;

	// initializing the Tests
	public void initialize() throws Exception{
		// check if the flag status
		if(!isInitalized){

			// Initializing the Test Data File
			Data = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//com//stc//config/Data.properties");
			Data.load(ip);

			// Initializing the object repository File
			OR = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//com//stc//config/OR.properties");
			OR.load(ip);

			// Initializing the Env File
			Env = new Properties();
			ip = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//com//stc//config/Env.properties");
			Env.load(ip);
			isInitalized=true;
		}
	}

	// open a browser if its not opened
	public void openBrowser(){
		if(Env.getProperty("browserType").equals("MOZILLA")){
			System.setProperty("webdriver.gecko.driver","path of geckodriver.exe");
		driver = new FirefoxDriver();
	}
		else if (Env.getProperty("browserType").equals("IE")){
			System.setProperty("webdriver.ie.driver","path of geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else if (Env.getProperty("browserType").equals("CHROME")){
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//test//resources//com//stc//drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}

		String waitTime=Env.getProperty("default_implicitWait");
		driver.manage().timeouts().implicitlyWait(Long.parseLong(waitTime), TimeUnit.SECONDS);
		driver.manage().window().maximize();


	}
	



}
