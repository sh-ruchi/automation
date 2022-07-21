package com.nuna.genlib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
/***
 * 
 * @author Ruchika Sharma
 *	Driver class to instatiate webdriver chrome instance  
 *
 */
public class Driver {

	public static WebDriver driver;
	public static DesiredCapabilities capabilities=null;
	
	public static WebDriver getDriver() throws Exception
	{
		
		
		 				System.out.println("Inside DEFAULT DRIVER");
						System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")+"/config/drivers/chromedriver.exe");
						driver=new ChromeDriver(); 
				
						driver.manage().window().maximize();
						return driver;
	}

}