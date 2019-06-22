package com.stc.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TestUtils extends TestBase{
	
	public void openApplication(){
		try{
			
		// Page timeout
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		// open application
		driver.get(Env.getProperty("url"));
		// maximize window
		driver.manage().window().maximize();

		}
		catch(Exception e){
			Assert.assertTrue(false, "Unable to launch Url" + e.getMessage());
		}
	}
	
	/**
	 * This method will use to login 
	 * @param UserNmae- Pass String as UserName
	 * @param Password- Pass String as Pasword
	 */
	public void LoginToApplication(String UserNmae,String Password) {
		try {
			//click on login button
			driver.findElement(By.xpath(OR.getProperty("LoginButton"))).click();
			driver.findElement(By.xpath(OR.getProperty("UserNameInputBox"))).sendKeys(UserNmae);
			driver.findElement(By.xpath(OR.getProperty("PasswordInputBox"))).sendKeys(Password);
			driver.findElement(By.xpath(OR.getProperty("LoginClickButton"))).click();
			wait(4);
		}catch (Exception e) {
			Assert.assertTrue(false, "Unable to login on application" + e.getMessage());
		}
	}
	
	
	/**
	 * This method will use to wait
	 * @param num- Pass integer value for wait time in second 
	 * @throws InterruptedException
	 */
	public void wait(int num) throws InterruptedException {
		int waitTime= num*1000;
		Thread.sleep(waitTime);
	}
	
	/**
	 * this method will use to delete the cart value if product is available in cart
	 */
	public void deleteCartProductIfAvailable() {
		try {
			String cartValue = driver.findElement(By.xpath(OR.getProperty("CartCount"))).getText();
			int cartCount = Integer.parseInt(cartValue);
			if (cartCount != 0) {
				driver.findElement(By.xpath(OR.getProperty("CartCount"))).click();
				wait(4);
				driver.findElement(By.xpath(OR.getProperty("DeleteItem"))).click();
				wait(6);
				System.out.println("User has successfully remove the product from cart");
			}

		} catch (Exception e) {
			Assert.assertTrue(false, "Unable to login on application" + e.getMessage());
		}
	}
	
	/**
	 * this method will use to scroll the page hori
	 */
	public void pageScrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("window.scrollBy(0,200)");		
	}
	

}
