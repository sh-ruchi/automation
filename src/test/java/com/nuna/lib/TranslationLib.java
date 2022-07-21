package com.nuna.lib;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.nuna.genlib.ReadExcelData;
import com.nuna.pages.GoogleTranslatePage;
import com.nuna.pages.ScreenKeyboardPage;

/**
 * 
 * @author Ruchika Sharma
 * 
 * class Library to Containing methods to automate the mentioned translion scenarios
 */

public class TranslationLib extends WebDriverMethods {

	WebDriver driver;
	Map<String, Map<String, String>> completeSheetData = new HashMap<String, Map<String, String>>();

	public TranslationLib(WebDriver driver) {
		this.driver = driver;
	}
	
	/***
	 * 
	 * @param url
	 * @param expectedPageTitle
	 * navigateToUrl(String url, String expectedPageTitle)
	 *  i. 	opening the url 
	 *  ii. verifying using assertions if the desired page is opened successfully 
	 *  iii.verifying using assertions if drop down to select language is present or not
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
			String targetLang = completeSheetData.get(i).get("translation language").trim();
			String initialText = completeSheetData.get(i).get("initial text").trim();
			String translText = completeSheetData.get(i).get("translated text").trim();

			translateTextDL(driver, srcLang, targetLang, initialText, translText);
		}
	}

	/**8
	 * 
	 * @param driver
	 * @param srcLang
	 * @param targetLang
	 * @param initialText
	 * @param translText
	 * @throws InterruptedException
	 * 
	 * This method selects the languages from the options shown on the browser after clicking detect Language tab 
	 */
	public void translateTextDL(WebDriver driver, String srcLang, String targetLang, String initialText, String translText)
			throws InterruptedException {
		
		GoogleTranslatePage gtp = PageFactory.initElements(driver, GoogleTranslatePage.class);
		// select srcLang from the options by using mouse operation
		
		moveToElementAndDoubleClick(driver, gtp.getDetectLanguageButton());
		System.out.println("Waiting for autoopen search box");
		waitForElementToBePresent(driver, gtp.getAutoOpenSrcLangSearch());
		System.out.println("Selecting src lang ");
		moveToElementAndClick(driver,gtp.selectSrcLanguage(driver,srcLang));
		
		//Verify the source language tab is shown as selected 
		Assert.assertEquals(gtp.getSrcLangTab().getAttribute("aria-selected"),"true");

		System.out.println("Clicking target lang drop down ");
		gtp.getTargetLangDropDownButton().click();
		System.out.println("waiting. ");
		waitForPageToLoad(driver);
		waitForElementToBePresent(driver, gtp.getAutoOpenTargetLangSearch());
		// selecting the target Language feom the options shown  
		System.out.println("Selecting target lang");		

		moveToElementAndClick(driver,gtp.selectTargetLanguage(driver,targetLang));
		//Verifying the target language tab is shown as selected 
		Assert.assertEquals(gtp.getTargetLangTab().getAttribute("aria-selected"),"true");
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
	/***
	 * 
	 * @param driver
	 * @param srcLang
	 * @param transLang
	 * @param initialText
	 * @param translText
	 * @throws InterruptedException
	 * translateText used to select srcLang, and translang
	 * enters initial text and verifies the taranslated text 
	 * all the parameters are read from Excel sheet 
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
		// verifying the the 
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'ccvoYb EjH7wc')]/descendant::button[@data-language-code='auto']/following-sibling::button[@tabindex='0']")).getAttribute("aria-selected"),"true");
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
	
	/***
	 * 
	 * @param initialText
	 * @throws InterruptedException
	 * swapLanguages  to swap the translation languages and verifying the translated result
	 */
	public void swapLanguages(String initialText) throws InterruptedException {
		GoogleTranslatePage gtp = PageFactory.initElements(driver, GoogleTranslatePage.class);
		click(gtp.getSwapLanguagesButton());
		Thread.sleep(2000);
		verifyResultForEquality(gtp.getTranslatedTextEle().getText(),initialText,
				"Translated text is incorrect. Expected " + initialText);
	}
	
	/**
	 * @throws InterruptedException 
	 * enterTextUsingOnScreenKeyboard1 
	 * is a generic method to type any string using on screen virtual Keyboard
	 * 
	 */
	
	
public void enterAnyTextUsingOnScreenKeyboard(String word) throws InterruptedException {
		
		GoogleTranslatePage gtp = PageFactory.initElements(driver, GoogleTranslatePage.class);
		ScreenKeyboardPage skp= PageFactory.initElements(driver, ScreenKeyboardPage.class);
		click(gtp.getClearButton());
		click(gtp.getScreenKeyboardButton());
		waitForElementToBePresent(driver,skp.getKeyBoardWindowTitle());
		String lcWord= word.toLowerCase();
		for(int i=0;i<lcWord.length();i++)
		{
			skp.getLettersLowerCase(driver,lcWord.charAt(i)).click();
			waitForSpecifiedTime(200);
		}
		waitForSpecifiedTime(2000);
	}
	public void enterTextUsingOnScreenKeyboard() throws InterruptedException {
		
		GoogleTranslatePage gtp = PageFactory.initElements(driver, GoogleTranslatePage.class);
		ScreenKeyboardPage skp= PageFactory.initElements(driver, ScreenKeyboardPage.class);
		click(gtp.getClearButton());
		click(gtp.getScreenKeyboardButton());
		waitForElementToBePresent(driver,skp.getKeyBoardWindowTitle());
		click(skp.getCapsButton());
		
		
		waitForElementToBePresent(driver,skp.getUpperLetterH());
		click(skp.getUpperLetterH());
		waitForElementToBePresent(driver,skp.getLowerLetterI());
		click(skp.getLowerLetterI());
		click(skp.getCapsButton());
		waitForElementToBePresent(driver,skp.getExclamation());
		click(skp.getExclamation());
		waitForSpecifiedTime(200);
	}
}
