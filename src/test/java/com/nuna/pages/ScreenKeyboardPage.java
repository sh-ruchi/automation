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
public class ScreenKeyboardPage {

	
	@FindBy(xpath ="//div/span[contains(text(),'English')]")
	private WebElement keyBoardWindowTitle;
	
	
	@FindBy(xpath = "//button[@id='K90']/preceding-sibling::button/span[contains(@class,'vk-cap')]")
	private WebElement capsButton;

	@FindBy(xpath = "//button[@id='K73']/span[contains(text(),'i')]")
	private WebElement lowerLetterI;

	@FindBy(xpath = "//button[@id='K72']/span[contains(text(),'H')]")
	private WebElement upperLetterH;
	
	@FindBy(xpath = "//button[@id='K49']/span[contains(text(),'!')]")
	private WebElement exclamation;
	
	
	public WebElement getLettersLowerCase(WebDriver driver, char text) {
		return driver.findElement(By.xpath("//div[@dir='ltr']/descendant::button[span[contains(text(),'"+text+"')]]"));
	}
	
	
	public WebElement getKeyBoardWindowTitle() {
		return keyBoardWindowTitle;
	}

	public WebElement getLowerLetterI() {
		return lowerLetterI;
	}

	public WebElement getUpperLetterH() {
		return upperLetterH;
	}

	public WebElement getExclamation() {
		return exclamation;
	}

	public WebElement getCapsButton() {
		return capsButton;
	}
}
