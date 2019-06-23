package com.stc.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.stc.base.TestBase;
import com.stc.base.TestUtils;



public class Sprint1Cases extends TestBase
{

	@BeforeSuite
	public void doSetup(){
		// Initializing the Config Files
		try {
			initialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.assertTrue(false, e.getMessage());
		}

	}

	// Verify Account Creation Terms & Condition checkbox unselected by default
	@Test (enabled = true,priority=1)
	public void verifyTermsAndConditionDefaultState(){
		try{			
			// Starting new instance of browser
			openBrowser();

			//Launch Application
			TestUtils.openApplication();

			//Verify Terms and condition is unselected by default.
			TestUtils.verifyTermsAndConditionsState();
		}
		catch(Exception e){
			Assert.assertTrue(false, "TC failing " + e.getMessage());
		}
	}

	// Verify Account Creation Offers and Alerts checkbox unselected by default
	@Test (enabled = true, priority=2)
	public void verifyOffersAndAlertsDefaultState(){
		try{			
			// Starting new instance of browser
			openBrowser();

			//Launch Application
			TestUtils.openApplication();

			//Verify Offers and Alerts is selected by default.
			TestUtils.verifyOffersAndAlertsState();
		}
		catch(Exception e){
			Assert.assertTrue(false, "TC failing " + e.getMessage());
		}
	}


	// Verify Subscribe to Offers & Alerts can be deselected.
	@Test (enabled = true, priority=3)
	public void OffersAndAlertsDeselect(){
		try{			
			// Starting new instance of browser
			openBrowser();

			//Launch Application
			TestUtils.openApplication();

			//Verify Offers and Alerts is selected by default.
			TestUtils.deselectOffersAndAlerts();
		}
		catch(Exception e){
			Assert.assertTrue(false, "TC failing " + e.getMessage());
		}
	}
        // Warning :- Run this TC ony after two new unregistered mobile numbers Mobile and Mobile2 are updated in Data.properties
	// Verify Account Creation based on different permutation and combinations
	@Test(enabled = false, priority=4)
	public void verifyAccountCreationViaDiffFields(){

		try{
			// Starting new instance of browser
			openBrowser();

			//Launch Application
			TestUtils.openApplication();

			//Fill all the  Account Creation Fields
			TestUtils.fillNewAccountDetails();

			// Select Both Checkbox Offers and Terms & Condition and Submit.
			String mobileNum = TestUtils.Data.getProperty("Mobile1");
			TestUtils.submitAndVerifyAccountMessage("Both Select",mobileNum);

			//Fill all the  Account Creation Fields
			TestUtils.fillNewAccountDetails();

			// Select Subscribe to Offers & Alerts Checkbox only
			mobileNum = TestUtils.Data.getProperty("Mobile2");
			TestUtils.submitAndVerifyAccountMessage("Offers", mobileNum);

			// Select Subscribe to Offers & Alerts Checkbox only
			mobileNum = TestUtils.Data.getProperty("Mobile2");
			TestUtils.submitAndVerifyAccountMessage("Terms",mobileNum);
		}
		catch(Exception e){
			Assert.assertTrue(false, "TC failing " + e.getMessage());
		}	

	}

	// Verify User get an Error message if he tries to create an Account with already registered Mobile Number
	@Test(enabled = true, priority=5)
	public void duplicateMobileNumberError(){

		try{
			// Starting new instance of browser
			openBrowser();

			//Launch Application
			TestUtils.openApplication();

			//Fill all the  Account Creation Fields
			TestUtils.fillNewAccountDetails();

			// Verify the Error message for already registered number
			TestUtils.verifyDuplicateMobileErrorMessage();

		}
		catch(Exception e){
			Assert.assertTrue(false, "TC failing " + e.getMessage());
		}	
	}

	// Verify User get an Error message if he tries to create an Account with already registered Email Address
	@Test(enabled = true, priority=6)
	public void duplicateEmailError(){

		try{
			// Starting new instance of browser
			openBrowser();

			//Launch Application
			TestUtils.openApplication();

			// Verify the Error message for already registered number
			TestUtils.verifyDuplicateEmailErrorMessage();

		}
		catch(Exception e){
			Assert.assertTrue(false, "TC failing " + e.getMessage());
		}	
	}

	// Verify User get an Error message if Invalid Mobile Number is used while creating an Account
	@Test(enabled = true, priority=7)
	public void InvalidMobileNumErrorMsg(){

		try{
			// Starting new instance of browser
			openBrowser();

			//Launch Application
			TestUtils.openApplication();

			// Verify the Error message for already registered number
			TestUtils.verifyInvalidMobileNumErrorMsg();

		}
		catch(Exception e){
			Assert.assertTrue(false, "TC failing " + e.getMessage());
		}	
	}

	// Verify User get an Error message if Invalid Email is used while creating an Account
	@Test(enabled = true, priority=8)
	public void InvalidEmailErrorMsg(){

		try{
			// Starting new instance of browser
			openBrowser();

			//Launch Application
			TestUtils.openApplication();

			// Verify the Error message for already registered number
			TestUtils.verifyInvalidEmailErrorMsg();

		}
		catch(Exception e){
			Assert.assertTrue(false, "TC failing " + e.getMessage());
		}	
	}

