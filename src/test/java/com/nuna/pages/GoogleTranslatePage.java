package com.nuna.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	
	@FindBy(xpath="//span[@class='Q4iAWc']")
	private WebElement translatedTextEle;
	
	@FindBy(xpath="//div[contains(@class,'ccvoYb EjH7wc')]/descendant::button[i[contains(text(),'swap_horiz')]]")
	private WebElement swapLanguagesButton;
	
	
	public WebElement getSwapLanguagesButton() {
		return swapLanguagesButton;
	}

	public WebElement getElementFromSuggestion(WebDriver driver, String text) {
		return driver.findElement(By.xpath("//div[div/span[contains(text(),'"+text+"')]]"));
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
