package com.nuna.lib;
/*
 * @author Ruchika Sharma
 * 
 * */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.nuna.genlib.ReadExcelData;
import com.nuna.pages.GoogleTranslatePage;

public class TranslationLib extends WebDriverMethods {

	WebDriver driver;
	Map<String, Map<String, String>> completeSheetData = new HashMap<String, Map<String, String>>();

	public TranslationLib(WebDriver driver) {
		this.driver = driver;
	}
	
	/*
	 *  navigateToUrl(String url, String expectedPageTitle)
	 *  i. 	opening the url 
	 *  ii. verifying using assertions if the desired page is opened successfully 
	 *  iii.verifying using assertions if drop down to select language is present or not
	 *  
	 */
	public void navigateToUrl(String url, String expectedPageTitle) {
		
		GoogleTranslatePage gtp = PageFactory.initElements(driver, GoogleTranslatePage.class);
		
		driver.get(url);
		waitForPageToLoad(driver);
		verifyResultForEquality(driver.getTitle(),expectedPageTitle, "Page is either not loaded or an incorrect Url provided");
		verifyElementIsPresent(gtp.getSourceLangDropDownButton());
	}
	
	
	/***
	 * readExcelDataAndTranslate()
	 * @throws InterruptedException
	 * 
	 * i. read Data from Excel using Apache POI code and stored data in Map 
	 * ii. iterated over Map to get data in each cell and
	 * iii. invoked translateText() to translate the text based on data read from Excel
	 */
	public void readExcelDataTranslateAndSwap() throws InterruptedException {
		try {
			completeSheetData = ReadExcelData.getExcelAsMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String i : completeSheetData.keySet()) {
			System.out.println(i);
			System.out.println(completeSheetData.get(i).get("source language"));
			System.out.println(completeSheetData.get(i).get("translation language"));
			System.out.println(completeSheetData.get(i).get("initial text"));
			System.out.println(completeSheetData.get(i).get("translated text"));

			String srcLang = completeSheetData.get(i).get("source language").trim();
			String transLang = completeSheetData.get(i).get("translation language").trim();
			String initialText = completeSheetData.get(i).get("initial text").trim();
			String translText = completeSheetData.get(i).get("translated text").trim();

			translateText(driver, srcLang, transLang, initialText, translText);
		}
	}

	/***
	 * 
	 * @param driver
	 * @param srcLang
	 * @param transLang
	 * @param initialText
	 * @param translText
	 * @throws InterruptedException
	 * 
	 * 
	 */
	public void translateText(WebDriver driver, String srcLang, String transLang, String initialText, String translText)
			throws InterruptedException {

		GoogleTranslatePage gtp = PageFactory.initElements(driver, GoogleTranslatePage.class);
		// enter and select srcLang in input box
		click(gtp.getSourceLangDropDownButton());
		System.out.println("Waiting for autoopen search box");
		waitForElementToBePresent(driver, gtp.getAutoOpenSrcLangSearch());
		//selecting the source Language based on excel data 
		System.out.println("Enter src lang ");
		enterValue(gtp.getSearchSrcLangTextBox(), srcLang);
		waitForSpecifiedTime(2000);
		System.out.println("waiting for suggestion list ");
		waitForElementToBePresent(driver, gtp.getElementFromSuggestion(driver, srcLang));
		// selecting the src language from suggestion based on the data typed
		System.out.println("selecting the src language from suggestion based on the data typed");
		click(gtp.getElementFromSuggestion(driver, srcLang));
		waitForSpecifiedTime(2000);
		// selecting target Translation Language and selecting the language from suggestion list
		System.out.println("selecting target Translation Language and selecting the language from suggestion list");
		isElementClickable(gtp.getTargetLangDropDownButton());
		System.out.println("Clicking target lang drop down ");
		gtp.getTargetLangDropDownButton().click();
		System.out.println("waiting. ");
		waitForPageToLoad(driver);
		waitForElementToBePresent(driver, gtp.getAutoOpenTargetLangSearch());
		System.out.println("Entering target lang");
		waitForSpecifiedTime(2000);
		enterValue(gtp.getSearchTargetLangTextBox(), transLang);
		waitForElementToBePresent(driver, gtp.getElementFromSuggestion(driver, transLang));
		System.out.println("selecting target lang from suggestion list");
		click(gtp.getElementFromSuggestion(driver, transLang));
		// entering initial text in source Language text area
		System.out.println("entering initial text in source Language text area");
		enterValue(gtp.getSrcTextArea(), initialText);
		click(gtp.getSrcTextArea());
		
		// delay is added to get the translated result
		Thread.sleep(2000);
		// Assertion to verify the translated result is as present in the excel
		System.out.println("verifying the translated result is as present in the excel");
		verifyResultForEquality(gtp.getTranslatedTextEle().getText(), translText,
				"Translated text is incorrect. Expected " + translText);
		System.out.println("Swapping language");
		swapLanguages(initialText);
	}
	
	public void swapLanguages(String initialText) throws InterruptedException {
		GoogleTranslatePage gtp = PageFactory.initElements(driver, GoogleTranslatePage.class);
		click(gtp.getSwapLanguagesButton());
		Thread.sleep(2000);
		verifyResultForEquality(gtp.getTranslatedTextEle().getText(),initialText,
				"Translated text is incorrect. Expected " + initialText);
	}
}