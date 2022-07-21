package com.nuna.tests;

/**
 * 
 * @author Ruchika Sharma
 *
 * Test class containing containing the automated tests 
 * uses Testng annotations
 */

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.nuna.genlib.Driver;
import com.nuna.genlib.ReadExcelData;
import com.nuna.lib.TranslationLib;
import com.nuna.lib.WebDriverMethods;
import com.nuna.pages.GoogleTranslatePage;
import com.nuna.pages.ScreenKeyboardPage;

public class TranslateTest {

	WebDriver driver;
	WebDriverMethods wmethods;
	GoogleTranslatePage gtp; 
	ScreenKeyboardPage skp;
	
	TranslationLib tLib;
	
	
	String srcLang,transLang,initialText,translatedText;
	
	 
	@BeforeClass(alwaysRun = true)
	public void configBeforeClass() throws Exception
	{
		driver= Driver.getDriver();
		wmethods= new WebDriverMethods();
		gtp= PageFactory.initElements(driver,GoogleTranslatePage.class);
		skp= PageFactory.initElements(driver,ScreenKeyboardPage.class);
		tLib= new TranslationLib(driver);
	
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass()
	{
		driver.quit();		
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {

		// the below code will only be executed after any test method failure to capture the screenshot
		if(ITestResult.FAILURE==result.getStatus()){
		try{
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		// Calling  method to capture screenshot
		File src=screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"/screenshots/"+result.getMethod().getMethodName()+".png"));
		System.out.println("Successfully captured a screenshot");
		}catch (Exception e){
		System.out.println("Exception while taking screenshot "+e.getMessage());
		}
		}
	}
	
	/****
	 * Scenario 1: 
	 * Translating the entered text where
	 * Source, Translation languages, initial text and expected result are taken from a separate  data file
	 * 
	 * 	Scenario 2: 
	 *  click swap languages button and verify the result. 
	 */
	
	@Test(priority=1)
	public void translateTest() throws InterruptedException {
		
		Reporter.log("***** Test Starts ***** ");
		System.out.println("translateTest starts ");
		//Launch url in chrome browser and verify if the desired Page is opened or not 
		tLib.navigateToUrl("https://translate.google.com/","Google Translate");
		// read data from Excel and stored it In Map and then performed steps for translation of entered text
		tLib.readExcelDataTranslateAndSwap();
		System.out.println("translateTest ends ");
		}
	
		
	/***
	 * Scenario 3: 
	 * 	clear the input field, click "select input tool" button, 
	 * 	select "screen keyboard" and  enter any text to be typed by 
	 *  virtual key Board  
	 * @throws InterruptedException 
	 */

		@Test(priority=2)
		public void onScreenKeyboardTest() throws InterruptedException {
			
			System.out.println("onScreenKeyboardTest starts");
			System.out.println("Clearing the input field and entering text using on screen keyboard ");
			//tLib.enterTextUsingOnScreenKeyboard();
			tLib.enterAnyTextUsingOnScreenKeyboard("bienvenidos");
			System.out.println("onScreenKeyboardTest ends ");
		}
		
		
	}
	
	
