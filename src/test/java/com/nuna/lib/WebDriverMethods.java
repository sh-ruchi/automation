package com.nuna.lib;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
/**
 * 
 * @author Ruchika Sharma
 * 
 * class to containing webdriver methods
 */
public class WebDriverMethods {

	
	public void waitForPageToLoad(WebDriver driver){
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
	}
	
	public void waitForElementToBePresent(WebDriver driver, WebElement ele){
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void isElementClickable(WebElement ele) {
		Assert.assertTrue(ele.isEnabled(),ele+" is not clickable ");
	}
	
	public void verifyResultForEquality(String actualResult, String expResult, String errMsg) {
		Assert.assertEquals(actualResult,expResult,errMsg);
	}
	
	public void verifyElementIsPresent(WebElement ele) {
		Assert.assertTrue(ele.isDisplayed(),"Element not present");
	}
	
	public void click(WebElement ele) {
		ele.click();
	}
	
	
	public void waitForSpecifiedTime(long n) throws InterruptedException {
		Thread.sleep(n);
	}
	
	public void enterValue(WebElement ele,String val)
	{
		ele.clear();
		ele.sendKeys(val.trim());
	}
	
	public void moveToElementAndClick(WebDriver driver,WebElement eleName) throws InterruptedException
	{
		Actions act= new Actions(driver);
		act.moveToElement(eleName).click().perform();
		
		waitForSpecifiedTime(200);
	}
	public void moveToElementAndDoubleClick(WebDriver driver,WebElement eleName) throws InterruptedException
	{
		Actions act= new Actions(driver);
		act.moveToElement(eleName).doubleClick().perform();
		
		waitForSpecifiedTime(200);
	}
	
}
