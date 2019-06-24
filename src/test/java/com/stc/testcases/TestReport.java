package com.stc.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.stc.base.Reports;
import com.stc.base.TestBase;
import com.stc.base.TestUtils;

public class TestReport extends TestBase {

	TestUtils utillObj = new TestUtils();

	@BeforeSuite
	public void doSetup() {
		// Initializing the Config Files
		try {
			initialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.assertTrue(false, e.getMessage());
		}

	}

	/**
	 * this method will use to close the browser after every test
	 */
	@AfterMethod
	public void close() {
		try {
			Reports.info("**************************' TEST CASE FINISH  '*****************************");
			Reports.endTest();
			Reports.flush();
			driver.close();
			
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
		}

	}

	/**
	 * Here we will validate the "email verification is required" message appears
	 * the users login without email verification
	 */
	@Test(priority = 1)
	public void verifyEmailVerificationMessage_EmailVerificationSheet_TC02() {
		try {

			/**
			 * This method will use to open Browser
			 */
			openBrowser();
			Reports.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(), "-TC02");
			Reports.info("Open browser");
			/**
			 * This method will use to open the application URL
			 */
			utillObj.openApplication();
			Reports.info("Open Application URL");

			// Login to application with unverified email and mobile number
			utillObj.LoginToApplication(Data.getProperty("UnverifiedEmail"), Data.getProperty("UnverifiedEmailPwd"));
			Reports.info("User has successfully logged in");
			/**
			 * ValidateMessage
			 */
			utillObj.wait(7);
			String VerificationMsg1 = driver.findElement(By.xpath(OR.getProperty("EmailVerificationMsg1"))).getText()
					.trim();
			String VerificationMsg2 = driver.findElement(By.xpath(OR.getProperty("EmailVerificationMsg2"))).getText()
					.trim();
			if (VerificationMsg1.contains(Data.getProperty("EmailVerificationMessage1"))
					&& VerificationMsg2.equals(Data.getProperty("EmailVerificationMessage2"))) {
				Assert.assertTrue(true, "As Expected,'Email verification is required' message is displayed");
				Reports.pass("As Expected,'Email verification is required' message is displayed on header");
			} else {
				Reports.fail("'Email verification is required' message is not displayed for users login	without email verification");
				Assert.assertTrue(false,
						"'Email verification is required' message is not displayed for users login	without email verification");
				
			}
			Reports.pass("Open browser3");
		} catch (NoSuchElementException nse) {
			Reports.fail("NoSuchWebElement expection occured for the WebElment--"+nse.getMessage());
			Assert.assertTrue(false, "NoSuchWebElement expection occured for the WebElment--" + nse.getMessage());
		} catch (Exception e) {
			Reports.fail("Exception Occured--"+e.getMessage());
			Assert.assertTrue(false, "Exception Occured as --" + e.getMessage());
		}
	}
	
}