	// Verify User get an Error message if small length password is used while creating an Account
	@Test(enabled = true, priority=9)
	public void smallPasswordErrorMsg(){

		try{
			// Starting new instance of browser
			openBrowser();

			//Launch Application
			TestUtils.openApplication();

			// Verify the Error message for choosing small password
			TestUtils.verifySmallPasswordErrorMsg();

		}
		catch(Exception e){
			Assert.assertTrue(false, "TC failing " + e.getMessage());
		}	
	}

	// Verify User is not able to navigate to the checkout page if email and phone are not verified
	@Test(enabled = true, priority=10)
	public void checkoutWithoutEmailMobileVerification(){

		try{
			// Starting new instance of browser
			openBrowser();

			//Launch Application
			TestUtils.openApplication();

			// Login in with valid email nd pwd
			TestUtils.doLogin();

			//Navigate to profile and modify email
			String email = TestUtils.Data.getProperty("ModifiedEmail");
			TestUtils.modifyEmail(email);

			// Verify Email and Mobile State
			String mobileNumStatus = TestUtils.Data.getProperty("NonVerifiedStatus");
			String emailStatus = TestUtils.Data.getProperty("NonVerifiedStatus");
			TestUtils.VerifyMobileEmailState(mobileNumStatus,emailStatus);

			// Click any Product Category
			TestUtils.selectSawaVouchers();

			// Click on add to cart and navigate to checkout
			String addToCart = TestUtils.OR.getProperty("AddToCart");
			TestUtils.addAndNavToCheckout(addToCart);
				

			// Verify Contact details page 
			//TestUtils.verifyContactDetailsPage();

			// Verify Email And Mobile Status on My Contacts Page
			TestUtils.VerifyMobileEmailState(mobileNumStatus,emailStatus);

			//Navigate to profile and modify email to earlier value
			email = TestUtils.Data.getProperty("Email");
			TestUtils.modifyEmail(email);
			
			//Delete Cart
			TestUtils.deleteCart();
			
			// Sign out user
			TestUtils.signoutUser();


		}

		catch(Exception e){
			Assert.assertTrue(false, "TC failing " + e.getMessage());
		}	
	}

	// verify Notify me option for registered User
	@Test(enabled = true, priority=11)
	public void notifyMeRegisteredUser(){

		try{
			// Starting new instance of browser
			openBrowser();

			//Launch Application
			TestUtils.openApplication();

			// Login in with valid email and pwd
			TestUtils.doLogin();

			//Change City
			String cityName = TestUtils.OR.getProperty("Sabya_City");
			TestUtils.changeCity(cityName);

			// Search Product
			String productName = TestUtils.Data.getProperty("ProductName");
			TestUtils.searchProduct(productName);

			// Verify Email is appearing on Notify me option
			TestUtils.verifyNotifyEmailAndMobileRegUser();
			
			//Navigate to Default City Riyadh
			cityName = TestUtils.OR.getProperty("Riyadh_City");
			TestUtils.changeCity(cityName);
			
			// Sign out
			TestUtils.signoutUser();
		}

		catch(Exception e){
			Assert.assertTrue(false, "TC failing " + e.getMessage());
		}	
	}


	// verify Notify me option for Guest User
	@Test(enabled = true, priority=12)
	public void notifyMeGuestUser(){

		try{
			// Starting new instance of browser
			openBrowser();

			//Launch Application
			TestUtils.openApplication();

			//Navigate to profile and modify email
			String cityName = TestUtils.OR.getProperty("Sabya_City");
			TestUtils.changeCity(cityName);

			// Search Product
			String productName = TestUtils.Data.getProperty("ProductName");
			TestUtils.searchProduct(productName);

			// Verify Email is appearing on Notify me option
			TestUtils.verifyNotifyEmailAndMobileGuestUser();
			
			cityName = TestUtils.OR.getProperty("Riyadh_City");
			TestUtils.changeCity(cityName);


		}

		catch(Exception e){
			Assert.assertTrue(false, "TC failing " + e.getMessage());
		}	
	}

	// verify Notify me option for Two Cities
	@Test(enabled = true, priority=13)
	public void notifyMeTwoCities(){

		try{
			// Starting new instance of browser
			openBrowser();
			//Launch Application
			TestUtils.openApplication();

			// Login in with valid email and pwd
			TestUtils.doLogin();

			// Search Product
			String productName = TestUtils.Data.getProperty("ProductName");
			TestUtils.searchProduct(productName);
			
			// Add Product to Cart and verify Checkout Page is Coming.
			String addToCart = TestUtils.OR.getProperty("AddToCart_Button");
			TestUtils.addAndNavToCheckout(addToCart);
			
			// Delete Cart
			TestUtils.deleteCart();
			
			// Navigate to Home Page
			TestUtils.navToHomePage();

			//Change City
			String cityName = TestUtils.OR.getProperty("Sabya_City");
			TestUtils.changeCity(cityName);

			// Search Product
			productName = TestUtils.Data.getProperty("ProductName");
			TestUtils.searchProduct(productName);
			
			// Verify Email is appearing on Notify me option
			TestUtils.verifyNotifyEmailAndMobileRegUser();
			
			// Verify Add to Cart option.
			TestUtils.verifyAddtoCart();
			
			//Navigate to Default City Riyadh
			cityName = TestUtils.OR.getProperty("Riyadh_City");
			TestUtils.changeCity(cityName);
			
			// Sign out
			TestUtils.signoutUser();
		}

		catch(Exception e){
			Assert.assertTrue(false, "TC failing " + e.getMessage());
		}	
	}

}

