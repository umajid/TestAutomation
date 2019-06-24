package com.stc.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.stc.base.TestBase;
import com.stc.base.TestUtils;

public class STC_Sprint1_TestCases extends TestBase {

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
			driver.close();
		} catch (Exception e) {
			Assert.assertTrue(false, e.getMessage());
		}

	}

	/**
	 * Here we will validate the "email verification is required" message appears
	 * the users login without email verification
	 */
	@Test(priority=1)
	public void verifyEmailVerificationMessage_EmailVerificationSheet_TC02() {
		try {

			/**
			 * This method will use to open Browser
			 */
			openBrowser();

			/**
			 * This method will use to open the application URL
			 */
			utillObj.openApplication();

			// Login to application with unverified email and mobile number
			utillObj.LoginToApplication(Data.getProperty("UnverifiedEmail"),
					Data.getProperty("UnverifiedEmailPwd"));

			/**
			 * ValidateMessage
			 */
			utillObj.wait(7);
			String VerificationMsg1 =
					driver.findElement(By.xpath(OR.getProperty("EmailVerificationMsg1"))).getText()
					.trim();
			String VerificationMsg2 =
					driver.findElement(By.xpath(OR.getProperty("EmailVerificationMsg2"))).getText()
					.trim();
			if (VerificationMsg1.contains(Data.getProperty("EmailVerificationMessage1"))
					&& VerificationMsg2.equals(Data.getProperty("EmailVerificationMessage2"))) {
				Assert.assertTrue(true, "As Expected,'Email verification is required' message is displayed");
			} else {
				Assert.assertTrue(false,
						"'Email verification is required' message is not displayed for users login	without email verification");
			}

		} catch (NoSuchElementException nse) {
			Assert.assertTrue(false, "NoSuchWebElement expection occured for the WebElment--" + nse.getMessage());
		} catch (Exception e) {
			Assert.assertTrue(false, "Exception Occured as --" + e.getMessage());
		}
	}
	
	
	@Test(priority=2)
	public void
	verifyStatusOfMobileMumberAndEmailAddress_EmailVerificationSheet_TC03() {
		try {

			/**
			 * This method will use to open Browser
			 */
			openBrowser();

			/**
			 * This method will use to open the application URL
			 */
			utillObj.openApplication();

			// Login to application with unverified email and mobile number
			utillObj.LoginToApplication(Data.getProperty("UnverifiedEmail"),
					Data.getProperty("UnverifiedEmailPwd"));

			// Click on Login Button
			utillObj.wait(7);
			driver.findElement(By.xpath(OR.getProperty("DropDownLoginButton"))).click();

			// Click on profile link.
			driver.findElement(By.xpath(OR.getProperty("ProfileLink"))).click();
			utillObj.wait(2);

			// Validate 'verify' link for Mob and Email
			if
			(driver.findElement(By.xpath(OR.getProperty("MobileVerifyLink"))).isDisplayed()
					&&
					driver.findElement(By.xpath(OR.getProperty("EmailVerifyLink"))).isDisplayed())
			{
				if
				(driver.findElement(By.xpath(OR.getProperty("MobileVerifyLink"))).getText()
						.equals(Data.getProperty("VerifyText"))
						&& driver.findElement(By.xpath(OR.getProperty("EmailVerifyLink"))).getText()
						.equals(Data.getProperty("VerifyText"))) {
					Assert.assertTrue(true,
							"As Expected,'Verify Link and verify text is displayed for both Email and Mobile");
				} else {
					Assert.assertTrue(false, "Verify Link is displayed but text is not displayed on link as 'Verify' ");
				}
			} else {
				Assert.assertTrue(false, "Verify Link is not displayed for Mobile OR Email");
			}

		} catch (NoSuchElementException nse) {
			Assert.assertTrue(false, "NoSuchWebElement expection occured for the WebElment--" + nse.getMessage());
		} catch (Exception e) {
			Assert.assertTrue(false, "Exception Occured as --" + e.getMessage());
		}
	}



	@Test(priority=3)
	public void verifyModifyPopUPforEmailAndMobile_EmailVerificationSheet_TC04()
	{
		try {

			/**
			 * This method will use to open Browser
			 */
			openBrowser();

			/**
			 * This method will use to open the application URL
			 */
			utillObj.openApplication();

			// Login to application with unverified email and mobile number
			utillObj.LoginToApplication(Data.getProperty("UnverifiedEmail"),
					Data.getProperty("UnverifiedEmailPwd"));

			// Click on Login Button
			utillObj.wait(7);
			driver.findElement(By.xpath(OR.getProperty("DropDownLoginButton"))).click();

			// Click on profile link.
			driver.findElement(By.xpath(OR.getProperty("ProfileLink"))).click();
			utillObj.wait(2);

			//click on Edit profile
			driver.findElement(By.xpath(OR.getProperty("EditProfile"))).click();
			utillObj.wait(2);

			// click on modify email
			driver.findElement(By.xpath(OR.getProperty("ModifyEmailButton"))).click();
			utillObj.wait(2);

			// Validate 'Modify PopUp'for email
			if
			(driver.findElement(By.xpath(OR.getProperty("ModifyEmailPopUp"))).isDisplayed())
			{
				Assert.assertTrue(true, "As Expected,'Modify PopUp' is displayed for Email");
			} else {
				Assert.assertTrue(false, "'Modify PopUp' is not displayed for Email");
			}

			// click on modify Mobile number
			driver.findElement(By.xpath(OR.getProperty("CloseModifyPopUpButton"))).click();
			utillObj.wait(2);
			driver.findElement(By.xpath(OR.getProperty("ModifyMobileButton"))).click();
			utillObj.wait(2);

			// Validate 'Modify PopUp'for email
			if
			(driver.findElement(By.xpath(OR.getProperty("ModifyMobilePopUp"))).isDisplayed())
			{
				Assert.assertTrue(true, "As Expected,'Modify PopUp' is displayed for Mobile");
			} else {
				Assert.assertTrue(false, "'Modify PopUp' is not displayed for mobile");
			}

			// click on modify Mobile number
			driver.findElement(By.xpath(OR.getProperty("CloseModifyPopUpButton"))).click();
			utillObj.wait(2);

		} catch (NoSuchElementException nse) {
			Assert.assertTrue(false, "NoSuchWebElement expection occured for the WebElment--" + nse.getMessage());
		} catch (Exception e) {
			Assert.assertTrue(false, "Exception Occured as --" + e.getMessage());
		}
	}



	@Test(priority=4)
	public void verifyDuplicateEmailAddress_EmailVerificationSheet_TC07() {
		try {

			/**
			 * This method will use to open Browser
			 */
			openBrowser();

			/**
			 * This method will use to open the application URL
			 */
			utillObj.openApplication();

			// Login to application with unverified email and mobile number
			utillObj.LoginToApplication(Data.getProperty("UnverifiedEmail"),
					Data.getProperty("UnverifiedEmailPwd"));

			// Click on Login Button
			utillObj.wait(7);
			driver.findElement(By.xpath(OR.getProperty("DropDownLoginButton"))).click();

			// Click on profile link.
			driver.findElement(By.xpath(OR.getProperty("ProfileLink"))).click();
			utillObj.wait(2);

			//click on Edit profile
			driver.findElement(By.xpath(OR.getProperty("EditProfile"))).click();
			utillObj.wait(2);

			// click on modify email
			driver.findElement(By.xpath(OR.getProperty("ModifyEmailButton"))).click();
			utillObj.wait(2);

			// Validate 'Modify PopUp'for email
			if
			(driver.findElement(By.xpath(OR.getProperty("ModifyEmailPopUp"))).isDisplayed())
			{
				Assert.assertTrue(true, "As Expected,'Modify PopUp' is displayed for Email");
			} else {
				Assert.assertTrue(false, "'Modify PopUp' is not displayed for Email");
			}

			//Enter Exist email ID
			driver.findElement(By.xpath(OR.getProperty("NewEmailInputBox"))).sendKeys(Data.getProperty("UnverifiedEmail"));

			//Click on Submit Button
			driver.findElement(By.xpath(OR.getProperty("SubmitButtonForNewEmail"))).click();

			// validate error message
			if
			(driver.findElement(By.xpath(OR.getProperty("ErrorTextForExistsEmail"))).getText().trim()
					.equals(Data.getProperty("ErrorMsgForExistEmail"))) {
				Assert.assertTrue(true, "As Expected,'Error message is displayed");
			} else {
				Assert.assertTrue(false, "Error message is not displayed");
			}
			utillObj.wait(1);

		} catch (NoSuchElementException nse) {
			Assert.assertTrue(false, "NoSuchWebElement expection occured for the WebElment--" + nse.getMessage());
		} catch (Exception e) {
			Assert.assertTrue(false, "Exception Occured as --" + e.getMessage());
		}
	}


	@Test(priority=5)
	public void validateMobileAndEmailVerifyPopUp_EmailVerificationSheet_TC05() {
		try {

			/**
			 * This method will use to open Browser
			 */
			openBrowser();

			/**
			 * This method will use to open the application URL
			 */
			utillObj.openApplication();

			// Login to application with unverified email and mobile number
			utillObj.LoginToApplication(Data.getProperty("UnverifiedEmail"),
					Data.getProperty("UnverifiedEmailPwd"));

			// Click on Login Button
			utillObj.wait(7);
			driver.findElement(By.xpath(OR.getProperty("DropDownLoginButton"))).click();

			// Click on profile link.
			driver.findElement(By.xpath(OR.getProperty("ProfileLink"))).click();
			utillObj.wait(2);


			// Click on verify link.
			driver.findElement(By.xpath(OR.getProperty("MobileVerifyLink"))).click();
			utillObj.wait(9);

			// Validate verify popup for mobile
			if
			(driver.findElement(By.xpath(OR.getProperty("MobileVerifyPopUp"))).isDisplayed())
			{
				Assert.assertTrue(true, "As Expected,'Verify PopUp' is displayed for Mobile");
			} else {
				Assert.assertTrue(false, "'Verify PopUp' is not displayed for Mobile");
			}

			// click on modify Mobile number
			driver.findElement(By.xpath(OR.getProperty("CloseModifyPopUpButton"))).click();
			utillObj.wait(2);

			// Click on verify link.
			driver.findElement(By.xpath(OR.getProperty("EmailVerifyLink"))).click();
			utillObj.wait(2);

			// Validate verify popup for mobile
			if
			(driver.findElement(By.xpath(OR.getProperty("EmailVerifyPopUp"))).isDisplayed())
			{
				Assert.assertTrue(true, "As Expected,'Verify PopUp' is displayed for Email");
			} else {
				Assert.assertTrue(false, "'Verify PopUp' is not displayed for Email");
			}


		} catch (NoSuchElementException nse) {
			Assert.assertTrue(false, "NoSuchWebElement expection occured for the WebElment--" + nse.getMessage());
		} catch (Exception e) {
			Assert.assertTrue(false, "Exception Occured as --" + e.getMessage());
		}
	}



	@Test(priority=6)
	public void
	validateStatusOfMobileMumberAndEmailAddressAsVerified_EmailVerificationSheet_TC06()
	{
		try {

			/**
			 * This method will use to open Browser
			 */
			openBrowser();

			/**
			 * This method will use to open the application URL
			 */
			utillObj.openApplication();

			// Login to application with unverified email and mobile number
			utillObj.LoginToApplication(Data.getProperty("VerifiedEmail"),
					Data.getProperty("VerifiedEmailPwd"));

			// Click on Login Button
			utillObj.wait(7);
			driver.findElement(By.xpath(OR.getProperty("DropDownLoginButton"))).click();

			// Click on profile link.
			driver.findElement(By.xpath(OR.getProperty("ProfileLink"))).click();
			utillObj.wait(2);

			// Validate 'verify' link for Mob and Email
			if
			(driver.findElement(By.xpath(OR.getProperty("MobileVerifiedLink"))).isDisplayed()
					&&
					driver.findElement(By.xpath(OR.getProperty("EmailVerifiedLink"))).isDisplayed())
			{
				if
				(driver.findElement(By.xpath(OR.getProperty("MobileVerifiedLink"))).getText()
						.equals(Data.getProperty("VerifiedText"))
						&&
						driver.findElement(By.xpath(OR.getProperty("EmailVerifiedLink"))).getText()
						.equals(Data.getProperty("VerifiedText"))) {
					Assert.assertTrue(true,
							"As Expected,'Verified Link and Verified text is displayed for both Email and Mobile");
				} else {
					Assert.assertTrue(false, "Verified Link is displayed but text is not displayed on link as 'Verified' ");
				}
			} else {
				Assert.assertTrue(false, "Verified Link is not displayed for Mobile OR	Email");
			}

		} catch (NoSuchElementException nse) {
			Assert.assertTrue(false, "NoSuchWebElement expection occured for the WebElment--" + nse.getMessage());
		} catch (Exception e) {
			Assert.assertTrue(false, "Exception Occured as --" + e.getMessage());
		}
	}

	@Test(priority=7)
	public void
	validateVerifiedTextForEmailAndMobileOnCheckOutPage_EmailVerificationSheet_TC08()
	{
		try {

			/**
			 * This method will use to open Browser
			 */
			openBrowser();

			/**
			 * This method will use to open the application URL
			 */
			utillObj.openApplication();

			// Login to application with unverified email and mobile number
			utillObj.LoginToApplication(Data.getProperty("VerifiedEmail"),
					Data.getProperty("VerifiedEmailPwd"));

			//Check Product in cart before adding in cart
			utillObj.wait(7);
			utillObj.deleteCartProductIfAvailable();


			// Click on Category
			driver.findElement(By.xpath(OR.getProperty("CategorySmartPhone"))).click();

			// Click on apple sub category.
			utillObj.wait(1);
			driver.findElement(By.xpath(OR.getProperty("AppleSmartPhoneTab"))).click();


			//click on add to cart button
			utillObj.wait(5);
			driver.findElement(By.xpath(OR.getProperty("AddToCartButton"))).click();


			// click on modify email
			utillObj.wait(2);
			driver.findElement(By.xpath(OR.getProperty("CheckOutButton"))).click();


			// Validate 'verify' link for email and mobile number
			utillObj.wait(6);
			if
			(driver.findElement(By.xpath(OR.getProperty("MobileVerifiedLink"))).isDisplayed()
					&&
					driver.findElement(By.xpath(OR.getProperty("EmailVerifiedLink"))).isDisplayed())
			{
				if
				(driver.findElement(By.xpath(OR.getProperty("MobileVerifiedLink"))).getText()
						.equals(Data.getProperty("VerifiedText"))
						&&
						driver.findElement(By.xpath(OR.getProperty("EmailVerifiedLink"))).getText()
						.equals(Data.getProperty("VerifiedText"))) {
					Assert.assertTrue(true,
							"As Expected,'Verified Link and Verified text is displayed for both Email and "
							+ "Mobile on checkout page");
				} else {
					Assert.assertTrue(false, "Verified Link is displayed but text is not"
							+ "displayed on link as Verified'on checkout page ");
				}
			} else {
				Assert.assertTrue(false, "Verified Link is not displayed for Mobile OR Email on checkout page");
			}


		} catch (NoSuchElementException nse) {
			Assert.assertTrue(false, "NoSuchWebElement expection occured for the WebElment--" + nse.getMessage());
		} catch (Exception e) {
			Assert.assertTrue(false, "Exception Occured as --" + e.getMessage());
		}
	}



	@Test(priority=8)
	public void validateQitafPaymentMethod_QitafRedeemSheet_TC01() {
		try {

			/**
			 * This method will use to open Browser
			 */
			openBrowser();

			/**
			 * This method will use to open the application URL
			 */
			utillObj.openApplication();

			// Login to application with unverified email and mobile number
			utillObj.LoginToApplication(Data.getProperty("VerifiedEmail"),
					Data.getProperty("VerifiedEmailPwd"));

			//Check Product in cart before adding in cart
			utillObj.wait(7);
			utillObj.deleteCartProductIfAvailable();


			// Click on Category
			driver.findElement(By.xpath(OR.getProperty("CategorySmartPhone"))).click();

			// Click on apple sub category.
			utillObj.wait(1);
			driver.findElement(By.xpath(OR.getProperty("AppleSmartPhoneTab"))).click();


			//click on add to cart button
			utillObj.wait(5);
			driver.findElement(By.xpath(OR.getProperty("AddToCartButton"))).click();


			// click on modify email
			utillObj.wait(2);
			driver.findElement(By.xpath(OR.getProperty("CheckOutButton"))).click();


			// click on modify email
			utillObj.wait(4);
			driver.findElement(By.xpath(OR.getProperty("NextButton"))).click();

			// click on modify email
			utillObj.wait(2);
			driver.findElement(By.xpath(OR.getProperty("NextButton"))).click();

			// Validate 'Qitaf' payment method
			utillObj.wait(3);
			if
			(driver.findElement(By.xpath(OR.getProperty("QitafPaymentMethod"))).isDisplayed())
			{
				if
				(driver.findElement(By.xpath(OR.getProperty("QitafPaymentMethod"))).getText().trim()
						.equals(Data.getProperty("QitafPaymentMethodText"))) {
					Assert.assertTrue(true,
							"As Expected,'Qitaf' Payment method is displayed as payment method.");
				} else {
					Assert.assertTrue(false, "'Qitaf' Payment method is not displayed");
				}
			} else {
				Assert.assertTrue(false, "'Qitaf' Payment method is not displayed");
			}


		} catch (NoSuchElementException nse) {
			Assert.assertTrue(false, "NoSuchWebElement expection occured for the	WebElment--" + nse.getMessage());
		} catch (Exception e) {
			Assert.assertTrue(false, "Exception Occured as --" + e.getMessage());
		}
	}


	@Test(priority = 9)
	public void validatePaymentWithInvalidMobileNumber_QitafRedeemSheet_TC06() {
		try {

			/**
			 * This method will use to open Browser
			 */
			openBrowser();

			/**
			 * This method will use to open the application URL
			 */
			utillObj.openApplication();

			// Login to application with unverified email and mobile number
			utillObj.LoginToApplication(Data.getProperty("VerifiedEmail"), Data.getProperty("VerifiedEmailPwd"));

			// Check Product in cart before adding in cart
			utillObj.wait(6);
			utillObj.deleteCartProductIfAvailable();

			// Click on Category
			driver.findElement(By.xpath(OR.getProperty("CategorySmartPhone"))).click();

			// Click on apple sub category.
			utillObj.wait(1);
			driver.findElement(By.xpath(OR.getProperty("AppleSmartPhoneTab"))).click();

			// click on add to cart button
			utillObj.wait(5);
			driver.findElement(By.xpath(OR.getProperty("AddToCartButton"))).click();

			// click on modify email
			utillObj.wait(2);
			driver.findElement(By.xpath(OR.getProperty("CheckOutButton"))).click();

			// click on modify email
			utillObj.wait(4);
			driver.findElement(By.xpath(OR.getProperty("NextButton"))).click();

			// click on modify email
			utillObj.wait(2);
			driver.findElement(By.xpath(OR.getProperty("NextButton"))).click();
			utillObj.pageScrollDown();

			// select payment method
			utillObj.wait(2);
			String QitafCheckBox=driver.findElement(By.xpath(OR.getProperty("CheckBoxSelection"))).getAttribute("class");
			if(QitafCheckBox.equals(OR.getProperty("SelectedCheckBoxAttrValue"))) {
				driver.findElement(By.xpath(OR.getProperty("NextButton"))).click();
				utillObj.wait(5);
			}else {
				driver.findElement(By.xpath(OR.getProperty("CheckBoxSelection"))).click();
				driver.findElement(By.xpath(OR.getProperty("NextButton"))).click();
				utillObj.wait(6);
			}

			// Validate Error message for invalid mobile number
			utillObj.pageScrollDown();
			driver.findElement(By.xpath(OR.getProperty("MobileNumberInputBox")))
					.sendKeys(Data.getProperty("InvalidMobNumber"));
			if (driver.findElement(By.xpath(OR.getProperty("ErrorTextForMobileNumber"))).getText().trim()
					.equals(Data.getProperty("InvalidMobileNumberErrorText"))) {

				Assert.assertTrue(true, "As Expected,'Error message is displayed");

			} else {
				Assert.assertTrue(false, "Error message is not displayed");
			}

		} catch (NoSuchElementException nse) {
			Assert.assertTrue(false, "NoSuchWebElement expection occured for the WebElment--" + nse.getMessage());
		} catch (Exception e) {
			Assert.assertTrue(false, "Exception Occured as --" + e.getMessage());
		}
	}
	

	
	@Test(priority = 11)
	public void validateAddToCartProductGrayColor_StockAvailabilitySheet_TC03() {
		try {

			/**
			 * This method will use to open Browser
			 */
			openBrowser();

			/**
			 * This method will use to open the application URL
			 */
			utillObj.openApplication();

			// Login to application with unverified email and mobile number
			utillObj.LoginToApplication(Data.getProperty("VerifiedEmail"), Data.getProperty("VerifiedEmailPwd"));

			// select city
			utillObj.wait(6);
			driver.findElement(By.xpath(OR.getProperty("SelectCity"))).click();
			
			

			// Select Sabya
			utillObj.wait(2);
			driver.findElement(By.xpath(OR.getProperty("Sabya"))).click();

			// Click on search input box
			//utillObj.wait(8);
			//driver.findElement(By.xpath(Data.getProperty("SearchBox"))).click();
			

			// Out of stock product search
			utillObj.wait(6);
			driver.findElement(By.xpath(OR.getProperty("SearchBox"))).sendKeys(Data.getProperty("OutOfStockProduct"));
			
			// Out of stock product search
			utillObj.wait(3);
			driver.findElement(By.xpath(OR.getProperty("OutOfStockProductLink"))).click();
			utillObj.wait(10);

			
			// Out of stock product search
			driver.findElement(By.xpath(OR.getProperty("SearchBox"))).sendKeys(Data.getProperty("OutOfStockProduct"));
			
			//Add to cart
			Boolean flag=driver.findElement(By.xpath(OR.getProperty("AddToCartButton"))).isEnabled();
			if(flag==false) {
				Assert.assertTrue(true, "As Expected, Add to cart is disabled");
			}else {
				Assert.assertTrue(false, " Add to cart is not disabled");
			}
			
		} catch (NoSuchElementException nse) {
			Assert.assertTrue(false, "NoSuchWebElement expection occured for the WebElment--" + nse.getMessage());
		} catch (Exception e) {
			Assert.assertTrue(false, "Exception Occured as --" + e.getMessage());
		}
	}

	@Test(priority = 12)
	public void validateWishListSelection_StockAvailabilitySheet_TC05() {
		try {

			/**
			 * This method will use to open Browser
			 */
			openBrowser();

			/**
			 * This method will use to open the application URL
			 */
			utillObj.openApplication();

			// Login to application with unverified email and mobile number
			utillObj.LoginToApplication(Data.getProperty("VerifiedEmail"), Data.getProperty("VerifiedEmailPwd"));

			// select city drop down
			utillObj.wait(6);
			driver.findElement(By.xpath(OR.getProperty("SelectCity"))).click();


			// Select Sabya
			utillObj.wait(2);
			driver.findElement(By.xpath(OR.getProperty("Sabya"))).click();
	

			// Out of stock product search
			utillObj.wait(6);
			driver.findElement(By.xpath(OR.getProperty("SearchBox"))).sendKeys(Data.getProperty("OutOfStockProduct"));
			
			// Out of stock product search
			utillObj.wait(3);
			driver.findElement(By.xpath(OR.getProperty("OutOfStockProductLink"))).click();
			utillObj.wait(10);
			
			
			//CheckWishList
			String AttValue=driver.findElement(By.xpath(OR.getProperty("WishList"))).getAttribute("class").trim();
			if(AttValue.equals(OR.getProperty("SelectedWishListAttValue"))) {
				driver.findElement(By.xpath(OR.getProperty("WishListClick"))).click();
				utillObj.wait(2);
				}
			
			//Click on wishList
			driver.findElement(By.xpath(OR.getProperty("WishListClick"))).click();
			
			//Validate wishList
			utillObj.wait(2);
			String AttValue1=driver.findElement(By.xpath(OR.getProperty("WishList"))).getAttribute("class").trim();
			if(AttValue1.equals(OR.getProperty("SelectedWishListAttValue"))) {
				Assert.assertTrue(true, "As Expected, Product added in wishlist");
			}else {
				Assert.assertTrue(false, "Product is not added in  wishlist");
			}
			
		} catch (NoSuchElementException nse) {
			Assert.assertTrue(false, "NoSuchWebElement expection occured for the WebElment--" + nse.getMessage());
		} catch (Exception e) {
			Assert.assertTrue(false, "Exception Occured as --" + e.getMessage());
		}
	}


	@Test(priority = 13)
	public void validateNotifyButtons_StockAvailabilitySheet_TC06() {
		try {

			/**
			 * This method will use to open Browser
			 */
			openBrowser();

			/**
			 * This method will use to open the application URL
			 */
			utillObj.openApplication();

			// Login to application with unverified email and mobile number
			utillObj.LoginToApplication(Data.getProperty("VerifiedEmail"), Data.getProperty("VerifiedEmailPwd"));

			// select city drop down
			utillObj.wait(6);
			driver.findElement(By.xpath(OR.getProperty("SelectCity"))).click();


			// Select Sabya
			utillObj.wait(2);
			driver.findElement(By.xpath(OR.getProperty("Sabya"))).click();
	

			// Out of stock product search
			utillObj.wait(6);
			driver.findElement(By.xpath(OR.getProperty("SearchBox"))).sendKeys(Data.getProperty("OutOfStockProduct"));
			
			// Out of stock product search
			utillObj.wait(3);
			driver.findElement(By.xpath(OR.getProperty("OutOfStockProductLink"))).click();
			utillObj.wait(10);
			
			
			//Check notify button	
			utillObj.pageScrollDown();
			Boolean flag=driver.findElement(By.xpath(OR.getProperty("NotifyButton"))).isDisplayed();
			if(flag==true) {
				Assert.assertTrue(true, "As Expected, Notify button is displayed");
			}else {
				Assert.assertTrue(false, "Notify button is  not displayed");
			}
			
		} catch (NoSuchElementException nse) {
			Assert.assertTrue(false, "NoSuchWebElement expection occured for the WebElment--" + nse.getMessage());
		} catch (Exception e) {
			Assert.assertTrue(false, "Exception Occured as --" + e.getMessage());
		}
	}	
	

	@Test(priority = 14)
	public void validateUnableToAddOutOfStockProduct_StockAvailabilitySheet_TC07() {
		try {

			/**
			 * This method will use to open Browser
			 */
			openBrowser();

			/**
			 * This method will use to open the application URL
			 */
			utillObj.openApplication();

			// Login to application with unverified email and mobile number
			utillObj.LoginToApplication(Data.getProperty("VerifiedEmail"), Data.getProperty("VerifiedEmailPwd"));

			// select city drop down
			utillObj.wait(6);
			driver.findElement(By.xpath(OR.getProperty("SelectCity"))).click();


			// Select Sabya
			utillObj.wait(2);
			driver.findElement(By.xpath(OR.getProperty("Sabya"))).click();
	

			// Out of stock product search
			utillObj.wait(6);
			driver.findElement(By.xpath(OR.getProperty("SearchBox"))).sendKeys(Data.getProperty("OutOfStockProduct"));
			
			// Out of stock product search
			utillObj.wait(3);
			driver.findElement(By.xpath(OR.getProperty("OutOfStockProductLink"))).click();
			utillObj.wait(10);
			
			
			//Check notify button	
			utillObj.pageScrollDown();
			Boolean flag=driver.findElement(By.xpath(OR.getProperty("AddToCartButton"))).isEnabled();
			if(flag==false) {
				Assert.assertTrue(true, "As Expected,'Add to cart Button' is displayed");
			}else {
				Assert.assertTrue(false, "'Add to cart Button' is not displayed");
			}
			
			//Click on Add to cart button
			driver.findElement(By.xpath(OR.getProperty("AddToCartButton"))).click();
			
			if(driver.getCurrentUrl().equals(Data.getProperty("OutOfStockProductURL"))) {
				Assert.assertTrue(true, "As Expected,'Unable to add out of stock product");
			}else {
				Assert.assertTrue(false, "Aable to add out of stock product");
			}
			
		} catch (NoSuchElementException nse) {
			Assert.assertTrue(false, "NoSuchWebElement expection occured for the WebElment--" + nse.getMessage());
		} catch (Exception e) {
			Assert.assertTrue(false, "Exception Occured as --" + e.getMessage());
		}
	}	
	
	@Test(priority = 15)
	public void validateOutOfStockText_StockAvailability() {
		try {

			/**
			 * This method will use to open Browser
			 */
			openBrowser();

			/**
			 * This method will use to open the application URL
			 */
			utillObj.openApplication();

			// Login to application with unverified email and mobile number
			utillObj.LoginToApplication(Data.getProperty("VerifiedEmail"), Data.getProperty("VerifiedEmailPwd"));

			// select city drop down
			utillObj.wait(6);
			driver.findElement(By.xpath(OR.getProperty("SelectCity"))).click();


			// Select Sabya
			utillObj.wait(2);
			driver.findElement(By.xpath(OR.getProperty("Sabya"))).click();
	

			// Out of stock product search
			utillObj.wait(6);
			driver.findElement(By.xpath(OR.getProperty("SearchBox"))).sendKeys(Data.getProperty("OutOfStockProduct"));
			
			// Out of stock product search
			utillObj.wait(3);
			driver.findElement(By.xpath(OR.getProperty("OutOfStockProductLink"))).click();
			utillObj.wait(10);
			
			
			//Check OOS product	
			utillObj.pageScrollDown();
			//String OOSText=driver.findElement(By.xpath(OR.getProperty("OutOfStock"))).getText().trim();
			 JavascriptExecutor js = (JavascriptExecutor)driver;
			 String text = js.executeScript("return document.getElementsByClassName('tangoico-out_of_stock').innerHTML").toString();
			 System.out.println("Text on hompage is- "+text );
			if(text.equals(Data.getProperty("OutOfStuckText"))) {
				Assert.assertTrue(true, "As Expected,'Out of stock' text is displayed");
			}else {
				Assert.assertTrue(false, "'Out of stock' text is not displayed");
			}
			
			
		} catch (NoSuchElementException nse) {
			Assert.assertTrue(false, "NoSuchWebElement expection occured for the WebElment--" + nse.getMessage());
		} catch (Exception e) {
			Assert.assertTrue(false, "Exception Occured as --" + e.getMessage());
		}
	}	
	
}
