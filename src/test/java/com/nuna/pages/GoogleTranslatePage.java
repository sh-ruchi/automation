package com.nuna.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
/**
 * 
 * @author Ruchika Sharma
 *
 */
public class GoogleTranslatePage {

	
	@FindBy(xpath = "//div[contains(@class,'ccvoYb EjH7wc')]/descendant::button[contains(@aria-label,'More source languages')]")
	private WebElement sourceLangDropDownButton;
	
	@FindBy(xpath = "//div[contains(@class,'ccvoYb EjH7wc')]/descendant::button[contains(@aria-label,'More target languages')]")
	private WebElement targetLangDropDownButton;

	
	@FindBy(xpath= "//div[contains(@class,'ccvoYb EjH7wc')]/descendant::div[@class='OoYv6d']/div[@data-auto-open-search='true']")
	private WebElement autoOpenSrcLangSearch;
	
	@FindBy(xpath= "//div[contains(@class,'ccvoYb EjH7wc')]/descendant::div[@class='ykTHSe']/div[@data-auto-open-search='true']")
	private WebElement autoOpenTargetLangSearch;
	
	@FindBy(xpath="//div[contains(@class,'ccvoYb EjH7wc')]/descendant::div[@class='OoYv6d']/descendant::input[@aria-label='Search languages']")
	private WebElement searchSrcLangTextBox;
	
	@FindBy(xpath="//div[contains(@class,'ccvoYb EjH7wc')]/descendant::div[@class='ykTHSe']/descendant::input[@aria-label='Search languages']")
	private WebElement searchTargetLangTextBox;
	
	@FindBy(xpath = "//textarea[@aria-label='Source text']")
	private WebElement srcTextArea;
	
	@FindBy(xpath = "//textarea[@aria-label='Source text']/following-sibling::div[@jsname='lKng5e']")
	private WebElement srcTextAreaText;
	
	
	@FindBy(xpath="//span[@class='Q4iAWc']")
	private WebElement translatedTextEle;
	
	@FindBy(xpath="//div[contains(@class,'ccvoYb EjH7wc')]/descendant::button[i[contains(text(),'swap_horiz')]]")
	private WebElement swapLanguagesButton;
	
	@FindBy(xpath="//div[@class='DVHrxd']/descendant::button[div[@jsname='s3Eaab']]/descendant::div[contains(@class,'RLmnJb')]")
	private WebElement clearButton;
	
	@FindBy(xpath = "//a[contains(@aria-label,'Show the Input Tools menu')]")
	private WebElement inputToolButton;
	
	@FindBy(xpath="//a[contains(@class,'kd-inputtool')]")
	private WebElement screenKeyboardButton;
	
	@FindBy(xpath = "//div[contains(@class,'ccvoYb EjH7wc')]/descendant::span[contains(text(),'Detect language')]")
	private WebElement detectLanguageButton;
	
	@FindBy(xpath="//div[contains(@class,'ccvoYb EjH7wc')]/descendant::button[@data-language-code='auto']/following-sibling::button[@tabindex='0']")
	private WebElement srcLangTab;
	
	@FindBy(xpath="		//div[contains(@class,'ccvoYb EjH7wc')]/descendant::div[contains(@jscontroller,'HwavCb')]/following-sibling::div/descendant::button[@tabindex='0']")
	private WebElement targetLangTab;
	
	public WebElement getSrcLangTab() {
		return srcLangTab;
	}

	public WebElement getTargetLangTab() {
		return targetLangTab;
	}

	public WebElement getScreenKeyboardButton() {
		return screenKeyboardButton;
	}

	public WebElement getSrcTextAreaText() {
		return srcTextAreaText;
	}

	public WebElement getInputToolButton() {
		return inputToolButton;
	}

	public WebElement getClearButton() {
		return clearButton;
	}

	public WebElement getSwapLanguagesButton() {
		return swapLanguagesButton;
	}

	public WebElement getElementFromSuggestion(WebDriver driver, String text) {
		return driver.findElement(By.xpath("//div[div/span[contains(text(),'"+text+"')]]"));
	}
	
	public WebElement selectSrcLanguage(WebDriver driver, String lang) {
		return driver.findElement(By.xpath("//div[contains(@class,'ccvoYb EjH7wc')]/descendant::div[@class='OoYv6d']/descendant::div[contains(@jsname,'rPx1uf')]/descendant::div[contains(text(),'"+lang+"')]"));
	}
	
	public WebElement selectTargetLanguage(WebDriver driver, String lang) {
		return driver.findElement(By.xpath("//div[contains(@class,'ccvoYb EjH7wc')]/descendant::div[@class='ykTHSe']/descendant::div[contains(@jsname,'rPx1uf')]/descendant::div[contains(text(),'"+lang+"')]"));
	}
	public WebElement getSrcTextArea() {
		return srcTextArea;
	}
			
	public WebElement getTranslatedTextEle() {
		return translatedTextEle;
	}

	public WebElement getSearchTargetLangTextBox() {
		return searchTargetLangTextBox;
	}

	public WebElement getSearchSrcLangTextBox() {
		return searchSrcLangTextBox;
	}

	public WebElement getDetectLanguageButton() {
		return detectLanguageButton;
	}


	public WebElement getAutoOpenSrcLangSearch() {
		return autoOpenSrcLangSearch;
	}

	public WebElement getAutoOpenTargetLangSearch() {
		return autoOpenTargetLangSearch;
	}

	public WebElement getSourceLangDropDownButton() {
		return sourceLangDropDownButton;
	
	}
	
	public WebElement getTargetLangDropDownButton() {
		return targetLangDropDownButton;
	}

}
